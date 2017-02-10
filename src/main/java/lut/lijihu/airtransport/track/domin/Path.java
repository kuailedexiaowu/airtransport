package lut.lijihu.airtransport.track.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by kj on 2017/2/9.
 */
@Data
@Entity
@Table(name = "path")
public class Path {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @Column(name = "sort")
    private int sort;
    @Column(name = "arrive_time")
    private Timestamp arrive_time;
}
