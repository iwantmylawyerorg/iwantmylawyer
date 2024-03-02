package com.thesis.iwantmylawyer.postcomment;

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
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String text;

    private LocalDateTime localDateTime;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public PostComment(String text,LocalDateTime localDateTime, Post post, User user) {
        this.text = text;
        this.localDateTime = localDateTime;
        this.post = post;
        this.user = user;
    }
}
