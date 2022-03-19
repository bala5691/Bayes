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
@Table(name="killed_tbl")
public class KilledEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="match_id", nullable = false)
    private long matchId;
    @Column(name="hero_name", nullable = false)
    private String heroName;
    @Column(name="killed_by", nullable = false)
    private String killedBy;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    public KilledEntity(long matchId, String heroName, String killedBy) {
        this.matchId = matchId;
        this.heroName = heroName;
        this.killedBy = killedBy;
        this.createdDate = LocalDateTime.now();
    }
}
