package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.entity.*;
import gg.bayes.challenge.repository.*;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {
    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
    private static final String HERO = "npc_dota_hero_";
    private static final String OTHERS = "npc_dota_";
    private static final String UNKNOWN = "dota_unknown";
    private static final String ITEM = "item_";
    private static final String BUYS = "buys";
    private static final String CASTS = "casts";
    private static final String USES = "uses";
    private static final String HITS = "hits";
    private static final String KILL = "killed";

    @Autowired
    private DamageHistoryRepository damageRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ItemPurchaseRepository itemRepository;
    @Autowired
    private SpellHistoryRepository spellRepository;
    @Autowired
    private KilledRepository killedRepository;
    @Autowired
    public MatchServiceImpl() {
    }

    @Override
    public Long ingestMatch(String payload) {
        // TODO
        try {
            // Creating Match
            MatchEntity entity = matchRepository.save(new MatchEntity(UUID.randomUUID().toString()));
            long matchId = entity.getId();

            // Reading the events
            String lines[] = payload.split("\\r?\\n");
            Arrays.stream(lines).forEach(line -> {
                String[] eventArr = line.split("\\s+");
                String heroName = null;
                if (eventArr[1].startsWith(HERO)) {
                    heroName = eventArr[1].replace(HERO, "");
                } else if(eventArr[1].startsWith(OTHERS)) {
                    heroName = eventArr[1].replace(OTHERS, "");
                }
                if (heroName != null) {
                    if (line.contains(BUYS)) {
                        String itemName = null;
                        if (eventArr[4].startsWith(ITEM)) {
                            itemName = eventArr[4].replace(ITEM, "");
                        }
                        itemRepository.save(new ItemPurchaseEntity(matchId, heroName, itemName));
                    } else if (line.contains(CASTS)) {
                        int level = Integer.parseInt(eventArr[6].replace(")", ""));
                        String castName = getHeroName(eventArr[8]);
                        spellRepository.save(new SpellCastEntity(matchId, eventArr[4], castName, heroName, level));
                    } else if (line.contains(HITS)) {
                        String hitName = getHeroName(eventArr[3]);
                        damageRepository.save(new DamageHistoryEntity(matchId, hitName, heroName, eventArr[5], Long.parseLong(eventArr[7])));
                    } else if (line.contains(KILL)) {
                        String killName = getHeroName(eventArr[5]);
                        killedRepository.save(new KilledEntity(matchId, killName, heroName));
                    }
                }
            });
            return matchId;
        } catch (Exception e) {
            logger.error("ingest error -- " + e.getMessage());
        }
        return 0l;
        //throw new NotImplementedException("should be implemented by the applicant");
    }
    
    private String getHeroName(String name) {
        String heroName = null;
        if (name.startsWith(HERO)) {
            heroName = name.replace(HERO, "");
        } else if(name.startsWith(OTHERS)) {
            heroName = name.replace(OTHERS, "");
        } else if(name.equals(UNKNOWN)){
            heroName = name;
        }
        return heroName;
    }

    public List<HeroKills> getHeroKills(long matchId) {
        return killedRepository.getHeroKills(matchId);
    }

    @Override
    public List<HeroItems> getHeroItems(long matchId, String heroName) {
        return itemRepository.getHeroPurchasedItems(matchId, heroName);
    }

    @Override
    public List<HeroSpells> getHeroSpells(long matchId, String heroName) {
        return spellRepository.getHeroSpells(matchId, heroName);
    }

    @Override
    public List<HeroDamage> getHeroDamages(long matchId, String heroName) {
        return damageRepository.getHeroDamages(matchId, heroName);
    }
}
