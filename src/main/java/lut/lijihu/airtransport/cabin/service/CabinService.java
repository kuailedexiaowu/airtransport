package lut.lijihu.airtransport.cabin.service;

import lut.lijihu.airtransport.cabin.domin.Cabin;
import lut.lijihu.airtransport.cabin.invo.CabinServiceSelectAllIn;
import lut.lijihu.airtransport.cabin.revo.CabinListIdVo;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;

import java.util.List;

/**
 * Created by kj on 2017/2/7.
 */
public interface CabinService {
    Message insertCabin(Cabin cabin);
    Message updateCabin(Cabin cabin);
    Message deleteCabin(String[] ids);
    PageInfo<Cabin> selectAll(CabinServiceSelectAllIn cabinServiceSelectAllIn);
    List<CabinListIdVo> listId();
}
