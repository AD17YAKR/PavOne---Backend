package cfd.debugsoul.pavone.controller;

import cfd.debugsoul.pavone.dto.TreeCreateRequest;
import cfd.debugsoul.pavone.entity.Photo;
import cfd.debugsoul.pavone.entity.Tree;
import cfd.debugsoul.pavone.entity.User;
import cfd.debugsoul.pavone.repository.PhotoRepository;
import cfd.debugsoul.pavone.repository.TreeRepository;
import cfd.debugsoul.pavone.repository.UserRepository;
import cfd.debugsoul.pavone.service.WalrusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trees")
@RequiredArgsConstructor
@Slf4j
public class TreeController {
    
    private final TreeRepository treeRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final WalrusService walrusService;
    
    @PostMapping
    public ResponseEntity<Tree> createTree(@Valid @RequestBody TreeCreateRequest request) {
        User owner = userRepository.findByWalletAddress(request.getOwnerWallet())
            .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        Tree tree = new Tree();
        tree.setOwner(owner);
        tree.setSpecies(request.getSpecies());
        tree.setPlantedDate(request.getPlantedDate());
        tree.setLat(request.getLat());
        tree.setLon(request.getLon());
        tree.setLatLonHash(hashLocation(request.getLat(), request.getLon()));
        tree.setInitialHeightMm(request.getInitialHeightMm());
        tree.setInitialDbhMm(request.getInitialDbhMm());
        tree.setMetadata(request.getMetadata());
        
        Tree savedTree = treeRepository.save(tree);
        log.info("Created tree {} for owner {}", savedTree.getId(), request.getOwnerWallet());
        
        return ResponseEntity.ok(savedTree);
    }
    
    @GetMapping("/{treeId}")
    public ResponseEntity<Tree> getTree(@PathVariable UUID treeId) {
        Tree tree = treeRepository.findById(treeId)
            .orElseThrow(() -> new RuntimeException("Tree not found"));
        
        return ResponseEntity.ok(tree);
    }
    
    @PostMapping("/{treeId}/photos")
    public ResponseEntity<Map<String, Object>> uploadPhoto(
            @PathVariable UUID treeId,
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "X-Demo-Wallet", required = false) String uploaderWallet) throws IOException {
        
        Tree tree = treeRepository.findById(treeId)
            .orElseThrow(() -> new RuntimeException("Tree not found"));
        
        User uploader = null;
        if (uploaderWallet != null) {
            uploader = userRepository.findByWalletAddress(uploaderWallet).orElse(null);
        }
        
        // Upload to Walrus
        String walrusCid = walrusService.uploadFile(file);
        
        // Save photo record
        Photo photo = new Photo();
        photo.setTree(tree);
        photo.setWalrusCid(walrusCid);
        photo.setFilename(file.getOriginalFilename());
        photo.setUploadedBy(uploader);
        
        Photo savedPhoto = photoRepository.save(photo);
        
        return ResponseEntity.ok(Map.of(
            "photoId", savedPhoto.getId(),
            "walrusCid", walrusCid,
            "uploadedAt", savedPhoto.getUploadedAt()
        ));
    }
    
    private String hashLocation(Double lat, Double lon) {
        // Simple hash for privacy - in production use proper geohashing
        return String.valueOf(Double.valueOf(lat + lon).hashCode());
    }
}