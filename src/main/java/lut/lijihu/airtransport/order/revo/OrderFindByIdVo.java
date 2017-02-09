package lut.lijihu.airtransport.order.revo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by kj on 2017/2/8.
 */
@Data
public class OrderFindByIdVo {
    private String orderId;

    private String sid;
    private String sname;
    private String stel;
    private String scode;
    private String saddress;

    private String rid;
    private String rname;
    private String rtel;
    private String rcode;
    private String raddress;

    private String good_id;
    private String type;
    private float weight;
    private float size;

    private String cabin_id;
    private String pay;
    private String status;
    private Timestamp create_time;
}
