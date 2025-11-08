package cfd.debugsoul.pavone.controller;

import cfd.debugsoul.pavone.dto.CreditMintRequest;
import cfd.debugsoul.pavone.entity.Credit;
import cfd.debugsoul.pavone.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/credits")
@RequiredArgsConstructor
@Slf4j
public class CreditController {
    
    private final CreditService creditService;
    
    @PostMapping("/mint")
    public ResponseEntity<Credit> mintCredit(@Valid @RequestBody CreditMintRequest request) {
        Credit credit = creditService.mintCredit(
            request.getTreeId(),
            request.getOwnerWallet(),
            request.getGrams(),
            request.getReportId()
        );
        
        return ResponseEntity.ok(credit);
    }
    
    @PostMapping("/{creditId}/retire")
    public ResponseEntity<Credit> retireCredit(
            @PathVariable UUID creditId,
            @RequestBody Map<String, String> request) {
        
        String retiredBy = request.get("retiredBy");
        Credit credit = creditService.retireCredit(creditId, retiredBy);
        
        return ResponseEntity.ok(credit);
    }
    
    @GetMapping("/balance/{wallet}")
    public ResponseEntity<Map<String, Long>> getBalance(@PathVariable String wallet) {
        Long balance = creditService.getBalance(wallet);
        return ResponseEntity.ok(Map.of("totalGrams", balance));
    }
    
    @GetMapping("/owner/{wallet}")
    public ResponseEntity<List<Credit>> getCreditsByOwner(@PathVariable String wallet) {
        List<Credit> credits = creditService.getCreditsByOwner(wallet);
        return ResponseEntity.ok(credits);
    }
}