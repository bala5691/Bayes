package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeroDamage {
    private String target;
    @JsonProperty("damage_instances")
    private Integer damageInstances;
    @JsonProperty("total_damage")
    private Integer totalDamage;

    public HeroDamage(String target, long damageInstances, long totalDamage) {
        this.target = target;
        this.damageInstances = Math.toIntExact(damageInstances);
        this.totalDamage = Math.toIntExact(totalDamage);
    }
}
