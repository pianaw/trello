package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.CardColumn;

import javax.transaction.Transactional;
import java.util.List;

public interface CardColumnRepository extends JpaRepository<CardColumn, Long> {

    @Query(value = "FROM CardColumn c LEFT JOIN FETCH c.cards card WHERE c.board.id = :boardId AND c.isDeleted = FALSE AND card.status != 'ARCHIVE' ORDER BY c.orderId")
    List<CardColumn> getAllCardColumnByBoard_IdOrderByOrderId(Long boardId);

    @Query(value = "UPDATE CardColumn c SET c.orderId = :orderId WHERE c.id = :columnId")
    @Modifying
    @Transactional
    void replace(Long columnId, Long orderId);

    @Query("UPDATE CardColumn c SET c.isDeleted = true WHERE c.id = :id")
    @Modifying
    @Transactional
    void delete(Long id);
}
