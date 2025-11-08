package cfd.debugsoul.pavone.controller;

import cfd.debugsoul.pavone.entity.User;
import cfd.debugsoul.pavone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    
    private final UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String wallet = request.get("wallet");
        
        // Mock JWT for hackathon - in production, verify signature
        String mockJwt = "mock-jwt-" + wallet.hashCode();
        
        // Create user if doesn't exist
        if (!userRepository.existsByWalletAddress(wallet)) {
            User user = new User();
            user.setWalletAddress(wallet);
            user.setDisplayName("User " + wallet.substring(0, 6));
            userRepository.save(user);
            log.info("Created new user for wallet: {}", wallet);
        }
        
        return ResponseEntity.ok(Map.of(
            "token", mockJwt,
            "wallet", wallet
        ));
    }
}