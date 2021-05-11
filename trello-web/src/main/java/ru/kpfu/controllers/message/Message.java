package ru.kpfu.controllers.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {

    private String text;

    private String authorName;

    private String authorId;

    @JsonCreator
    public Message(@JsonProperty("text")String text,
                   @JsonProperty("author_name")String authorName,
                   @JsonProperty("author_id")String authorId) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
