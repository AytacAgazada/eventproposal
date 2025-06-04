package com.example.eventproposal.repository;

import com.example.eventproposal.model.entity.EventProposalEntity;
import com.example.eventproposal.model.enumeration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventProposalRepository extends JpaRepository<EventProposalEntity,Long> {

    Page<EventProposalEntity> findAllByStatus(Status s, org.springframework.data.domain.Pageable pageable);

}
