package com.quizduell.server.jsonRepresentation.turn;

import com.quizduell.server.domain.Answer;
import com.quizduell.server.domain.Player;
import com.quizduell.server.domain.Question;
import com.quizduell.server.domain.Turn;
import java.util.List;

/**
 *
 * @author Andre
 */
public class TurnJson {

    public List<Question> questionList;

    public Player firstPlayer;

    public Player secondPlayer;

    public List<Answer> firstPlayersAnswerList;

    public List<Answer> secondPlayersAnswerList;

    public static TurnJson fromTurn(final Turn turn) {
        TurnJson turnJson = new TurnJson();
        turnJson.questionList = turn.getQuestionList();
        turnJson.firstPlayer = turn.getFirstPlayer();
        turnJson.secondPlayer = turn.getSecondPlayer();
        turnJson.firstPlayersAnswerList = turn.getFirstPlayersAnswerList();
        turnJson.secondPlayersAnswerList = turn.getSecondPlayersAnswerList();
        return turnJson;
    }

}
