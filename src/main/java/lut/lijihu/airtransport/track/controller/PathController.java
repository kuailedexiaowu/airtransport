package lut.lijihu.airtransport.track.controller;

import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.track.domin.Path;
import lut.lijihu.airtransport.track.invo.PathAddIn;
import lut.lijihu.airtransport.track.invo.PathAddServiceIn;
import lut.lijihu.airtransport.track.revo.ListPathsVo;
import lut.lijihu.airtransport.track.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kj on 2017/2/9.
 */
@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    PathService pathService;

    @RequestMapping("/addpath")
    public Message addPath(@RequestBody PathAddIn pathAddIn){
        PathAddServiceIn pathAddServiceIn=new PathAddServiceIn();
        pathAddServiceIn.setOrderId(pathAddIn.getOrderId());
        pathAddServiceIn.setArrive_time(pathAddIn.getArrive_time());
        pathAddServiceIn.setName(pathAddIn.getName());
        pathAddServiceIn.setSort(pathAddIn.getSort());
        return pathService.addPath(pathAddServiceIn);
    }

    @RequestMapping("/getpaths")
    public List<Path> getPaths(String id){
        return pathService.getPaths(id);
    }

    @RequestMapping("/listpaths")
    public PageInfo<ListPathsVo> listPaths(Integer pageNo,Integer pageSize){
       return pathService.listPaths(pageNo,pageSize);
    }
    @RequestMapping("/deletepaths")
    public Message deletePaths(String id){
        return pathService.deletePaths(id);
    }
}
