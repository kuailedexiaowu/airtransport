package lut.lijihu.airtransport.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kj on 2017/1/20.
 */
@Configuration
public class HibernateConfiguration {

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean localSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("lut.lijihu.airtransport.*.domin");
        localSessionFactoryBean.setHibernateProperties(this.hibernateproperties());
        /*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory= new MetadataSources(registry).buildMetadata().buildSessionFactory();*/
        return localSessionFactoryBean;
    }

/*    @Bean
    public JpaTransactionManager JpaTransactionManager(){
        JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
        jpaTransactionManager.setDataSource(this.dataSource);
        return jpaTransactionManager;
    }*/

    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(@Qualifier("dataSource") DataSource dataSource,@Qualifier("sessionFactory") SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
        hibernateTransactionManager.setDataSource(dataSource);
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return  hibernateTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties hibernateproperties(){
        return new Properties(){
            {
                setProperty("hibernate.show_sql","true");
                setProperty("hibernate.format_sql","true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                setProperty("hibernate.connecton.provider_class","com.alibaba.druid.support.hibernate.DruidConnectionProvider");
                //setProperty("packagesToScan","lut.lijihu.airtransport");
                setProperty("hibernate.hbm2ddl.auto","update");
                //setProperty("use_sql_comments","true");
                //setProperty("hibernate.connection.autocommit","true");
                //setProperty("hibernate.jdbc.batch_size","0");
            }
        };
    }
}
