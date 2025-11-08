package cfd.debugsoul.pavone.repository;

import cfd.debugsoul.pavone.entity.Report;
import cfd.debugsoul.pavone.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findByTree(Tree tree);
    Optional<Report> findTopByTreeOrderByGeneratedAtDesc(Tree tree);
}