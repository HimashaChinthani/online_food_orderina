package com.himasha.service;

import com.himasha.model.Order;
import com.himasha.model.User;
import com.himasha.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus)throws Exception;

    public  void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId)throws Exception;

    public List<Order> getResturantsOrder(Long resturantId,String orderStatus) throws  Exception;

    public  Order findOrderById(Long orderId) throws Exception;
}
