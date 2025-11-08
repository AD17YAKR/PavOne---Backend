package cfd.debugsoul.pavone.service;

import cfd.debugsoul.pavone.entity.Photo;
import cfd.debugsoul.pavone.entity.Report;
import cfd.debugsoul.pavone.entity.Tree;
import cfd.debugsoul.pavone.repository.PhotoRepository;
import cfd.debugsoul.pavone.repository.ReportRepository;
import cfd.debugsoul.pavone.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationService {
    
    private final TreeRepository treeRepository;
    private final PhotoRepository photoRepository;
    private final ReportRepository reportRepository;
    private final GeminiService geminiService;
    private final WalrusService walrusService;
    
    @Transactional
    public Report verify(UUID treeId, UUID photoId, String requestedBy) {
        Tree tree = treeRepository.findById(treeId)
            .orElseThrow(() -> new RuntimeException("Tree not found"));
        
        Photo photo = photoRepository.findById(photoId)
            .orElseThrow(() -> new RuntimeException("Photo not found"));
        
        if (!photo.getTree().getId().equals(treeId)) {
            throw new RuntimeException("Photo does not belong to the specified tree");
        }
        
        // Get photo URL from Walrus
        String photoUrl = walrusService.getFileUrl(photo.getWalrusCid());
        
        // Analyze with Gemini
        Map<String, Object> analysis = geminiService.analyzeTreePhoto(photoUrl, tree.getSpecies());
        
        // Create report
        Report report = new Report();
        report.setTree(tree);
        report.setAiModel((String) analysis.get("aiModel"));
        report.setHealthStatus((String) analysis.get("healthStatus"));
        report.setGrowthEstimatePercent((BigDecimal) analysis.get("growthEstimatePercent"));
        report.setDetectedForgery((Boolean) analysis.get("detectedForgery"));
        report.setReportJson(analysis);
        
        // Store report to Walrus
        String reportCid = walrusService.uploadContent(analysis.toString(), "report_" + report.getId() + ".json");
        report.setWalrusCid(reportCid);
        
        return reportRepository.save(report);
    }
    
    public Long calculateSuggestedGrams(String species, BigDecimal growthPercent) {
        // Simple species-based calculation (kg/year * 1000 for grams)
        Map<String, Integer> speciesLookup = Map.of(
            "Moringa", 15,
            "Oak", 25,
            "Pine", 20,
            "Eucalyptus", 30
        );
        
        int baseKgPerYear = speciesLookup.getOrDefault(species, 20);
        double multiplier = growthPercent.doubleValue() / 100.0;
        
        return Math.round(baseKgPerYear * 1000 * multiplier);
    }
}