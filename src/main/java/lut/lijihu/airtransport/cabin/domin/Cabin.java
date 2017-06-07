package lut.lijihu.airtransport.cabin.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

/**
 * Created by kj on 2017/2/7.
 */
@Data
@Entity
@Table(name ="cabin")
public class Cabin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "assigned")
    @Column(name="id")
    private String id= System.currentTimeMillis()+"";
    @Column(name = "start")
    private String start;
    @Column(name = "end")
    private String end;
    @Column(name = "status")
    private String status="可用";
}
