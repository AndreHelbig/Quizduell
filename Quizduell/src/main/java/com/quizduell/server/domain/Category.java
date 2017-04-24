package com.quizduell.server.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author Andre
 */

@Entity
@Indexed
public class Category {
    @Getter
    @Setter
    @Id
    private UUID uuid;
    
    @Getter
    @Setter
    @OneToOne
    private String name;
    
    @Getter
    @Setter
    @OneToMany
    private List<Question> questions;
}
