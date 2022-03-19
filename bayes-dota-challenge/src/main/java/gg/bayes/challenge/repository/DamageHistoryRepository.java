package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.DamageHistoryEntity;
import gg.bayes.challenge.rest.model.HeroDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DamageHistoryRepository extends JpaRepository<DamageHistoryEntity, Long> {
    @Query(value = "SELECT new gg.bayes.challenge.rest.model.HeroDamage(d.heroName as target, count(d.heroName) as damageInstances, sum(damageCount) as totalDamage) from DamageHistoryEntity d where d.matchId=:matchId and d.damagedBy=:heroName group by d.heroName")
    List<HeroDamage> getHeroDamages(@Param("matchId") long matchId, @Param("heroName") String heroName);
}
