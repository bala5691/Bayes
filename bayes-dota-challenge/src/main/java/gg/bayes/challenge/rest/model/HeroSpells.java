package gg.bayes.challenge.rest.model;

import lombok.Data;

@Data
public class HeroSpells {
    private String spell;
    private Integer casts;

    public HeroSpells(String spell, long casts) {
        this.spell= spell;
        this.casts = Math.toIntExact(casts);
    }
}
