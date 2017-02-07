
import lut.lijihu.airtransport.configuration.DataSourceConfiguration;
import lut.lijihu.airtransport.configuration.DruidProperties;
import lut.lijihu.airtransport.configuration.HibernateConfiguration;
import lut.lijihu.airtransport.client.domin.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kj on 2017/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(AirTransportApplication.class)
@ContextConfiguration(classes = {HibernateConfiguration.class, DruidProperties.class, DataSourceConfiguration.class})
@TestPropertySource(properties = {
        "druid.datasource.password=cbpass",
        "druid.datasource.url=jdbc:mysql://localhost:3306/airtransport?characterEncoding=utf8",
        "druid.datasource.username=cbuser"
})
public class HibernateTest {
    @Autowired
    HibernateConfiguration hibernateConfiguration;
    @Autowired
    DruidProperties druidProperties;
    @Autowired
    DataSourceConfiguration dataSourceConfiguration;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    GenericApplicationContext applicationContext;
    @Test
   public void insertClient(){
        Client client=new Client();
        client.setAddress("11");
        client.setCode("12306");
        client.setTel("1555555555");
        client.setName("11");
        Session session=applicationContext.getBean("sessionFactory",SessionFactory.class).openSession();
        session.save(client);
        session.close();

    }


}
