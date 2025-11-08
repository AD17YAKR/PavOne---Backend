package cfd.debugsoul.pavone.service;

import cfd.debugsoul.pavone.entity.Credit;
import cfd.debugsoul.pavone.entity.Tree;
import cfd.debugsoul.pavone.entity.User;
import cfd.debugsoul.pavone.repository.CreditRepository;
import cfd.debugsoul.pavone.repository.TreeRepository;
import cfd.debugsoul.pavone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditService {
    
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    
    @Transactional
    public Credit mintCredit(UUID treeId, String ownerWallet, Long grams, UUID reportId) {
        Tree tree = treeRepository.findById(treeId)
            .orElseThrow(() -> new RuntimeException("Tree not found"));
        
        User owner = userRepository.findByWalletAddress(ownerWallet)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Credit credit = new Credit();
        credit.setTree(tree);
        credit.setOwner(owner);
        credit.setTotalGrams(grams);
        credit.setRetired(false);
        
        log.info("Minting {} grams of carbon credits for tree {} to owner {}", 
                grams, treeId, ownerWallet);
        
        return creditRepository.save(credit);
    }
    
    @Transactional
    public Credit retireCredit(UUID creditId, String retiredBy) {
        Credit credit = creditRepository.findById(creditId)
            .orElseThrow(() -> new RuntimeException("Credit not found"));
        
        if (credit.getRetired()) {
            throw new RuntimeException("Credit already retired");
        }
        
        credit.setRetired(true);
        log.info("Credit {} retired by {}", creditId, retiredBy);
        
        return creditRepository.save(credit);
    }
    
    public Long getBalance(String walletAddress) {
        User user = userRepository.findByWalletAddress(walletAddress)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Long balance = creditRepository.getTotalActiveGramsByOwner(user);
        return balance != null ? balance : 0L;
    }
    
    public List<Credit> getCreditsByOwner(String walletAddress) {
        User user = userRepository.findByWalletAddress(walletAddress)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        return creditRepository.findByOwner(user);
    }
}