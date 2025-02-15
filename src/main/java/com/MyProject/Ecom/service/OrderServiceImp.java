package com.MyProject.Ecom.service;


import com.MyProject.Ecom.dto.AnalyticsResponse;
import com.MyProject.Ecom.entity.Order;
import com.MyProject.Ecom.entity.OrderDto;
import com.MyProject.Ecom.entity.OrderStatus;
import com.MyProject.Ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

   public List<OrderDto> getAllPlaceOrder(){

        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));
        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());

    }

    public OrderDto changeOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (Objects.equals(status, "Shipped")) {
                order.setOrderStatus(OrderStatus.Shipped);
            } else if (Objects.equals(status, "Delivered")) {
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepository.save(order).getOrderDto();
        }
        return null;
    }

    public AnalyticsResponse calculateAnalytics(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);

        Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
    }

    public Long getTotalOrdersForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();




    }

}
