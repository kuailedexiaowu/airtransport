package lut.lijihu.airtransport.good.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by kj on 2017/2/8.
 */
@Data
@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "id")
    private String id=UUID.randomUUID().toString();
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private float size;
    @Column(name = "weight")
    private float weight;
}
