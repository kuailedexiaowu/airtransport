package lut.lijihu.airtransport.user.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kj on 2017/2/12.
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
