package com.MyProject.Ecom.repository;

import com.MyProject.Ecom.entity.Order;
import com.MyProject.Ecom.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "SELECT * FROM orders o WHERE o.user_id = 18 AND o.order_status = 1", nativeQuery = true)
    Order findByUser_IdAndOrderStatus (Long userId, OrderStatus orderStatus);

   // Order findById(Long userId);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findByUser_IdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);


}
