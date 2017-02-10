package lut.lijihu.airtransport.track.service.impl;

import lut.lijihu.airtransport.cabin.domin.Cabin;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.track.domin.OrderPath;
import lut.lijihu.airtransport.track.domin.Path;
import lut.lijihu.airtransport.track.invo.PathAddServiceIn;
import lut.lijihu.airtransport.track.revo.ListPathsVo;
import lut.lijihu.airtransport.track.service.PathService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kj on 2017/2/9.
 */
@Service
public class PathServiceImpl implements PathService {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Message addPath(PathAddServiceIn pathAddServiceIn) {
        Path path=new Path();
            path.setName(pathAddServiceIn.getName());
            path.setArrive_time(pathAddServiceIn.getArrive_time());
            path.setSort(pathAddServiceIn.getSort());
        OrderPath orderPath=new OrderPath();
            orderPath.setOrderId(pathAddServiceIn.getOrderId());
            orderPath.setPathId(path.getId());
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(path);
        session.save(orderPath);
        transaction.commit();
        session.close();
        return new Message("插入站点信息成功");
    }

    @Override
    public Message addPaths(String id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Order order=session.find(Order.class,id);
        Cabin cabin=session.find(Cabin.class,order.getCabin_id());
        Path path=new Path();
        path.setSort(1);
        path.setName(cabin.getStart());
        OrderPath orderPath=new OrderPath();
        orderPath.setOrderId(id);
        orderPath.setPathId(path.getId());
        session.save(path);
        session.save(orderPath);
        transaction.commit();
        session.close();
        return new Message("创建跟踪信息成功");
    }

    @Override
    public Message updatePath(Path path) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.update(path);
        transaction.commit();
        session.close();
        return new Message("更新站点信息成功");
    }

    @Override
    public Path selectById(String id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Path path=session.find(Path.class,id);
        transaction.commit();
        session.close();
        return path;
    }

    @Override
    @Transactional
    public List<Path> getPaths(String id) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from OrderPath op where op.orderId=:id");
        query.setParameter("id",id);
        List<OrderPath> orderPathList=query.list();
        List<Path> paths=new ArrayList<Path>();
        for(int i=0;i<orderPathList.size();i++){
            Path path=selectById(orderPathList.get(i).getPathId());
            paths.add(path);
        }
        session.close();
        return paths;
    }

    @Override
    public PageInfo<ListPathsVo> listPaths(Integer pageNo,Integer pageSize) {
        Session session=sessionFactory.openSession();
        String hql="select distinct o from lut.lijihu.airtransport.order.domin.Order o,OrderPath op where o.id=op.orderId";
        Query query=session.createQuery(hql);
        List<Order> orders=query.list();
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        List<Order> orders1=query.list();
        List<ListPathsVo> listPathsVos=new ArrayList<ListPathsVo>();
        for(int i=0;i<orders1.size();i++){
           List<Path> paths=this.getPaths(orders1.get(i).getId());
            ListPathsVo listPathsVo=new ListPathsVo();
            listPathsVo.setOrderId(orders1.get(i).getId());
            listPathsVo.setPassCount(paths.size());
            listPathsVo.setNowName(paths.get(listPathsVo.getPassCount()-1).getName());
            listPathsVo.setArriveTime(paths.get(listPathsVo.getPassCount()-1).getArrive_time());
            listPathsVo.setCabinId(orders1.get(i).getCabin_id());
             listPathsVos.add(listPathsVo);
        }
        session.close();
        PageInfo<ListPathsVo> pageInfo=new PageInfo<ListPathsVo>(listPathsVos);
        pageInfo.setTotal(orders.size());
        pageInfo.setPageCount(orders.size()%pageSize==0?orders.size()/pageSize:orders.size()/pageSize+1);
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(pageNo);
        pageInfo.setSize(orders1.size());
        return pageInfo;
    }

    @Override
    public Message deletePaths(String id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        String hql="from OrderPath op where op.orderId=:orderId ";
        Query query=session.createQuery(hql);
        query.setParameter("orderId",id);
        List<OrderPath> orderPathList=query.list();
        for(int i=0;i<orderPathList.size();i++){
            Path path=session.find(Path.class,orderPathList.get(i).getPathId());
            System.out.println(path.toString()+orderPathList.get(i).toString());
            session.delete(path);
            session.delete(orderPathList.get(i));
        }
        transaction.commit();
        session.close();
        return  new Message("删除跟踪信息成功");
    }
}
