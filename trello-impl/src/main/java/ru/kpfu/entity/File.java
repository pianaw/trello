package ru.kpfu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String type;

    public byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @NotNull
    private Card card;

}
