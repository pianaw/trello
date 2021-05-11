package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByCard_Id(Long cardId);
}
