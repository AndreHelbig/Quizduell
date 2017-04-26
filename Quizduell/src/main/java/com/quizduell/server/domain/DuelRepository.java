package com.quizduell.server.domain;

import java.util.List;

/**
 *
 * @author Andre
 */
public interface DuelRepository {
    Duel getDuelById(long id);
    List<Duel> getDuelsOfPlayer(Player player);
    boolean existsDuel(Player player1, Player player2);
    
    Duel getDuel(Player player1, Player player2);
    void persistDuel(Duel duel);
    
}
