package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

import java.util.List;

public interface MatchService {
    Long ingestMatch(String payload);

    // TODO add more methods as needed
    List<HeroKills> getHeroKills(long matchId);
    List<HeroItems> getHeroItems(long matchId, String heroName);
    List<HeroSpells> getHeroSpells(long matchId, String heroName);
    List<HeroDamage> getHeroDamages(long matchId, String heroName);
}
