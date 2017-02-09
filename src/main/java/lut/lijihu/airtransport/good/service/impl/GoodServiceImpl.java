package lut.lijihu.airtransport.good.service.impl;

import lut.lijihu.airtransport.good.domin.Good;
import lut.lijihu.airtransport.good.service.GoodService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kj on 2017/2/8.
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void addGood(Good good) {
        Session session=sessionFactory.openSession();
        Transaction tr=session.beginTransaction();
        session.save(good);
        tr.commit();
        session.close();

    }

    @Override
    public void deleteGood(Good good) {
        Session session=sessionFactory.openSession();
        Transaction tr=session.beginTransaction();
        session.delete(good);
        tr.commit();
        session.close();
    }

    @Override
    public void updateGood(Good good) {
        Session session=sessionFactory.openSession();
        Transaction tr=session.beginTransaction();
        session.update(good);
        tr.commit();
        session.close();
    }

    @Override
    public Good selectById(String id) {
        Session session=sessionFactory.openSession();
        Transaction tr=session.beginTransaction();
        Good good=session.find(Good.class,id);
        tr.commit();
        session.close();
        return good;
    }
}
