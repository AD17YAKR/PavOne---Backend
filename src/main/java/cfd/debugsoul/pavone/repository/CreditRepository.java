package cfd.debugsoul.pavone.repository;

import cfd.debugsoul.pavone.entity.Credit;
import cfd.debugsoul.pavone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findByOwner(User owner);
    List<Credit> findByRetired(Boolean retired);
    
    @Query("SELECT SUM(c.totalGrams) FROM Credit c WHERE c.owner = :owner AND c.retired = false")
    Long getTotalActiveGramsByOwner(@Param("owner") User owner);
}