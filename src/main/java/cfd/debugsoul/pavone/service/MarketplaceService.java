package cfd.debugsoul.pavone.service;

import cfd.debugsoul.pavone.entity.Credit;
import cfd.debugsoul.pavone.entity.Listing;
import cfd.debugsoul.pavone.entity.User;
import cfd.debugsoul.pavone.repository.CreditRepository;
import cfd.debugsoul.pavone.repository.ListingRepository;
import cfd.debugsoul.pavone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketplaceService {
    
    private final ListingRepository listingRepository;
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public Listing createListing(UUID creditId, String sellerWallet, BigDecimal price, String currency) {
        Credit credit = creditRepository.findById(creditId)
            .orElseThrow(() -> new RuntimeException("Credit not found"));
        
        User seller = userRepository.findByWalletAddress(sellerWallet)
            .orElseThrow(() -> new RuntimeException("Seller not found"));
        
        if (!credit.getOwner().getId().equals(seller.getId())) {
            throw new RuntimeException("Only credit owner can create listing");
        }
        
        if (credit.getRetired()) {
            throw new RuntimeException("Cannot list retired credits");
        }
        
        Listing listing = new Listing();
        listing.setCredit(credit);
        listing.setSeller(seller);
        listing.setPriceAmount(price);
        listing.setPriceCurrency(currency);
        listing.setIsActive(true);
        
        log.info("Created listing for credit {} by seller {} at price {} {}", 
                creditId, sellerWallet, price, currency);
        
        return listingRepository.save(listing);
    }
    
    @Transactional
    public void buyListing(UUID listingId, String buyerWallet) {
        Listing listing = listingRepository.findById(listingId)
            .orElseThrow(() -> new RuntimeException("Listing not found"));
        
        if (!listing.getIsActive()) {
            throw new RuntimeException("Listing is not active");
        }
        
        User buyer = userRepository.findByWalletAddress(buyerWallet)
            .orElseThrow(() -> new RuntimeException("Buyer not found"));
        
        Credit credit = listing.getCredit();
        
        // Transfer ownership
        credit.setOwner(buyer);
        creditRepository.save(credit);
        
        // Deactivate listing
        listing.setIsActive(false);
        listingRepository.save(listing);
        
        log.info("Listing {} purchased by {} from {}", 
                listingId, buyerWallet, listing.getSeller().getWalletAddress());
    }
    
    public List<Listing> getActiveListings() {
        return listingRepository.findByIsActive(true);
    }
    
    public List<Listing> getListingsBySeller(String sellerWallet) {
        User seller = userRepository.findByWalletAddress(sellerWallet)
            .orElseThrow(() -> new RuntimeException("Seller not found"));
        
        return listingRepository.findBySeller(seller);
    }
}