package com.fireflink.feature.repository;

import com.fireflink.feature.models.entity.Comment;
import com.fireflink.feature.models.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByAssigneeEmail(String assigneeEmail);

    List<Ticket> findByCreatedByEmail(String userEmail);

    Optional<Ticket> findByTicketId(String ticketId);

//    Page<Comment> findByCommentId(Pageable pageable);

//    Page<Comment> findAllCommentsByTicketId(Pageable pageable);
}
