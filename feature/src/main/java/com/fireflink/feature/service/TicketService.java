package com.fireflink.feature.service;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.CommentDto;
import com.fireflink.feature.models.dto.TicketDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    ResponseEntity<ApiResponse> saveTicket(TicketDto ticketDto, String userEmail);

    ResponseEntity<ApiResponse> updateTicket(String userEmail, String id, TicketDto ticketDto);

    ResponseEntity<ApiResponse> assignTickets(List<String> ticketId, String userEmail, String assigneeEmail);

    ResponseEntity<ApiResponse> updateTicketStatus(String status, String ticketId);

    ResponseEntity<ApiResponse> getSupportMembers();

    ResponseEntity<ApiResponse> searchTicket(String search);

    ResponseEntity<ApiResponse> findTicketById(String id);

    ResponseEntity<ApiResponse> findAllTicketsByUserEmail(String userEmail, int pageNumber, int pageSize);

    ResponseEntity<ApiResponse> findAllTickets(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> addComments(String ticketId, CommentDto commentDto, String email);

    ResponseEntity<ApiResponse> findCommentsByCommentId(int pageNo, int size, String commentId, String email);

    ResponseEntity<ApiResponse> findCommentsByTicketId(String ticketId);
}
