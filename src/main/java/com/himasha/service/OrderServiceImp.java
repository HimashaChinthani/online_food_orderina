package com.himasha.service;

import com.himasha.model.*;
import com.himasha.repository.*;
import com.himasha.request.OrderRequest;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired

    private ResturantRepository resturantRepository;
    @Autowired
    private ResturantService resturantService;


    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shippAddress=order.getDeliveryAddress();
        Address savedAddress=addressRepository.save(shippAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Resturant resturant=resturantService.findResturantById

                (order.getResturantId());

        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreateAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(savedAddress);
        createOrder.setResturant(resturant);


        Cart cart=cartService.findCartByUserId(user.getId());

        List<Orderitem> orderitems=new ArrayList<>();
 for(CartItem cartItem:cart.getItems()){
    Orderitem orderitem=new Orderitem();
    orderitem.setFood(cartItem.getFood());
    orderitem.setIngredients(cartItem.getIngredients());
    orderitem.setQuantity(cartItem.getQuantity());
    orderitem.setTotalPrice(cartItem.getTotalPrice());

    Orderitem saveOrderItem = orderItemRepository.save(orderitem);
    orderitems.add(saveOrderItem);
 }
 Long  totalPrice = cartService.calculateCartTotals(cart);

 createOrder.setItems(orderitems);
 createOrder.setTotalPrice(cart.getTotal());

 Order saveOrder=orderRepository.save(createOrder);
 resturant.getOrders().add(saveOrder);





        return createOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order=findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")||orderStatus.equals("DELIVERED")||orderStatus.equals("COMPLETED")||orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
      Order order=findOrderById(orderId);
      orderRepository.deleteOrderById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getResturantsOrder(Long resturantId, String orderStatus) throws Exception {
        List<Order> orders=orderRepository.findByResturantId(resturantId);
        if(orderStatus!=null){
            orders=orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found");
        }
        return null;
    }
}
