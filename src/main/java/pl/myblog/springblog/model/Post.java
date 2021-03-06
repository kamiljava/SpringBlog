package pl.myblog.springblog.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Type(type = "text")
    private String content;
    @Enumerated
    private PostCategory category;
    private LocalDateTime date_added = LocalDateTime.now();
    //fetchtype.lazy dane zostana odczytane z bazy dopiero w momencie gdy nastapi odwolanie do obiektu
    //fetchtype.eager pobierz w momencie wykonywania zapytania
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public Post(@NotNull String title, @NotNull String content, PostCategory category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
}

   // public String getUser(){
     //   return user.getName();
    //}
    }

