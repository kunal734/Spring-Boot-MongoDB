package com.fireflink.feature.dao.impl;

import com.fireflink.feature.dao.TicketDao;
import com.fireflink.feature.models.entity.Ticket;
import com.fireflink.feature.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDao {

    private final TicketRepository ticketRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findByTicketId(String ticketId) {
        return ticketRepository.findByTicketId(ticketId);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllById(List<String> ticketIds) {
        return ticketRepository.findAllById(ticketIds);
    }

    @Override
    public List<Ticket> searchTicket(String search){
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("issueRelatedTo").is(search),
                Criteria.where("summary").regex(search,"i"),
                Criteria.where("id").is(search)
        );
        Query query = Query.query(criteria);
        return mongoTemplate.find(query,Ticket.class);
    }

    @Override
    public int findCountOfTickets(String email) {
        List<Ticket> optionalUser = ticketRepository.findByAssigneeEmail(email);
        return optionalUser.size();
    }

    @Override
    public List<Ticket> findAllAssignedTickets() {
        return ticketRepository.findAll().stream().filter(ticket -> ticket.getAssigneeEmail() != null).toList();
    }


//    @Override
//    public Page<Comment> findByCommentId(int pageNo, int pageSize, String id) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        return ticketRepository.findByCommentId(pageable);
//    }

//    @Override
//    public Page<Comment> findCommentsByTicketId(int pageNo, int pageSize, String ticketId) {
//        Optional<Ticket> ticket = ticketRepository.findByTicketId(ticketId);
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        return ticketRepository.findAllCommentsByTicketId(pageable);
//    }


}
