package lut.lijihu.airtransport.client.service.impl;

import lut.lijihu.airtransport.client.domin.Client;
import lut.lijihu.airtransport.client.invo.ClientServiceSelectAllIn;
import lut.lijihu.airtransport.client.service.ClientService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kj on 2017/1/31.
 */
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    //@Transactional
    public Message delete(String[] ids) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        for(String id:ids){
            System.out.println(id);
            Client client=new Client();
            client.setId(id);
        session.delete(client);
        }
        transaction.commit();
        session.close();
        return new Message("删除成功");
    }

    @Override
    //@Transactional
    public Message update(Client client) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.update(client);
        session.flush();
        transaction.commit();
        session.close();
        return new Message("更新成功");
    }

    @Override
    //@Transactional
    public Message insert(Client client) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
        return new Message("插入成功");
    }

    @Override
    @Transactional
    public PageInfo<Client> selectAll(ClientServiceSelectAllIn clientServiceSelectAllIn) {
        int pageSize=clientServiceSelectAllIn.getPageSize();
        Session session=sessionFactory.openSession();
        String hql="from Client";
        Query query=session.createQuery(hql);
        List<Client> clients=query.list();
        query.setFirstResult(clientServiceSelectAllIn.getPageSize()*(clientServiceSelectAllIn.getPageNo()-1));
        query.setMaxResults(clientServiceSelectAllIn.getPageSize());
        List<Client> clients1=query.list();
        session.close();
        PageInfo<Client> pageInfo=new PageInfo<Client>(clients1);
        pageInfo.setTotal(clients1.size());
        pageInfo.setPageCount(clients.size()%pageSize==0?clients.size()/pageSize:clients.size()/pageSize+1);
        pageInfo.setPageSize(clientServiceSelectAllIn.getPageSize());
        pageInfo.setCurrentPage(clientServiceSelectAllIn.getPageNo());
        pageInfo.setSize(clients1.size());
        return pageInfo;
    }
}
