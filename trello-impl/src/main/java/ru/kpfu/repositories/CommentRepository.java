package ru.kpfu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT comment, comment.author.id, comment.author.name, comment.author.email, mentions.id, mentions.name, mentions.email FROM Comment comment LEFT JOIN comment.author LEFT JOIN FETCH comment.userMentions mentions WHERE comment.card.id = :cardId")
    List<Comment> getCommentByCard_Id(Long cardId);
}
