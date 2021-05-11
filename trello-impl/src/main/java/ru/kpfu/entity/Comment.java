package ru.kpfu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @NotNull
    private Card card;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "comment_mention",
            joinColumns = {@JoinColumn(name = "comment_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> userMentions;
}
