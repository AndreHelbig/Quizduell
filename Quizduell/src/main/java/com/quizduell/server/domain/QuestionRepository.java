package com.quizduell.server.domain;

import java.util.List;

/**
 *
 * @author Andre
 */
public interface QuestionRepository {
    Question getQuestionById(long id);
    void persistQuestion(Question question);
    List<Question> getQuestions(int amount);
}
