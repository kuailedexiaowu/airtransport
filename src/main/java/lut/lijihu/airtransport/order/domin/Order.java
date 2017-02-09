package lut.lijihu.airtransport.order.domin;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by kj on 2017/2/8.
 */
@Data
@Entity
@Table(name = "air_order")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "send_id")
    private String send_id;
    @Column(name = "receiver_id")
    private String receiver_id;
    @Column(name = "good_id")
    private String good_id;
    @Column(name = "cabin_id")
    private String cabin_id;
    @Column(name = "pay")
    private String pay;
    @Column(name = "status")
    private String status;
    @Column(name = "create_time")
    private Timestamp create_time=new Timestamp(System.currentTimeMillis());
}
