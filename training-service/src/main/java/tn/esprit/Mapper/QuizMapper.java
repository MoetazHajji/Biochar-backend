package tn.esprit.Mapper;

import tn.esprit.Dto.QuizDto;

import tn.esprit.Entity.Quiz;




public class QuizMapper {

    public static Quiz mapToEntity(QuizDto quizDto){

        Quiz quiz = Quiz.builder()
                .id(quizDto.getId_quiz())
                .question(quizDto.getQuestion())
                .answer_1(quizDto.getAnswer_1())
                .answer_2(quizDto.getAnswer_2())
                .answer_3(quizDto.getAnswer_3())
                .answer_4(quizDto.getAnswer_4())
                .valid_answer(quizDto.getValid())
                .type_q(quizDto.getType())
                .build();
        return quiz;
    }

    public static QuizDto mapToDto(Quiz quiz){
        QuizDto quizDto = QuizDto.builder()
                .id_quiz(quiz.getId())
                .question(quiz.getQuestion())
                .answer_1(quiz.getAnswer_1())
                .answer_2(quiz.getAnswer_2())
                .answer_3(quiz.getAnswer_3())
                .answer_4(quiz.getAnswer_4())
                .valid(quiz.getValid_answer())
                .type(quiz.getType_q())
                .build();
        return quizDto;
    }
}
