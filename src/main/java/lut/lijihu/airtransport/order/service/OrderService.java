package lut.lijihu.airtransport.order.service;

import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.order.invo.OrderAddIn;
import lut.lijihu.airtransport.order.invo.OrderServiceSelectAllIn;
import lut.lijihu.airtransport.order.invo.OrderUpdateIn;

/**
 * Created by kj on 2017/2/8.
 */
public interface OrderService {
    Message insertOrder(OrderAddIn orderAddIn);
    Message deleetOrder(String[] ids);
    Message updateOrder(OrderUpdateIn orderUpdateIn);
    PageInfo<Order> selectAll(OrderServiceSelectAllIn orderServiceSelectAllIn);
}
