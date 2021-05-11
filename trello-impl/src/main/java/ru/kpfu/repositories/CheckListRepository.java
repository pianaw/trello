package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.CheckList;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    List<CheckList> getCheckListByCard_Id(Long cardId);
}
