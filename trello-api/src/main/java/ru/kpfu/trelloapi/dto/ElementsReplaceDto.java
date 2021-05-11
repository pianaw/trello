package ru.kpfu.trelloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElementsReplaceDto {

    public Long orderId1;
    public Long orderId2;
    public Long elemId1;
    public Long elemId2;
}
