package cfd.debugsoul.pavone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class CreditMintRequest {
    @NotNull
    private UUID treeId;
    
    @NotBlank
    private String ownerWallet;
    
    @NotNull
    @Positive
    private Long grams;
    
    private UUID reportId;
}