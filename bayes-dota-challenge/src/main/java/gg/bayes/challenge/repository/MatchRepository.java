package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
}
