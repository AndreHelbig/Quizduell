
package com.quizduell.server.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author Andre
 */

@Entity
@Indexed
public class Player implements Serializable {
    @Getter
    @Setter
    @Id
    private UUID uuid;

    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private int matchesWon;

    @Getter
    @Setter
    private int matchesLost;

    @Getter
    @Setter
    private int totalMatches;

    //for hibernate
    @Getter
    @Setter
    @ManyToOne
    private Duel duelPlayer1;

    @Getter
    @Setter
    @ManyToOne
    private Duel duelPlayer2;

    public Player() {
        this.uuid = UUID.randomUUID();
    }
    
}
