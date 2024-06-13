package com.fireflink.feature.controller;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.CommentDto;
import com.fireflink.feature.models.dto.TicketDto;
import com.fireflink.feature.service.TicketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveTicket(@Valid @RequestBody TicketDto ticketDto, @RequestHeader String userEmail) {
        return ticketService.saveTicket(ticketDto, userEmail);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTicket(@RequestHeader @Email(message = "Invalid Email format") @Valid String userEmail, @RequestParam String ticketId, @RequestBody @Valid TicketDto ticketDto) {
        return ticketService.updateTicket(userEmail, ticketId, ticketDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllTickets(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "5") int pageSize) {
        return ticketService.findAllTickets(pageNo, pageSize);
    }

    @PutMapping("/assignTickets")
    public ResponseEntity<ApiResponse> assignTickets(@RequestBody List<String> ticketIds, @RequestHeader @Email(message = "Invalid Email format") @Valid String userEmail, @RequestHeader @Email(message = "Invalid Email format") @Valid String assigneeEmail){
        return ticketService.assignTickets(ticketIds, userEmail, assigneeEmail);
    }

    @PatchMapping("/updateTicketStatus")
    public ResponseEntity<ApiResponse> updateTicketStatus(@RequestParam String status, @RequestParam String ticketId){
        return ticketService.updateTicketStatus(status, ticketId);
    }

    @GetMapping("/supportMembers")
    public ResponseEntity<ApiResponse> getSupportMembers() {
        return ticketService.getSupportMembers();
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchTicket(@RequestParam String search) {
        return ticketService.searchTicket(search);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<ApiResponse> findTicketById(@PathVariable @ Valid String ticketId) {
        return ticketService.findTicketById(ticketId);
    }

    @GetMapping("/userTickets/{userEmail}")
    public ResponseEntity<ApiResponse> findTicketByUserId(@PathVariable @Email(message = "Invalid Email format") @Valid String userEmail, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "20")int pageSize){
        return ticketService.findAllTicketsByUserEmail(userEmail, pageNumber,pageSize );
    }

    @PatchMapping("/addComment")
    public ResponseEntity<ApiResponse> addComments(@RequestParam String ticketId, @Valid @RequestBody CommentDto commentDto, @RequestParam String email) {
        return ticketService.addComments(ticketId, commentDto, email);
    }

    @GetMapping("/allComments")
    public ResponseEntity<ApiResponse> findCommentsByCommentId(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int size,@RequestParam String ticketId,@RequestParam String email){
        return ticketService.findCommentsByCommentId(pageNo,size,ticketId,email);
    }

    @GetMapping("/findAllCommentsByTicketId")
    public ResponseEntity<ApiResponse> findCommentsByTicketId(@RequestParam String ticketId){
        return ticketService.findCommentsByTicketId(ticketId);
    }
}
