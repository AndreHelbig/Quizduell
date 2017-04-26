package com.quizduell.server.resources;

import com.quizduell.server.domain.Duel;
import com.quizduell.server.domain.Manager;
import com.quizduell.server.domain.Question;
import com.quizduell.server.domain.Answer;
import static com.quizduell.server.domain.Manager.manager;
import com.quizduell.server.domain.Turn;
import com.quizduell.server.jsonRepresentation.duel.DuelListJson;
import com.quizduell.server.jsonRepresentation.turn.CreateTurnJson;
import com.quizduell.server.jsonRepresentation.turn.TurnJson;
import com.quizduell.server.jsonRepresentation.turn.TurnListJson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Andre
 */
public class TurnResource extends ServerResource{
    
    private EntityManager entityManager = Manager.create();

    @Get
    public JacksonRepresentation index() {
        Query query = entityManager.createQuery("SELECT m FROM Turn m", Turn.class);
        List<Turn> turnList = query.getResultList();

        TurnListJson turnListJson = new TurnListJson();
        turnListJson.turns = new TurnJson[turnList.size()];

        for (int i = 0; i < turnList.size(); i++) {
            turnListJson.turns[i] = TurnJson.fromTurn(turnList.get(i));
        }

        return new JacksonRepresentation(turnListJson);
    }

    @Post("json")
    public void create(CreateTurnJson createTurnJson) {
        Query query = entityManager.createQuery("SELECT m FROM Duel m WHERE id = :uuid", Duel.class);
        query.setParameter("uuid", createTurnJson.duelUuid);
        Duel match = (Duel) query.getSingleResult();

        Turn turn = new Turn();
        turn.setDuel(match);
        turn.setFirstPlayer(match.getPlayer1());
        turn.setSecondPlayer(match.getPlayer2());

        entityManager.getTransaction().begin();
        entityManager.persist(turn);
        entityManager.getTransaction().commit();
        createQuestion(
                turn,
                "Wie viele Fragen sind in einer Runde von Quizduell?",
                "18",
                "16",
                "21",
                "15"
        );
        createQuestion(
                turn,
                "Wie viele Einwohner hat Karlsruhe?",
                "300000",
                "150000",
                "200000",
                "250000"
        );
        createQuestion(
                turn,
                "Welche Farben hat das DHBW Logo?",
                "Rot und grau",
                "Blau und grau",
                "Gelb und orange",
                "Grün und rot"
        );
    }

    private void createQuestion(Turn turn, String questionText, String rightAnswerText, String wrongAnswerText1, String wrongAnswerText2, String wrongAnswerText3) {
        Question question = new Question();
        question.setTurn(turn);
        question.setText(questionText);
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        createAnswer(question, rightAnswerText, true);
        createAnswer(question, wrongAnswerText1, false);
        createAnswer(question, wrongAnswerText2, false);
        createAnswer(question, wrongAnswerText3, false);
    }

    private Answer createAnswer(Question question, String text, boolean isRight) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setText(text);
        answer.setRight(isRight);
        entityManager.getTransaction().begin();
        entityManager.persist(answer);
        entityManager.getTransaction().commit();
        return answer;
    }
}
