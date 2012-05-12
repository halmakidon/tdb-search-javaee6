package tdb.search.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ extends tdb.search.entity.BaseEntity_ {

	public static volatile SetAttribute<Company, Key> keys;
	public static volatile SingularAttribute<Company, String> address;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, String> code;
	public static volatile SingularAttribute<Company, String> type;

}

