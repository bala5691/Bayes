package gg.bayes.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="damage_history_tbl")
public class DamageHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="match_id", nullable = false)
    private long matchId;
    @Column(name="hero_name", nullable = false)
    private String heroName;
    @Column(name="damaged_by", nullable = false)
    private String damagedBy;
    @Column(name="spell_name", nullable = false)
    private String spellName;
    @Column(name="damage_count", nullable = false)
    private long damageCount;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    public DamageHistoryEntity(long matchId, String heroName, String damagedBy, String spellName, long damageCount) {
        this.matchId = matchId;
        this.heroName = heroName;
        this.damagedBy = damagedBy;
        this.spellName = spellName;
        this.damageCount = damageCount;
        this.createdDate = LocalDateTime.now();
    }
}
