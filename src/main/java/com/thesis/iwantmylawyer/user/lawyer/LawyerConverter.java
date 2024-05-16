package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.address.AddressConverter;
import com.thesis.iwantmylawyer.article.ArticleConverter;
import com.thesis.iwantmylawyer.city.CityConverter;
import com.thesis.iwantmylawyer.commonquestion.CommonQuestionConverter;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldConverter;
import com.thesis.iwantmylawyer.minio.MinioService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class LawyerConverter {
    private final CityConverter cityConverter;
    private final AddressConverter addressConverter;
    private final ExpertiseFieldConverter expertiseFieldConverter;
    private final ArticleConverter articleConverter;
    private final CommonQuestionConverter commonQuestionConverter;
    private final MinioService minioService;

    public LawyerConverter(CityConverter cityConverter, AddressConverter addressConverter, ExpertiseFieldConverter expertiseFieldConverter, ArticleConverter articleConverter, CommonQuestionConverter commonQuestionConverter, MinioService minioService) {
        this.cityConverter = cityConverter;
        this.addressConverter = addressConverter;
        this.expertiseFieldConverter = expertiseFieldConverter;
        this.articleConverter = articleConverter;
        this.commonQuestionConverter = commonQuestionConverter;
        this.minioService = minioService;
    }

    public LawyerResponse convert(Lawyer from) {
        return new LawyerResponse(
                from.getEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getTelephoneNo(),
                from.getBaroSicilNo(),
                minioService.getBase64Image(from.getLawyerPhoto()),
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
    public UnconfirmedLawyerResponse unconfirmedConvert(Lawyer from){
        return new UnconfirmedLawyerResponse(
                from.getFirstName(),
                from.getLastName(),
                from.getBaroSicilNo(),
                from.getTcNo(),
                minioService.getBase64Image(from.getLawyerPhoto()),
                minioService.getBase64Image(from.getAvukatKartPhoto()),
                minioService.getBase64Image(from.getTcPhoto()),
                addressConverter.convert(from.getAddress())
        );
    }

    public Page<LawyerGetAllResponse> getAllConvert(Page<Lawyer> fromList) {
        return fromList.map(lawyer -> new LawyerGetAllResponse(
                lawyer.getId(),
                lawyer.getFirstName(),
                lawyer.getLastName(),
                lawyer.getContactTelNo(),
                minioService.getBase64Image(lawyer.getLawyerPhoto()),
                cityConverter.convert(lawyer.getBaroKayitIl())));
    }
}
