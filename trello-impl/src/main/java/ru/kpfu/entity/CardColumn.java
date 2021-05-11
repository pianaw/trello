package ru.kpfu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "card_column")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"board", "cards"})
@ToString(exclude = {"board", "cards"})
public class CardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "column")
    @OrderBy("orderId")
    public List<Card> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @NotNull
    public Board board;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "is_deleted")
    public Boolean isDeleted;

}
