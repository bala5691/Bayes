package gg.bayes.challenge;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readString;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class DotaChallengeApplicationTests {

    private final Path root = Paths.get("data");
    private static long matchId = 0;

    @Autowired
    private MatchService matchService;

    @Test
    void contextLoads() throws Exception {
        Path file = this.root.resolve("combatlog_1.txt");
        String content = readString(file);
        matchId = matchService.ingestMatch(content);
        Assertions.assertEquals(1, matchId);
    }

    @Test
    void testKills() {
        List<HeroKills> heroKillsList = matchService.getHeroKills(matchId);
        Assertions.assertTrue(heroKillsList.size() > 0);
        HeroKills heroKills = heroKillsList.get(0);
        Assertions.assertEquals("abyssal_underlord", heroKills.getHero());
    }

    @Test
    void testItems() {
        List<HeroItems> heroItemsList = matchService.getHeroItems(matchId, "snapfire");
        Assertions.assertTrue(heroItemsList.size() > 0);
        HeroItems heroItems = heroItemsList.get(0);
        Assertions.assertEquals("clarity", heroItems.getItem());
    }

    @Test
    void testSpells() {
        List<HeroSpells> heroSpellsList = matchService.getHeroSpells(matchId, "snapfire");
        Assertions.assertTrue(heroSpellsList.size() > 0);
        HeroSpells heroSpells = heroSpellsList.get(0);
        Assertions.assertEquals("snapfire_firesnap_cookie", heroSpells.getSpell());
    }

    @Test
    void testDamage() {
        List<HeroDamage> heroDamageList = matchService.getHeroDamages(matchId, "snapfire");
        Assertions.assertTrue(heroDamageList.size() > 0);
        HeroDamage heroDamage = heroDamageList.get(0);
        Assertions.assertEquals("bane", heroDamage.getTarget());
    }
}
