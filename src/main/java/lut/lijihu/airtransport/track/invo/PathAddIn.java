package lut.lijihu.airtransport.track.invo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by kj on 2017/2/9.
 */
@Data
public class PathAddIn {
    private String orderId;
    private String name;
    private int sort;
    private Timestamp arrive_time;
}
