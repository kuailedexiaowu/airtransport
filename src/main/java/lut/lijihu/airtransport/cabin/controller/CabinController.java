package lut.lijihu.airtransport.cabin.controller;

import lut.lijihu.airtransport.cabin.domin.Cabin;
import lut.lijihu.airtransport.cabin.invo.CabinAddIn;
import lut.lijihu.airtransport.cabin.invo.CabinServiceSelectAllIn;
import lut.lijihu.airtransport.cabin.invo.CabinUpdateIn;
import lut.lijihu.airtransport.cabin.service.CabinService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kj on 2017/2/7.
 */
@RestController
@RequestMapping("/cabin")
public class CabinController {
    @Autowired
    CabinService cabinService;

    @RequestMapping("/addcabin")
    public Message addCabin(@RequestBody CabinAddIn cabinAddIn){
        Cabin cabin=new Cabin();
        cabin.setStart(cabinAddIn.getStart());
        cabin.setEnd(cabinAddIn.getEnd());
        return cabinService.insertCabin(cabin);
    }
    @RequestMapping("/deletecabin")
    public Message deleteCabin(@RequestBody String[] ids){
        return cabinService.deleteCabin(ids);
    }
    @RequestMapping("/updatecabin")
    public Message updateCabin(@RequestBody CabinUpdateIn cabinUpdateIn){
        Cabin cabin=new Cabin();
        cabin.setId(cabinUpdateIn.getId());
        cabin.setStart(cabinUpdateIn.getStart());
        cabin.setEnd(cabinUpdateIn.getEnd());
        cabin.setStatus(cabinUpdateIn.getStatus());
        return cabinService.updateCabin(cabin);
    }
    @RequestMapping("/selectall")
    public PageInfo<Cabin> selectAll(Integer pageNo,Integer pageSize){
        CabinServiceSelectAllIn cabinServiceSelectAllIn=new CabinServiceSelectAllIn();
        cabinServiceSelectAllIn.setPageNo(pageNo);
        cabinServiceSelectAllIn.setPageSize(pageSize);
        return cabinService.selectAll(cabinServiceSelectAllIn);
    }
}
