package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.SpellCastEntity;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpellHistoryRepository extends JpaRepository<SpellCastEntity, Long> {
    @Query(value = "SELECT new gg.bayes.challenge.rest.model.HeroSpells(s.spellName as spell, count(s.spellName) as casts) from SpellCastEntity s where s.matchId=:matchId and s.spellUsedBy=:heroName group by s.spellName")
    List<HeroSpells> getHeroSpells(@Param("matchId") long matchId, @Param("heroName") String heroName);
}
