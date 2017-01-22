package lut.lijihu.airtransport.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by kj on 2017/1/20.
 */
@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @Column(name="tel")
    private String tel;
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;
}

