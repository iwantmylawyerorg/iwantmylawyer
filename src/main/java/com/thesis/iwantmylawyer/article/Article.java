package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String header;
    @Column(columnDefinition = "text")
    private String text;
    private LocalDateTime localDateTime;
    private String photo;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Lawyer lawyer;

    public Article(String header, String text, LocalDateTime localDateTime, Lawyer lawyer) {
        this.header = header;
        this.text = text;
        this.localDateTime = localDateTime;
        this.lawyer = lawyer;
    }

    public Article(String header, String text, LocalDateTime localDateTime, String photo, Lawyer lawyer) {
        this.header = header;
        this.text = text;
        this.localDateTime = localDateTime;
        this.photo = photo;
        this.lawyer = lawyer;
    }
}
