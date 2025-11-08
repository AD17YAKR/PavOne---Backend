package cfd.debugsoul.pavone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class TreeCreateRequest {
    @NotBlank
    private String ownerWallet;
    
    @NotBlank
    private String species;
    
    @NotNull
    private LocalDate plantedDate;
    
    @NotNull
    private Double lat;
    
    @NotNull
    private Double lon;
    
    private Integer initialHeightMm;
    private Integer initialDbhMm;
    private Map<String, Object> metadata;
}