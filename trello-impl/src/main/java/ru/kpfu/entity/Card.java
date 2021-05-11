package ru.kpfu.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"users", "comments", "checkLists", "files"})
@ToString(exclude = {"users", "comments", "checkLists", "files"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String description;

    @CreationTimestamp
    public Date deadline;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "card_participants",
            joinColumns = {@JoinColumn(name = "card_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "participant_id")}
    )
    public Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "column_id")
    public CardColumn column;

    @OneToMany(mappedBy = "card")
    public Set<Comment> comments;

    @Enumerated(value = EnumType.STRING)
    public Status status;

    @OneToMany(mappedBy = "card")
    public Set<CheckList> checkLists;

    @Column(name = "order_id")
    private Long orderId;

    public enum Status {
        DONE,
        PROCESS,
        EXPIRED,
        ARCHIVE,
        WAITING
    }

    @OneToMany(mappedBy = "card")
    public Set<File> files;

}
