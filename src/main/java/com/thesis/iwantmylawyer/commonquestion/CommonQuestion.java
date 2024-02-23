package com.thesis.iwantmylawyer.commonquestion;

import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String questionLine;
    private String answerLine;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Lawyer lawyer;
}
