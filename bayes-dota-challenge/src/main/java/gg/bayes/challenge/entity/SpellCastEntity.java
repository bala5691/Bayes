package gg.bayes.challenge.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="spell_cast_tbl")
public class SpellCastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="match_id", nullable = false)
    private long matchId;
    @Column(name="spell_name", nullable = false)
    private String spellName;
    @Column(name="hero_name", nullable = false)
    private String heroName;
    @Column(name="spell_used_by", nullable = false)
    private String spellUsedBy;
    @Column(name="spell_level", nullable = false)
    private int spellLevel;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    public SpellCastEntity(long matchId, String spellName, String heroName, String spellUsedBy, int spellLevel) {
        this.matchId = matchId;
        this.spellName = spellName;
        this.heroName = heroName;
        this.spellUsedBy = spellUsedBy;
        this.spellLevel = spellLevel;
        this.createdDate = LocalDateTime.now();
    }
}
