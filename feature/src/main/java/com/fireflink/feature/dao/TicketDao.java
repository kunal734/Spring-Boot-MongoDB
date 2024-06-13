package com.fireflink.feature.dao;

import com.fireflink.feature.models.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDao {

    Ticket saveTicket(Ticket ticket);

    Optional<Ticket> findByTicketId(String id);

    List<Ticket> searchTicket(String search);

//    Page<Comment> findByCommentId(int pageNo, int pageSize, String id);

    List<Ticket> findAllTickets();

    List<Ticket> findAllById(List<String> ticketIds);

    int findCountOfTickets(String email);

    List<Ticket> findAllAssignedTickets();
//    Page<Comment> findCommentsByTicketId(int pageNo, int pageSize, String ticketId);
}
