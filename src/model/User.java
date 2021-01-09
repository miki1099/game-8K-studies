package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Data model for user uses hibernate annotations
 * @author Michal Glodek
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {

    /** User id auto generated*/
    @Id
    @Column(name = "USR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** User name*/
    @Column(name = "USR_NAME")
    private String name;

    /**
     * Creates new user
     * @param name User name
     */
    public User(String name) {
        this.name = name;
    }
}
