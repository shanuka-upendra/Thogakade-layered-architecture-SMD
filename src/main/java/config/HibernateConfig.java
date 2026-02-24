package config;

import dto.CustomerDto;
import dto.ItemDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import service.CustomerBo;

public class HibernateConfig{
    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.configure("hibernate.cfg.xml");
        builder.build();

        Metadata metadata = new MetadataSources()
                .addAnnotatedClass(CustomerDto.class)
                .addAnnotatedClass(ItemDto.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession(){
        return session.openSession();
    }


}
