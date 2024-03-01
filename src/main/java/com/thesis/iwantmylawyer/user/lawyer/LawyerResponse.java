package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.address.AddressResponse;
import com.thesis.iwantmylawyer.article.ArticleResponse;
import com.thesis.iwantmylawyer.commonquestion.CommonQuestionResponse;
import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.city.CityResponse;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldResponse;

import java.util.List;

public record LawyerResponse(
    String email,
    String firstName,
    String lastName,
    String telephoneNo,
    String baroSicilNo,
    String lawyerPhoto,
    String aboutMe,
    String contactEmail,
    String contactTelNo,
    String contactTwitterUrl,
    String contactInstagramUrl,
    String contactFaceBookUrl,
    CityResponse cityResponse,
    AddressResponse addressResponse,
    List<ExpertiseFieldResponse> expertiseFieldResponseList,
    List<ArticleResponse> articleResponseList,
    List<CommonQuestionResponse> commonQuestionResponseList

) {
}
