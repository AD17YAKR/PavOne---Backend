package cfd.debugsoul.pavone.repository;

import cfd.debugsoul.pavone.entity.Photo;
import cfd.debugsoul.pavone.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {
    List<Photo> findByTree(Tree tree);
    List<Photo> findByTreeOrderByUploadedAtDesc(Tree tree);
}