package cfd.debugsoul.pavone.repository;

import cfd.debugsoul.pavone.entity.Tree;
import cfd.debugsoul.pavone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreeRepository extends JpaRepository<Tree, UUID> {
    List<Tree> findByOwner(User owner);
    List<Tree> findByStatus(String status);
}