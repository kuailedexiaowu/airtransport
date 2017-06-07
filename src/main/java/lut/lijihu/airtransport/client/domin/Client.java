package lut.lijihu.airtransport.client.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by kj on 2017/1/20.
 */
@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "id")
    private String id=System.currentTimeMillis()+"";
    @Column(name = "name")
    private String name;
    @Column(name="tel")
    private String tel;
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;


}

