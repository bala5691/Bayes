package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.KilledEntity;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KilledRepository extends JpaRepository<KilledEntity, Long> {
    @Query(value = "SELECT new gg.bayes.challenge.rest.model.HeroKills(k.killedBy as hero, count(k.heroName) as kills) from KilledEntity k where k.matchId=:matchId group by k.killedBy")
    List<HeroKills> getHeroKills(@Param("matchId") long matchId);
}
