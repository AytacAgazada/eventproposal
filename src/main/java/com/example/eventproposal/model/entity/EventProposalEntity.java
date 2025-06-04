package com.example.eventproposal.model.entity;


import com.example.eventproposal.model.enumeration.Priority;
import com.example.eventproposal.model.enumeration.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
public class EventProposalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proposerName;
    private String email;
    private String title;
    private String description;
    private String location;
    private Priority priority;
    private Status status;
    private LocalDate proposalDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
