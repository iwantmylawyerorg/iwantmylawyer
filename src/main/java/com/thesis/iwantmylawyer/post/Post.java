package com.thesis.iwantmylawyer.post;

import com.thesis.iwantmylawyer.like.Like;
import com.thesis.iwantmylawyer.postcomment.PostComment;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        private String text;

        private String postPhoto;

        private LocalDateTime localDateTime;

        @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
        @JoinColumn
        private Lawyer lawyer;

        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "post")
        private List<Like> likeList;

        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "post")
        private List<PostComment> postCommentList;

        public Post(String text,LocalDateTime localDateTime, Lawyer lawyer) {
                this.text = text;
                this.localDateTime = localDateTime;
                this.lawyer = lawyer;
        }
}
