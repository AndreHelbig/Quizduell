package com.quizduell.server.domain;

import java.util.List;
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
public class Turn {
    private static final int MAX_QUESTIONS = 3;
    @Getter
    @Setter
    @Id
    private UUID uuid;

    @Getter
    @Setter
    @OneToOne
    private Category category;

    @Getter
    @Setter
    @OneToMany
    private List<Question> questionList;

    @Getter
    @Setter
    @OneToOne
    private Player firstPlayer;

    @Getter
    @Setter
    @OneToOne
    private Player secondPlayer;

    @Getter
    @Setter
    @OneToMany
    private List<Answer> firstPlayersAnswerList;

    @Getter
    @Setter
    @OneToMany
    private List<Answer> secondPlayersAnswerList;

    //for hibernate
    @Getter
    @Setter
    @ManyToOne
    private Duel duel;
}
