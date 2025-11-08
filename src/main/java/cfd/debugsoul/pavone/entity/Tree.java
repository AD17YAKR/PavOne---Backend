package cfd.debugsoul.pavone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "trees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "nft_id")
    private String nftId;

    private String species;

    @Column(name = "planted_date")
    private LocalDate plantedDate;

    private Double lat;
    private Double lon;

    @Column(name = "lat_lon_hash")
    private String latLonHash;

    @Column(name = "initial_height_mm")
    private Integer initialHeightMm;

    @Column(name = "initial_dbh_mm")
    private Integer initialDbhMm;

    @Column(columnDefinition = "TEXT DEFAULT 'active'")
    private String status = "active";

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}