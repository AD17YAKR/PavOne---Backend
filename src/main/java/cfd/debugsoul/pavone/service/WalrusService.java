package cfd.debugsoul.pavone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class WalrusService {
    
    @Value("${walrus.api.key}")
    private String apiKey;
    
    @Value("${walrus.api.url}")
    private String apiUrl;
    
    public String uploadFile(MultipartFile file) throws IOException {
        // Mock implementation for hackathon
        String mockCid = "bafy" + System.currentTimeMillis();
        log.info("Mock upload to Walrus: {} -> CID: {}", file.getOriginalFilename(), mockCid);
        return mockCid;
    }
    
    public String uploadContent(String content, String filename) {
        // Mock implementation for hackathon
        String mockCid = "bafy" + System.currentTimeMillis() + "_" + filename.hashCode();
        log.info("Mock upload content to Walrus: {} -> CID: {}", filename, mockCid);
        return mockCid;
    }
    
    public String getFileUrl(String cid) {
        return apiUrl + "/v1/retrieve/" + cid;
    }
}