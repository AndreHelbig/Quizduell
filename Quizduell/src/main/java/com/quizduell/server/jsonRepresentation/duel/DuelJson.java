package com.quizduell.server.jsonRepresentation.duel;

import com.quizduell.server.domain.Duel;
import com.quizduell.server.domain.Player;
import com.quizduell.server.domain.Turn;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Andre
 */
public class DuelJson {
    public UUID id;
    public Player player1;
    public Player player2;
    public List<Turn> turns;

    public static DuelJson fromDuel(final Duel duel) {
        DuelJson duelJson = new DuelJson();
        duelJson.id = duel.getUuid();
        duelJson.player1 = duel.getPlayer1();
        duelJson.player2 = duel.getPlayer2();
        duelJson.turns = duel.getTurns();
        return duelJson;
    }
}
