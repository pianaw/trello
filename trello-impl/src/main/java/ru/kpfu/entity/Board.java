package ru.kpfu.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude="users")
@ToString(exclude = "users")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "board_participants",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "participant_id")}
    )
    private Set<User> users;

    @OneToMany(mappedBy = "board")
    private Set<CardColumn> columns;

}
