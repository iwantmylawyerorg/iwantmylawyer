package com.thesis.iwantmylawyer.commonquestion;

import com.thesis.iwantmylawyer.article.ArticleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonQuestionConverter {

    public List<CommonQuestionResponse> convert(List<CommonQuestion> fromList){
        return fromList.stream().map(commonQuestion -> new CommonQuestionResponse(
                commonQuestion.getId(),
                commonQuestion.getQuestionLine(),
                commonQuestion.getAnswerLine())
        ).toList();

    }

    public CommonQuestionResponse convert(CommonQuestion from){
        return new CommonQuestionResponse(
                from.getId(),
                from.getQuestionLine(),
                from.getAnswerLine()
        );
    }
}
