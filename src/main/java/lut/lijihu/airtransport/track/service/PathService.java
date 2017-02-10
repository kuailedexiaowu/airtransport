package lut.lijihu.airtransport.track.service;

import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.track.domin.Path;
import lut.lijihu.airtransport.track.invo.PathAddIn;
import lut.lijihu.airtransport.track.invo.PathAddServiceIn;
import lut.lijihu.airtransport.track.revo.ListPathsVo;

import java.util.List;

/**
 * Created by kj on 2017/2/9.
 */
public interface PathService {
    Message addPath(PathAddServiceIn pathAddServiceIn);
    Message updatePath(Path path);
    Path selectById(String id);
    List<Path> getPaths(String id);
    PageInfo<ListPathsVo> listPaths(Integer pageNo,Integer pageSize);
    Message deletePaths(String id);
}
