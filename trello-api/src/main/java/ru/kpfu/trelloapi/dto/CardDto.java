package ru.kpfu.trelloapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

    public Long columnId;

    public Long creatorId;

    public String title;

    public String description;

    public String deadline;

    public String status;

    public Long id;

    public Long orderId;
}
