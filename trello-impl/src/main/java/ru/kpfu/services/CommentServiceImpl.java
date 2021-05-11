package ru.kpfu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.entity.Card;
import ru.kpfu.entity.Comment;
import ru.kpfu.entity.User;
import ru.kpfu.repositories.CommentRepository;
import ru.kpfu.trelloapi.dto.CommentDto;
import ru.kpfu.trelloapi.dto.PublicUserDto;
import ru.kpfu.trelloapi.services.CommentService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CommentDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentRepository.save(
                Comment.builder()
                    .author(User.builder()
                            .id(commentDto.getAuthorId())
                            .build())
                    .card(Card.builder()
                            .id(commentDto.cardId)
                            .build())
                    .text(commentDto.text)
                .build()
        );

        return CommentDto.builder()
                .creationDate(comment.getCreationDate().toString())
                .text(comment.getText())
                .build();
    }

    @Override
    public Boolean delete(CommentDto commentDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentByCardId(Long cardId) {
        return commentRepository.getCommentByCard_Id(cardId).stream()
                .map(x -> CommentDto.builder()
                        .text(x.getText())
                        .creationDate(x.getCreationDate().toString())
                        .authorName(x.getAuthor().name)
                        .authorId(x.getAuthor().id)
                        .mentions(x.getUserMentions().stream()
                            .map(y -> PublicUserDto.builder()
                                    .id(y.id)
                                    .name(y.name)
                                    .email(y.email)
                                    .build())
                            .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toList());
    }
}
