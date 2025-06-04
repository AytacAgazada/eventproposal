package com.example.eventproposal.model.dto;

import com.example.eventproposal.model.enumeration.Priority;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventProposalDto {

    @NotBlank(message = "Proposer name is required")
    @Size(min = 2, max = 100, message = "Proposer name must be between 2 and 100 characters")
    private String proposerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 30, max = 1000, message = "Description must be between 30 and 1000 characters")
    private String description;

    @NotNull(message = "Proposed date is required")
    @FutureOrPresent(message = "Proposed date must be today or in the future")
    private LocalDate proposedDate;

    @NotBlank(message = "Location is required")
    @Size(min = 5, max = 200, message = "Location must be between 5 and 200 characters")
    private String location;

    @NotNull(message = "Priority is required")
    private Priority priority;
}
