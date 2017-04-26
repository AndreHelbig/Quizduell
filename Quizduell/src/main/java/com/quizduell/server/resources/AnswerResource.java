package com.quizduell.server.resources;

import com.quizduell.server.domain.Answer;
import com.quizduell.server.domain.Duel;
import com.quizduell.server.domain.Manager;
import com.quizduell.server.domain.Player;
import com.quizduell.server.domain.Turn;
import com.quizduell.server.jsonRepresentation.answer.AnswerValidationJson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Andre
 */
public class AnswerResource extends ServerResource{
    
    @Post
    public boolean createAnswerValidation(AnswerValidationJson jsonObject) {

        int turnIndex = jsonObject.turnNumber;

        Duel duel;
        Answer answer;
        Player player;

        String duelCondition = "'" + jsonObject.duelID + "'";
        String answerCondition = "'" + jsonObject.answerID + "'";
        String playerCondition = "'" + jsonObject.playerID + "'";

        EntityManager entityManager = Manager.create();

        entityManager.getTransaction().begin();

        try {
            duel = getDuel(entityManager, duelCondition);
            //if (duel == null) return false;
            answer = getAnswer(entityManager, answerCondition);
            //if (answer == null) return false;
            player = getPlayer(entityManager, playerCondition);
            //if (player == null) return false;
        } catch (NoResultException e) {
            return false;
        }
        List<Turn> turnList = duel.getTurns();

        if (player.getUuid().equals(jsonObject.playerID)) {
            turnList.get(turnIndex).getFirstPlayersAnswerList().add(answer);
        }

        if (player.getUuid().equals(jsonObject.playerID)) {
            turnList.get(turnIndex).getSecondPlayersAnswerList().add(answer);
        }

        entityManager.persist(turnList.get(turnIndex));
        entityManager.getTransaction().commit();

        return true;
    }

    private Duel getDuel(EntityManager entityManager, String uuid) {
        Query query = entityManager.createQuery("SELECT m FROM Duel m WHERE id = :uuid", Duel.class);
        query.setParameter("uuid", uuid);
        return (Duel) query.getSingleResult();
    }

    private Answer getAnswer(EntityManager entityManager, String uuid) {
        Query query = entityManager.createQuery("SELECT a FROM Answer a WHERE id = :uuid", Answer.class);
        query.setParameter("uuid", uuid);
        return (Answer) query.getSingleResult();
    }

    private Player getPlayer(EntityManager entityManager, String uuid) {
        Query query = entityManager.createQuery("SELECT p FROM Player p WHERE id = :uuid", Player.class);
        query.setParameter("uuid", uuid);
        return (Player) query.getSingleResult();
    }
}
