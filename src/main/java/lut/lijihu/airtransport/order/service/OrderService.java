package lut.lijihu.airtransport.order.service;

import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import lut.lijihu.airtransport.order.domin.Order;
import lut.lijihu.airtransport.order.invo.OrderAddIn;
import lut.lijihu.airtransport.order.invo.OrderServiceSelectAllIn;
import lut.lijihu.airtransport.order.invo.OrderUpdateIn;
import lut.lijihu.airtransport.order.revo.OrderFindByIdVo;
import lut.lijihu.airtransport.order.revo.OrderListIdVo;
import lut.lijihu.airtransport.order.revo.OrderSelectAllVo;

import java.util.List;

/**
 * Created by kj on 2017/2/8.
 */
public interface OrderService {
    Message insertOrder(Order order);
    Message deleetOrder(String[] ids);
    Message updateOrder(OrderUpdateIn orderUpdateIn);
    OrderFindByIdVo findById(String id);
    PageInfo<OrderSelectAllVo> selectAll(OrderServiceSelectAllIn orderServiceSelectAllIn);
    List<OrderListIdVo> listId();
}
