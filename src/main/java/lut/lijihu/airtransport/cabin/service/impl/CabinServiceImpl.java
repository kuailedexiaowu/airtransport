package lut.lijihu.airtransport.cabin.service.impl;

import lut.lijihu.airtransport.cabin.domin.Cabin;
import lut.lijihu.airtransport.cabin.invo.CabinServiceSelectAllIn;
import lut.lijihu.airtransport.cabin.revo.CabinListIdVo;
import lut.lijihu.airtransport.cabin.service.CabinService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
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
 * Created by kj on 2017/2/7.
 */
@Service
public class CabinServiceImpl implements CabinService {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Message insertCabin(Cabin cabin) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(cabin);
        transaction.commit();
        session.close();
        return new Message("插入订舱成功");
    }

    @Override
    public Message updateCabin(Cabin cabin) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.update(cabin);
        transaction.commit();
        session.close();
        return new Message("更新订舱信息成功");
    }

    @Override
    public Message deleteCabin(String[] ids) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        for(String id:ids){
           Cabin cabin=new Cabin();
            cabin.setId(id);
            session.delete(cabin);
        }
        transaction.commit();
        session.close();
        return new Message("删除订舱成功");
    }

    @Override
    @Transactional
    public PageInfo<Cabin> selectAll(CabinServiceSelectAllIn cabinServiceSelectAllIn) {
        int pageSize=cabinServiceSelectAllIn.getPageSize();
        Session session=sessionFactory.openSession();
        String hql="from Cabin";
        Query query=session.createQuery(hql);
        List<Cabin> cabins=query.list();
        query.setFirstResult(cabinServiceSelectAllIn.getPageSize()*(cabinServiceSelectAllIn.getPageNo()-1));
        query.setMaxResults(cabinServiceSelectAllIn.getPageSize());
        List<Cabin> cabins1=query.list();
        session.close();
        PageInfo<Cabin> pageInfo=new PageInfo<Cabin>(cabins1);
        pageInfo.setTotal(cabins1.size());
        pageInfo.setPageCount(cabins.size()%pageSize==0?cabins.size()/pageSize:cabins.size()/pageSize+1);
        pageInfo.setPageSize(cabinServiceSelectAllIn.getPageSize());
        pageInfo.setCurrentPage(cabinServiceSelectAllIn.getPageNo());
        pageInfo.setSize(cabins1.size());
        return pageInfo;
    }

    @Override
    @Transactional
    public List<CabinListIdVo> listId() {
        Session session=sessionFactory.openSession();
        String hql="from Cabin c where c.status='可用'";
        Query query=session.createQuery(hql);
        List<Cabin> cabins=query.list();
        List<CabinListIdVo> cabinListIdVos=new ArrayList<CabinListIdVo>();
        for(int i=0;i<cabins.size();i++){
            CabinListIdVo cabinListIdVo=new CabinListIdVo();
            cabinListIdVo.setId(cabins.get(i).getId());
            cabinListIdVos.add(cabinListIdVo);
        }
        return cabinListIdVos;
    }
}
