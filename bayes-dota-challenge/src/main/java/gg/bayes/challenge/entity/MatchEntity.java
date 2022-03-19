package gg.bayes.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="match_tbl")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name="match_name", nullable = false)
    private String matchName;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public MatchEntity(String matchName) {
        this.matchName = matchName;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = this.createdDate;
    }
}
