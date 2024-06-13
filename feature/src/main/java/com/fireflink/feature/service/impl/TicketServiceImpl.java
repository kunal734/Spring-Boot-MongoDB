package com.fireflink.feature.service.impl;

import com.fireflink.feature.dao.TicketDao;
import com.fireflink.feature.dao.UserDao;
import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.CommentDto;
import com.fireflink.feature.models.dto.TicketDto;
import com.fireflink.feature.models.entity.Comment;
import com.fireflink.feature.models.entity.Ticket;
import com.fireflink.feature.models.entity.User;
import com.fireflink.feature.service.TicketService;
import com.fireflink.feature.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final EmailServiceImpl emailServiceImpl;
    private String format = "Hi %s";
    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<ApiResponse> saveTicket(TicketDto ticketDto, String userEmail) {
        Optional<User> receivedUser = userDao.findByEmail(userEmail);
        if (receivedUser.isPresent() && receivedUser.get().getAccess().equals(Access.EditAccess)) {
            Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
            ticket.setStatus(AccountStatus.OPEN);
            ticket.setCreatedEntity(receivedUser.get().getName(), receivedUser.get().getEmail());
            String subject = String.format(format, receivedUser.get().getName());
            emailServiceImpl.sendEmail(receivedUser.get().getEmail(),subject,"Your Ticket has been created...");
            return ResponseUtil.getCreatedResponse(ticketDao.saveTicket(ticket));
        }
        return ResponseUtil.getBadRequestResponse("You do not have access to create ticket");
    }

    @Override
    public ResponseEntity<ApiResponse> updateTicket(String userEmail, String id, TicketDto ticketDto) {
        Optional<User> user = userDao.findByEmail(userEmail);
        if (user.isPresent() && (user.get().getAccess().equals(Access.EditAccess) || user.get().getCreatedByEmail().equals(userEmail))) {
            Optional<Ticket> ticket = ticketDao.findByTicketId(id);
            if(ticket.isPresent()){
                Ticket updatedTicket = modelMapper.map(ticketDto, Ticket.class);
                updatedTicket.setTicketId(ticket.get().getTicketId());
                updatedTicket.setStatus(ticket.get().getStatus());
                updatedTicket.setCreatedEntity(ticket.get().getCreatedByName(), ticket.get().getCreatedByEmail());
                updatedTicket.setModifiedEntity(user.get().getName(), user.get().getEmail());
                return ResponseUtil.getOkResponse(ticketDao.saveTicket(updatedTicket));
            }
            return ResponseUtil.getBadRequestResponse("Invalid Ticket ID");
        }
        return ResponseUtil.getBadRequestResponse("No user found with the email");
    }

    @Override
    public ResponseEntity<ApiResponse> assignTickets(List<String> ticketIds, String userEmail, String assigneeEmail) {
        Optional<User> optionalUser = userDao.findByEmail(userEmail);
        if(optionalUser.isPresent() && optionalUser.get().getRole().equals(Role.Admin)){
            List<Ticket> tickets = ticketDao.findAllById(ticketIds);
            for (Ticket ticket : tickets) {
                if(optionalUser.get().getRole().equals(Role.Customer)){
                    return ResponseUtil.getBadRequestResponse("You do not have access to assign tickets");
                }else {
                    Optional<User> assignee = userDao.findByEmail(assigneeEmail);
                    if (assignee.isPresent() && assignee.get().getRole().equals(Role.Internal_Team)) {
                        ticket.setStatus(AccountStatus.OPEN);
                        ticket.setCreatedEntity(ticket.getCreatedByName(), ticket.getCreatedByEmail());
                        ticket.setModifiedEntity(optionalUser.get().getName(), optionalUser.get().getEmail());
                        ticket.setAssigneeDetails(assignee.get().getName(), assignee.get().getEmail(), assignee.get().getTeam());
                        emailServiceImpl.sendEmail(optionalUser.get().getEmail(),"Ticket Assigned","You have assigned a ticket to "+assignee.get().getName());
                        emailServiceImpl.sendEmail(assignee.get().getEmail(),"Ticket Assigned","Ticket has been assigned to you...");
                        return ResponseUtil.getOkResponse(ticketDao.saveTicket(ticket));
                    }
                    return ResponseUtil.getBadRequestResponse("Invalid Assignee Email or Assignee is not part of Internal Team");
                }
            }
        }
        return ResponseUtil.getBadRequestResponse("No user found with the email or You do not have access to assign tickets");
    }

    @Override
    public ResponseEntity<ApiResponse> updateTicketStatus(String status, String ticketId){
        if(StringUtils.isBlank(status) && StringUtils.isBlank(ticketId)){
            Ticket ticket = ticketDao.findByTicketId(ticketId).get();
            ticket.setStatus(ticket.getStatus().equals(AccountStatus.OPEN) ? AccountStatus.IN_PROGRESS : AccountStatus.OPEN);
            return ResponseUtil.getOkResponse(ticketDao.saveTicket(ticket));
        }
        return ResponseUtil.getBadRequestResponse("Ticket Details Not Found");
    }

    @Override
    public ResponseEntity<ApiResponse> getSupportMembers() {
        List<Ticket> tickets = ticketDao.findAllAssignedTickets();
        if (!tickets.isEmpty()) {
            List<User> users = new ArrayList<>();
            for (Ticket ticket : tickets) {
                users.add(userDao.findByEmail(ticket.getAssigneeEmail()).get());
            }
            return ResponseUtil.getOkResponse(users);
        }
        return ResponseUtil.getBadRequestResponse("No Support Members Found");
    }

    @Override
    public ResponseEntity<ApiResponse> searchTicket(String search) {
        if (!Objects.isNull(search)) {
            return ResponseUtil.getOkResponse(ticketDao.searchTicket(search));
        }
        return ResponseUtil.getBadRequestResponse("Tickets not found");
    }

    @Override
    public ResponseEntity<ApiResponse> findTicketById(String id) {
        return ticketDao.findByTicketId(id).stream().map(ResponseUtil::getOkResponse).findAny().orElseThrow(() -> new NoSuchElementException("Invalid ID"));
    }

    @Override
    public ResponseEntity<ApiResponse> findAllTicketsByUserEmail(String userEmail, int pageNo, int pageSize) {
        Optional<User> optionalUser = userDao.findByEmail(userEmail);
        if (optionalUser.isPresent() && optionalUser.get().getAccess().equals(Access.EditAccess)) {
            Query query = new Query();
            query.with(PageRequest.of(pageNo, pageSize));
            List<Ticket> tickets = mongoTemplate.find(query, Ticket.class);
            long totalCount = mongoTemplate.count(new Query(), Ticket.class);
            PageImpl<Ticket> ticketPage = new PageImpl<>(tickets, PageRequest.of(pageNo, pageSize), totalCount);
            return ResponseUtil.getOkResponse(ticketPage);
        }
        return ResponseUtil.getBadRequestResponse("Invalid User Email");
    }

    @Override
    public ResponseEntity<ApiResponse> findAllTickets(int pageNo, int pageSize) {
        Query query = new Query();
        query.with(PageRequest.of(pageNo, pageSize));
        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class);
        long totalCount = mongoTemplate.count(new Query(), Ticket.class);
        PageImpl<Ticket> ticketPage = new PageImpl<>(tickets, PageRequest.of(pageNo, pageSize), totalCount);
        return ResponseUtil.getOkResponse(ticketPage);
    }

    @Override
    public ResponseEntity<ApiResponse> addComments(String ticketId, CommentDto commentDto, String email) {
        Optional<Ticket> optionalTicket = ticketDao.findByTicketId(ticketId);
        Optional<User> optionalUser = userDao.findByEmail(email);

        if (optionalUser.isPresent() && optionalTicket.isPresent()) {
            User user = optionalUser.get();
            Ticket ticket = optionalTicket.get();

            Comment comment = CommentUtil.initComment(user, commentDto);
            List<Comment> comments = ticket.getComments();

            if (comments == null) {
                comments = new ArrayList<>();
            }

            comments.add(comment);
            ticket.setComments(comments);
            comment.setModifiedEntity(user.getName(), user.getEmail());

            Ticket updatedTicket = ticketDao.saveTicket(ticket);
            return ResponseUtil.getOkResponse(updatedTicket);
        }
        return ResponseUtil.getBadRequestResponse("User Not Registered or Ticket Not Found");
    }

    @Override
    public ResponseEntity<ApiResponse> findCommentsByCommentId(int pageNo, int size, String commentId, String email) {
        return null;
    }

//    @Override
//    public ResponseEntity<ApiResponse> findCommentsByCommentId(int pageNo, int pageSize, String commentId, String email) {
//        Optional<Comment> comment = ticketDao.findByCommentId(pageNo, pageSize, commentId);
//        if(comment.isPresent()){
//            Optional<User> optionalUser = userDao.findByEmail(email);
//            if(optionalUser.isPresent()){
//                List<Comment> comments = comment.get().getReply();
//                User user = optionalUser.get();
//                comment.get().setModifiedEntity(user.getName(), user.getEmail());
//                return ResponseUtil.getOkResponse(new PageImpl<>(comments, PageRequest.of(pageNo, pageSize), comments.size()));
//            }
//        }
//        return ResponseUtil.getNoContentResponse("Comment not found");
//    }

    @Override
    public ResponseEntity<ApiResponse> findCommentsByTicketId(String ticketId){
        Optional<Ticket> tickets=ticketDao.findByTicketId(ticketId);
        return tickets.map(ticket -> ResponseUtil.getOkResponse(ticket.getComments())).orElseGet(() -> ResponseUtil.getBadRequestResponse("Ticket not found"));
    }
}
