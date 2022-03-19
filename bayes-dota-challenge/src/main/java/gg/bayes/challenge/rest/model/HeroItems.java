package gg.bayes.challenge.rest.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class HeroItems {
    private String item;
    private Long timestamp;

    public HeroItems(String item, LocalDateTime timestamp) {
        this.item = item;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
