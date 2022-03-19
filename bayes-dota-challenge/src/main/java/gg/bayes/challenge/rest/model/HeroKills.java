package gg.bayes.challenge.rest.model;

import lombok.Data;

@Data
public class HeroKills {
    private String hero;
    private Integer kills;

    public HeroKills(String hero, long kills){
        this.hero = hero;
        this.kills = Math.toIntExact(kills);
    }

}
