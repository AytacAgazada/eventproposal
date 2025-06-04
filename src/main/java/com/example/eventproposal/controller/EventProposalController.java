package com.example.eventproposal.controller;

import com.example.eventproposal.model.dto.EventProposalDto;
import com.example.eventproposal.model.dto.StatusUpdateRequest;
import com.example.eventproposal.model.entity.EventProposalEntity;
import com.example.eventproposal.model.enumeration.Status;
import com.example.eventproposal.service.EventProposalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/proposals")
public class EventProposalController {

    private final EventProposalService service;

    public EventProposalController(EventProposalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventProposalEntity> createProposal(@RequestBody @Valid EventProposalDto dto) {
        return ResponseEntity.ok(service.createProposal(dto));
    }

    @GetMapping
    public ResponseEntity<Page<EventProposalEntity>> getAll(@RequestParam Optional<Status> status, Pageable pageable) {
        return ResponseEntity.ok(service.getAllProposals(pageable, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventProposalEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProposalById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EventProposalEntity> updateStatus(@PathVariable Long id, @RequestBody @Valid StatusUpdateRequest request) {
        return ResponseEntity.ok(service.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProposal(id);
        return ResponseEntity.noContent().build();
    }
}
