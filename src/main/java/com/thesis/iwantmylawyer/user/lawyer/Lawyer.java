package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.address.Address;
import com.thesis.iwantmylawyer.article.Article;
import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.commonquestion.CommonQuestion;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseField;
import com.thesis.iwantmylawyer.user.Role;
import com.thesis.iwantmylawyer.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Lawyer extends User {

    private String tcNo;
    private String baroSicilNo;
    private String tcPhoto;
    private String avukatKartPhoto;
    private String lawyerPhoto;
    private boolean isApproved = false;
    private String aboutMe;
    private String contactEmail;
    private String contactTelNo;
    private String contactTwitterUrl;
    private String contactInstagramUrl;
    private String contactFaceBookUrl;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "lawyer")
    private Address address;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private City baroKayitIl;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn,inverseJoinColumns = @JoinColumn)
    private List<ExpertiseField> expertiseFieldList;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "lawyer")
    private List<Article> articleList;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "lawyer")
    private List<CommonQuestion> commonQuestionList;

    public Lawyer(String email, String password, String firstName, String lastName, String telephoneNo, String tcNo, String baroSicilNo, City baroKayitIl) {
        super(email, password, firstName, lastName, telephoneNo);
        this.tcNo = tcNo;
        this.baroSicilNo = baroSicilNo;
        this.baroKayitIl = baroKayitIl;
    }
}
