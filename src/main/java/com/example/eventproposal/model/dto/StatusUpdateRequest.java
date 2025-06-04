package com.example.eventproposal.model.dto;

import com.example.eventproposal.model.enumeration.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull(message = "Status is required")
    private Status status;
}
