package lut.lijihu.airtransport.track.revo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by kj on 2017/2/9.
 */
@Data
public class ListPathsVo {
    private String orderId;
    private int passCount;
    private String cabinId;
    private String nowName;
    private Timestamp arriveTime;
}
