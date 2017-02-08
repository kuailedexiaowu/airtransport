package lut.lijihu.airtransport.order.service.impl;

import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.order.invo.OrderAddIn;
import lut.lijihu.airtransport.order.invo.OrderServiceSelectAllIn;
import lut.lijihu.airtransport.order.invo.OrderUpdateIn;
import lut.lijihu.airtransport.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Created by kj on 2017/2/8.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Message insertOrder(OrderAddIn orderAddIn) {
        return null;
    }

    @Override
    public Message deleetOrder(String[] ids) {
        return null;
    }

    @Override
    public Message updateOrder(OrderUpdateIn orderUpdateIn) {
        return null;
    }

    @Override
    public PageInfo<Order> selectAll(OrderServiceSelectAllIn orderServiceSelectAllIn) {
        return null;
    }
}
