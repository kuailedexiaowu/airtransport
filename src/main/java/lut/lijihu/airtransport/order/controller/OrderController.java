package lut.lijihu.airtransport.order.controller;

import lut.lijihu.airtransport.client.domin.Client;
import lut.lijihu.airtransport.client.service.ClientService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.good.domin.Good;
import lut.lijihu.airtransport.good.service.GoodService;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.order.invo.OrderAddIn;
import lut.lijihu.airtransport.order.invo.OrderServiceSelectAllIn;
import lut.lijihu.airtransport.order.invo.OrderUpdateIn;
import lut.lijihu.airtransport.order.revo.OrderFindByIdVo;
import lut.lijihu.airtransport.order.revo.OrderListIdVo;
import lut.lijihu.airtransport.order.revo.OrderSelectAllVo;
import lut.lijihu.airtransport.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kj on 2017/2/8.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    GoodService goodService;
    @Autowired
    ClientService clientService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/addorder")
    public Message addOrder(@RequestBody OrderAddIn orderAddIn) {
        Good good = new Good();
            good.setSize(orderAddIn.getSize());
            good.setType(orderAddIn.getType());
            good.setWeight(orderAddIn.getWeight());
        goodService.addGood(good);
        Client sclient = new Client();
            sclient.setName(orderAddIn.getSname());
            sclient.setTel(orderAddIn.getStel());
            sclient.setAddress(orderAddIn.getSaddress());
            sclient.setCode(orderAddIn.getScode());
        clientService.insert(sclient);
        Client rclient = new Client();
            rclient.setAddress(orderAddIn.getRaddress());
            rclient.setCode(orderAddIn.getRcode());
            rclient.setName(orderAddIn.getRname());
            rclient.setTel(orderAddIn.getRtel());
        clientService.insert(rclient);
        Order order = new Order();
            order.setStatus(orderAddIn.getStatus());
            order.setCabin_id(orderAddIn.getCabin_id());
            order.setGood_id(good.getId());
            order.setReceiver_id(rclient.getId());
            order.setSend_id(sclient.getId());
            order.setPay(orderAddIn.getPay());
        return orderService.insertOrder(order);
    }

    @RequestMapping("/deleteorder")
    public Message deleteOrder(@RequestBody String[] ids) {
        return orderService.deleetOrder(ids);
    }

    @RequestMapping("/updateorder")
    public Message updateOrder(@RequestBody OrderUpdateIn orderUpdateIn) {
        return orderService.updateOrder(orderUpdateIn);
    }
    @RequestMapping("/selectall")
    public PageInfo<OrderSelectAllVo> selectAll(Integer pageNo, Integer pageSize){
        OrderServiceSelectAllIn orderServiceSelectAllIn=new OrderServiceSelectAllIn();
        orderServiceSelectAllIn.setPageNo(pageNo);
        orderServiceSelectAllIn.setPageSize(pageSize);
        return orderService.selectAll(orderServiceSelectAllIn);
    }
    @RequestMapping("/findbyid")
    public OrderFindByIdVo findById(String id){
       return orderService.findById(id);
    }

    @RequestMapping("/listid")
    public List<OrderListIdVo> listId(){
        return orderService.listId();
    }
}