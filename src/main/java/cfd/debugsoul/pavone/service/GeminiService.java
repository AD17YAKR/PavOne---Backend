package cfd.debugsoul.pavone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class GeminiService {
    
    @Value("${gemini.api.key}")
    private String apiKey;
    
    @Value("${gemini.api.url}")
    private String apiUrl;
    
    private final Random random = new Random();
    
    public Map<String, Object> analyzeTreePhoto(String photoUrl, String species) {
        // Mock implementation for hackathon
        log.info("Mock Gemini analysis for photo: {} species: {}", photoUrl, species);
        
        Map<String, Object> analysis = new HashMap<>();
        analysis.put("healthStatus", getRandomHealthStatus());
        analysis.put("growthEstimatePercent", BigDecimal.valueOf(random.nextDouble() * 10 + 2)); // 2-12%
        analysis.put("detectedForgery", random.nextBoolean() && random.nextDouble() < 0.1); // 10% chance
        analysis.put("confidence", BigDecimal.valueOf(0.8 + random.nextDouble() * 0.2)); // 80-100%
        analysis.put("aiModel", "gemini-pro-vision-mock");
        analysis.put("analysis", "Mock AI analysis: Tree appears healthy with good growth indicators.");
        
        return analysis;
    }
    
    private String getRandomHealthStatus() {
        String[] statuses = {"Excellent", "Good", "Fair", "Poor"};
        return statuses[random.nextInt(statuses.length)];
    }
}