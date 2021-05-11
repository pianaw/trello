package ru.kpfu.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "check_list")
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String description;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @NotNull
    public Card card;

    @Column(name = "is_done")
    @NotNull
    public Boolean isDone;
}
