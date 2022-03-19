package gg.bayes.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="item_purchase_tbl")
public class ItemPurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name="match_id", nullable = false)
    private long matchId;
    @NotNull
    @Column(name="hero_name", nullable = false)
    private String heroName;
    @NotNull
    @Column(name="item_name", nullable = false)
    private String itemName;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    public ItemPurchaseEntity(long matchId, String heroName, String itemName) {
        this.matchId = matchId;
        this.heroName = heroName;
        this.itemName = itemName;
        this.createdDate = LocalDateTime.now();
    }
}
