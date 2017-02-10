package lut.lijihu.airtransport.track.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by kj on 2017/2/9.
 */
@Data
@Entity
@Table(name = "order_path")
public class OrderPath {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "path_id")
    private String pathId;
}
