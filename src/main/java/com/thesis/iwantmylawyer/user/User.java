package com.thesis.iwantmylawyer.user;

import com.thesis.iwantmylawyer.like.Like;
import com.thesis.iwantmylawyer.postcomment.PostComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String telephoneNo;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "user")
    private List<Like> likeList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "user")
    private List<PostComment> postCommentList;

    public User(String email, String password, String firstName, String lastName, String telephoneNo, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNo = telephoneNo;
        this.role = role;
    }

    public User(String email, String password, String firstName, String lastName, String telephoneNo) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNo = telephoneNo;
    }
}
