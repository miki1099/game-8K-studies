package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Score.class)
public abstract class Score_ {

	public static volatile SingularAttribute<Score, LocalDate> date;
	public static volatile SingularAttribute<Score, Integer> score;
	public static volatile SingularAttribute<Score, Integer> id;
	public static volatile SingularAttribute<Score, User> user;

	public static final String DATE = "date";
	public static final String SCORE = "score";
	public static final String ID = "id";
	public static final String USER = "user";

}

