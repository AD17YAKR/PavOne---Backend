package cfd.debugsoul.pavone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id")
    private Tree tree;

    @Column(name = "walrus_cid", nullable = false)
    private String walrusCid;

    @Column(name = "ai_model")
    private String aiModel;

    @Column(name = "health_status")
    private String healthStatus;

    @Column(name = "growth_estimate_percent")
    private BigDecimal growthEstimatePercent;

    @Column(name = "detected_forgery")
    private Boolean detectedForgery = false;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "report_json", columnDefinition = "jsonb")
    private Map<String, Object> reportJson;

    @CreationTimestamp
    @Column(name = "generated_at")
    private LocalDateTime generatedAt;
}