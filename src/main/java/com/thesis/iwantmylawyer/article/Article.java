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

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Lawyer lawyer;
}
