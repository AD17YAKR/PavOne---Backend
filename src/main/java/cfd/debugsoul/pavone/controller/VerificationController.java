package cfd.debugsoul.pavone.controller;

import cfd.debugsoul.pavone.dto.VerificationRequest;
import cfd.debugsoul.pavone.entity.Report;
import cfd.debugsoul.pavone.entity.Tree;
import cfd.debugsoul.pavone.repository.TreeRepository;
import cfd.debugsoul.pavone.service.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
@Slf4j
public class VerificationController {
    
    private final VerificationService verificationService;
    private final TreeRepository treeRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> verify(@Valid @RequestBody VerificationRequest request) {
        Report report = verificationService.verify(
            request.getTreeId(), 
            request.getPhotoId(), 
            request.getRequestedBy()
        );
        
        Tree tree = treeRepository.findById(request.getTreeId())
            .orElseThrow(() -> new RuntimeException("Tree not found"));
        
        Long suggestedGrams = verificationService.calculateSuggestedGrams(
            tree.getSpecies(), 
            report.getGrowthEstimatePercent()
        );
        
        return ResponseEntity.ok(Map.of(
            "reportId", report.getId(),
            "healthStatus", report.getHealthStatus(),
            "growthEstimatePercent", report.getGrowthEstimatePercent(),
            "detectedForgery", report.getDetectedForgery(),
            "suggestedGrams", suggestedGrams,
            "walrusCid", report.getWalrusCid()
        ));
    }
}