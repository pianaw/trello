package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "UPDATE Card c SET c.orderId = :orderId WHERE c.id = :cardId")
    @Modifying
    @Transactional
    void replace(Long cardId, Long orderId);

    @Query(value = "UPDATE Card c SET c.status = :status WHERE c.id = :cardId")
    @Modifying
    @Transactional
    void updateStatus(Long cardId, Card.Status status);
}
