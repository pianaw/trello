package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    public Long cardId;

    public String text;

    public String creationDate;

    public String authorName;

    public Long authorId;

    public Set<PublicUserDto> mentions;
}
