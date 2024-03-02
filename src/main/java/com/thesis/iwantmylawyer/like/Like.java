package com.thesis.iwantmylawyer.like;

import com.thesis.iwantmylawyer.post.Post;
import com.thesis.iwantmylawyer.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public Like(Post post,LocalDateTime localDateTime, User user) {
        this.post = post;
        this.localDateTime = localDateTime;
        this.user = user;
    }
}
