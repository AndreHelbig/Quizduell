package com.quizduell.server.resources;

import com.quizduell.server.jsonRepresentation.duel.DuelListJson;
import com.quizduell.server.jsonRepresentation.duel.CreateDuelJson;
import com.quizduell.server.jsonRepresentation.duel.DuelJson;
import com.quizduell.server.domain.Duel;
import com.quizduell.server.domain.Manager;
import com.quizduell.server.domain.Player;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Andre
 */
public class DuelResource extends ServerResource{
    
    private EntityManager manager = Manager.create();
    
    @Get
    public JacksonRepresentation index() {
        Query query = manager.createQuery("SELECT m FROM Duel m", Duel.class);
        List<Duel> duelList = query.getResultList();

        DuelListJson duelListJson = new DuelListJson();
        duelListJson.duels = new DuelJson[duelList.size()];

        for (int i = 0; i < duelList.size(); i++) {
            duelListJson.duels[i] = DuelJson.fromDuel(duelList.get(i));
        }

        return new JacksonRepresentation(duelListJson);
    }

    @Post("json")
    public void create(CreateDuelJson createDuelJson) {
        Player player1 = getOrCreatePlayer(createDuelJson.playerName1);
        Player player2 = getOrCreatePlayer(createDuelJson.playerName2);

        Duel duel = new Duel();
        duel.setPlayer1(player1);
        duel.setPlayer2(player2);

        manager.getTransaction().begin();
        manager.persist(duel);
        manager.getTransaction().commit();
    }

    private Player getOrCreatePlayer(String name) {
        try {
            return getPlayer(name);
        } catch (NoResultException e) {
            Player player = new Player();
            player.setName(name);
            manager.getTransaction().begin();
            manager.persist(player);
            manager.getTransaction().commit();
            return player;
        }
    }

    private Player getPlayer(String name) {
        Query query = manager.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
        query.setParameter("name", name);
        return (Player) query.getSingleResult();
    }
}
