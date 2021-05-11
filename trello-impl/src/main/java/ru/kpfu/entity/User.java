package ru.kpfu.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"boards", "cards", "comments", "commentMentions"})
@ToString(exclude = {"boards", "cards", "comments", "commentMentions"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;

    public String email;

    @Column(name = "hash_password")
    public String hashPassword;

    @Enumerated(value = EnumType.STRING)
    public Role role;

    public enum Role {
        ADMIN,
        USER
    }

    @ManyToMany(cascade =  CascadeType.MERGE, mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Board> boards;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Card> cards;

    @OneToMany(mappedBy = "author")
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "userMentions", fetch = FetchType.LAZY)
    private Set<Comment> commentMentions;

    @Enumerated(value = EnumType.STRING)
    public Provider provider;

    public enum Provider {
        GOOGLE,
        TRELLO
    }
}
