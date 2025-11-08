package cfd.debugsoul.pavone.repository;

import cfd.debugsoul.pavone.entity.Listing;
import cfd.debugsoul.pavone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {
    List<Listing> findByIsActive(Boolean isActive);
    List<Listing> findBySeller(User seller);
    List<Listing> findBySellerAndIsActive(User seller, Boolean isActive);
}