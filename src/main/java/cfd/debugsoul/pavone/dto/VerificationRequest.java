package cfd.debugsoul.pavone.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class VerificationRequest {
    @NotNull
    private UUID treeId;
    
    @NotNull
    private UUID photoId;
    
    private String requestedBy;
}