package com.thesis.iwantmylawyer.commonquestion;

import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import com.thesis.iwantmylawyer.user.lawyer.LawyerService;
import org.springframework.stereotype.Service;



@Service
public class CommonQuestionService {

    private final CommonQuestionRepository commonQuestionRepository;
    private final LawyerService lawyerService;

    public CommonQuestionService(CommonQuestionRepository commonQuestionRepository, LawyerService lawyerService) {
        this.commonQuestionRepository = commonQuestionRepository;
        this.lawyerService = lawyerService;
    }

    public void create(CreateCommonQuestionRequest request){
        Lawyer lawyer = lawyerService.findById(request.lawyerId());
        CommonQuestion commonQuestion = new CommonQuestion(request.questionLine(), request.answerLine(),lawyer);
        commonQuestionRepository.save(commonQuestion);
    }
    public void delete(String id){
        commonQuestionRepository.delete(findById(id));
    }
    public CommonQuestion findById(String id){
        return commonQuestionRepository.findById(id)
                .orElseThrow(()-> new CommonQuestionNotFoundException(Constant.COMMON_QUESTION_DOES_NOT_FOUND_EXCEPTION));
    }
}
