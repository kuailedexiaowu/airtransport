package lut.lijihu.airtransport.order.revo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by kj on 2017/2/8.
 */
@Data
public class OrderSelectAllVo {
    private String id;
    private String sname;
    private String rname;
    private String status;
    private String cabin_id;
    private Timestamp create_time;
}
