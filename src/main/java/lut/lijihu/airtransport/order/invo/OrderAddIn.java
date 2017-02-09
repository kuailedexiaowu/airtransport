package lut.lijihu.airtransport.order.invo;

import lombok.Data;

/**
 * Created by kj on 2017/2/8.
 */
@Data
public class OrderAddIn {
    private String sname;
    private String stel;
    private String scode;
    private String saddress;

    private String rname;
    private String rtel;
    private String rcode;
    private String raddress;

    private String type;
    private float weight;
    private float size;

    private String cabin_id;
    private String pay;
    private String status;


}
