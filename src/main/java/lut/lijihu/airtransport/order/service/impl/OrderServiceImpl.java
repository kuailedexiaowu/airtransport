package lut.lijihu.airtransport.order.service.impl;

import lut.lijihu.airtransport.client.domin.Client;
import lut.lijihu.airtransport.client.service.ClientService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.good.domin.Good;
import lut.lijihu.airtransport.good.service.GoodService;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.order.invo.OrderServiceSelectAllIn;
import lut.lijihu.airtransport.order.invo.OrderUpdateIn;
import lut.lijihu.airtransport.order.revo.OrderFindByIdVo;
import lut.lijihu.airtransport.order.revo.OrderSelectAllVo;
import lut.lijihu.airtransport.order.service.OrderService;
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
 * Created by kj on 2017/2/8.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ClientService clientService;
    @Autowired
    GoodService goodService;
    @Override
    public Message insertOrder(Order order) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
        return new Message("插入订单成功");
    }

    @Override
    public Message deleetOrder(String[] ids) {

        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        for(String id:ids){
            Order order=session.find(Order.class,id);
            Good good=session.find(Good.class,order.getGood_id());
            session.delete(good);
            session.delete(order);
        }
        transaction.commit();
        session.close();
        return new Message("删除订单成功");
    }

    @Override
    public Message updateOrder(OrderUpdateIn orderUpdateIn) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        System.out.println(orderUpdateIn.getOrderId());
        OrderFindByIdVo orderFindByIdVo = this.findById(orderUpdateIn.getOrderId());
        Good good = goodService.selectById(orderFindByIdVo.getGood_id());
        good.setSize(orderUpdateIn.getSize());
        good.setType(orderUpdateIn.getType());
        good.setWeight(orderUpdateIn.getWeight());
        goodService.updateGood(good);
        Client sclient = clientService.findById(orderFindByIdVo.getSid());
        sclient.setName(orderUpdateIn.getSname());
        sclient.setTel(orderUpdateIn.getStel());
        sclient.setAddress(orderUpdateIn.getSaddress());
        sclient.setCode(orderUpdateIn.getScode());
        clientService.update(sclient);
        Client rclient = clientService.findById(orderFindByIdVo.getRid());
        rclient.setAddress(orderUpdateIn.getRaddress());
        rclient.setCode(orderUpdateIn.getRcode());
        rclient.setName(orderUpdateIn.getRname());
        rclient.setTel(orderUpdateIn.getRtel());
        clientService.update(rclient);
        Order order=new Order();
        order.setId(orderUpdateIn.getOrderId());
        order.setStatus(orderUpdateIn.getStatus());
        order.setCabin_id(orderUpdateIn.getCabin_id());
        order.setPay(orderUpdateIn.getPay());
        order.setSend_id(orderFindByIdVo.getSid());
        order.setReceiver_id(orderFindByIdVo.getRid());
        order.setCreate_time(orderFindByIdVo.getCreate_time());
        order.setGood_id(orderFindByIdVo.getGood_id());
        session.update(order);
        transaction.commit();
        session.close();
        return new Message("更新订单成功");
    }

    @Override
    @Transactional
    public PageInfo<OrderSelectAllVo> selectAll(OrderServiceSelectAllIn orderServiceSelectAllIn) {
        int pageSize=orderServiceSelectAllIn.getPageSize();
        Session session=sessionFactory.openSession();
        String hql="from lut.lijihu.airtransport.order.domin.Order";
        Query query=session.createQuery(hql);
        List<Order> orders=query.list();
        query.setFirstResult(orderServiceSelectAllIn.getPageSize()*(orderServiceSelectAllIn.getPageNo()-1));
        query.setMaxResults(orderServiceSelectAllIn.getPageSize());
        List<Order> orders1=query.list();
        session.close();
        List<OrderSelectAllVo> orderSelectAllVos=new ArrayList<OrderSelectAllVo>();
        for(int i=0;i<orders1.size();i++){
            OrderSelectAllVo orderSelectAllVo=new OrderSelectAllVo();
            orderSelectAllVo.setId(orders1.get(i).getId());
            orderSelectAllVo.setCabin_id(orders1.get(i).getCabin_id());
            orderSelectAllVo.setCreate_time(orders1.get(i).getCreate_time());
            orderSelectAllVo.setRname(clientService.findById(orders1.get(i).getReceiver_id()).getName());
            orderSelectAllVo.setSname(clientService.findById(orders1.get(i).getSend_id()).getName());
            orderSelectAllVo.setStatus(orders1.get(i).getStatus());
            orderSelectAllVos.add(orderSelectAllVo);
        }
        PageInfo<OrderSelectAllVo> pageInfo=new PageInfo<OrderSelectAllVo>(orderSelectAllVos);
        pageInfo.setTotal(orders.size());
        pageInfo.setPageCount(orders.size()%pageSize==0?orders.size()/pageSize:orders.size()/pageSize+1);
        pageInfo.setPageSize(orderServiceSelectAllIn.getPageSize());
        pageInfo.setCurrentPage(orderServiceSelectAllIn.getPageNo());
        pageInfo.setSize(orders1.size());
        return pageInfo;
    }

    @Override
    public OrderFindByIdVo findById(String id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Order order=session.find(Order.class,id);
        Client sclient=session.find(Client.class,order.getSend_id());
        Client rclient=session.find(Client.class,order.getReceiver_id());
        Good good=session.find(Good.class,order.getGood_id());
        transaction.commit();
        session.close();
        OrderFindByIdVo orderFindByIdVo=new OrderFindByIdVo();
            orderFindByIdVo.setGood_id(good.getId());
            orderFindByIdVo.setType(good.getType());
            orderFindByIdVo.setSize(good.getSize());
            orderFindByIdVo.setWeight(good.getWeight());
            orderFindByIdVo.setSid(sclient.getId());
            orderFindByIdVo.setSaddress(sclient.getAddress());
            orderFindByIdVo.setStel(sclient.getTel());
            orderFindByIdVo.setScode(sclient.getCode());
            orderFindByIdVo.setSname(sclient.getName());
            orderFindByIdVo.setRid(rclient.getId());
            orderFindByIdVo.setRaddress(rclient.getAddress());
            orderFindByIdVo.setRtel(rclient.getTel());
            orderFindByIdVo.setRcode(rclient.getCode());
            orderFindByIdVo.setRname(rclient.getName());
            orderFindByIdVo.setStatus(order.getStatus());
            orderFindByIdVo.setPay(order.getPay());
            orderFindByIdVo.setCabin_id(order.getCabin_id());
            orderFindByIdVo.setOrderId(order.getId());
            orderFindByIdVo.setCreate_time(order.getCreate_time());
        return orderFindByIdVo;
    }
}
