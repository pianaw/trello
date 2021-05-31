package ru.kpfu.repositories;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.Board;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.User;

import javax.persistence.Lob;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "FROM User u LEFT JOIN FETCH u.boards board WHERE board.id = :boardId AND u.id = :userId")
    User isParticipantBoard(Long userId, Long boardId);

    @Query(value = "FROM User u JOIN FETCH u.cards cards WHERE cards.id = :id")
    List<User> findCardParticipantsByCard_Id(Long id);

    Optional<User> findByRedisId(String redisId);
}
