package com.example.eventproposal.service;

import com.example.eventproposal.exception.ResourceNotFoundException;
import com.example.eventproposal.model.dto.EventProposalDto;
import com.example.eventproposal.model.dto.StatusUpdateRequest;
import com.example.eventproposal.model.entity.EventProposalEntity;
import com.example.eventproposal.model.enumeration.Status;
import com.example.eventproposal.repository.EventProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventProposalService {

    private final EventProposalRepository repository;

    @Autowired
    public EventProposalService(EventProposalRepository repository) {
        this.repository = repository;
    }

    public EventProposalEntity createProposal(EventProposalDto dto) {
        EventProposalEntity entity = new EventProposalEntity();
        entity.setProposerName(dto.getProposerName());
        entity.setEmail(dto.getEmail());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setProposalDate(dto.getProposedDate());
        entity.setLocation(dto.getLocation());
        entity.setPriority(dto.getPriority());
        entity.setStatus(Status.PENDING);
        return repository.save(entity);
    }

    public Page<EventProposalEntity> getAllProposals(Pageable pageable, Optional<Status> status) {
        return status.map(s -> repository.findAllByStatus(s, pageable))
                .orElseGet(() -> repository.findAll(pageable));
    }

    public EventProposalEntity getProposalById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposal not found with id: " + id));
    }

    public EventProposalEntity updateStatus(Long id, StatusUpdateRequest request) {
        EventProposalEntity entity = getProposalById(id);

        if (entity.getStatus() != Status.PENDING) {
            throw new IllegalStateException("Only PENDING proposals can be updated.");
        }

        Status newStatus = request.getStatus();
        if (newStatus != Status.APPROVED && newStatus != Status.REJECTED) {
            throw new IllegalArgumentException("Status must be APPROVED or REJECTED.");
        }

        entity.setStatus(newStatus);
        entity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return repository.save(entity);
    }

    public void deleteProposal(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Proposal not found with id: " + id);
        }
        repository.deleteById(id);
    }
}