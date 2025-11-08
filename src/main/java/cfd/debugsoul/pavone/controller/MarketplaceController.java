package cfd.debugsoul.pavone.controller;

import cfd.debugsoul.pavone.entity.Listing;
import cfd.debugsoul.pavone.service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/listings")
@RequiredArgsConstructor
@Slf4j
public class MarketplaceController {
    
    private final MarketplaceService marketplaceService;
    
    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestBody Map<String, Object> request) {
        UUID creditId = UUID.fromString((String) request.get("creditId"));
        String sellerWallet = (String) request.get("sellerWallet");
        BigDecimal price = new BigDecimal(request.get("price").toString());
        String currency = (String) request.get("currency");
        
        Listing listing = marketplaceService.createListing(creditId, sellerWallet, price, currency);
        return ResponseEntity.ok(listing);
    }
    
    @PostMapping("/{listingId}/buy")
    public ResponseEntity<Map<String, String>> buyListing(
            @PathVariable UUID listingId,
            @RequestBody Map<String, String> request) {
        
        String buyerWallet = request.get("buyerWallet");
        marketplaceService.buyListing(listingId, buyerWallet);
        
        return ResponseEntity.ok(Map.of("status", "success", "message", "Purchase completed"));
    }
    
    @GetMapping
    public ResponseEntity<List<Listing>> getActiveListings() {
        List<Listing> listings = marketplaceService.getActiveListings();
        return ResponseEntity.ok(listings);
    }
    
    @GetMapping("/seller/{wallet}")
    public ResponseEntity<List<Listing>> getListingsBySeller(@PathVariable String wallet) {
        List<Listing> listings = marketplaceService.getListingsBySeller(wallet);
        return ResponseEntity.ok(listings);
    }
}