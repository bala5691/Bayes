package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.ItemPurchaseEntity;
import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchaseEntity, Long> {
    @Query(value = "SELECT new gg.bayes.challenge.rest.model.HeroItems(i.itemName as item, i.createdDate as timestamp) from ItemPurchaseEntity i where i.matchId=:matchId and i.heroName=:heroName")
    List<HeroItems> getHeroPurchasedItems(@Param("matchId") long matchId, @Param("heroName") String heroName);
}
