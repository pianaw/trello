package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUsers_id(Long users_id);


}
