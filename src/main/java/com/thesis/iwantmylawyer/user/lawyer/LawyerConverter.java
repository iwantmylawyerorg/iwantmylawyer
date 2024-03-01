package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.address.AddressConverter;
import com.thesis.iwantmylawyer.article.ArticleConverter;
import com.thesis.iwantmylawyer.city.CityConverter;
import com.thesis.iwantmylawyer.commonquestion.CommonQuestionConverter;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LawyerConverter {
    private final CityConverter cityConverter;
    private final AddressConverter addressConverter;
    private final ExpertiseFieldConverter expertiseFieldConverter;
    private final ArticleConverter articleConverter;
    private final CommonQuestionConverter commonQuestionConverter;

    public LawyerConverter(CityConverter cityConverter, AddressConverter addressConverter, ExpertiseFieldConverter expertiseFieldConverter, ArticleConverter articleConverter, CommonQuestionConverter commonQuestionConverter) {
        this.cityConverter = cityConverter;
        this.addressConverter = addressConverter;
        this.expertiseFieldConverter = expertiseFieldConverter;
        this.articleConverter = articleConverter;
        this.commonQuestionConverter = commonQuestionConverter;
    }

    public LawyerResponse convert(Lawyer from){
        return new LawyerResponse(
                from.getContactEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getTelephoneNo(),
                from.getBaroSicilNo(),
                from.getLawyerPhoto(),
                from.getAboutMe(),
                from.getContactEmail(),
                from.getContactTelNo(),
                from.getContactTwitterUrl(),
                from.getContactInstagramUrl(),
                from.getContactFaceBookUrl(),
                cityConverter.convert(from.getBaroKayitIl()),
                addressConverter.convert(from.getAddress()),
                expertiseFieldConverter.convert(from.getExpertiseFieldList()),
                articleConverter.convert(from.getArticleList()),
                commonQuestionConverter.convert(from.getCommonQuestionList())

        );
    }

    public Page<List<LawyerGetAllResponse>> getAllConvert(Page<List<Lawyer>> fromList){
        return fromList.map(lawyer -> new LawyerGetAllResponse(
                lawyer.getFirstName(),
                lawyer.getLastName(),
                lawyer.getTelephoneNo(),
                lawyer.getLawyerPhoto(),
                cityConverter.convert(lawyer.getBaroKayitIl()));
    }
}
