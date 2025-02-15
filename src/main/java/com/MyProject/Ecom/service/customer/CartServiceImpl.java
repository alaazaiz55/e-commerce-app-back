package com.MyProject.Ecom.service.customer;

import com.MyProject.Ecom.dto.AddProductInCartDto;
import com.MyProject.Ecom.dto.PlaceOrderDto;
import com.MyProject.Ecom.entity.*;
import com.MyProject.Ecom.exceptions.ValidationException;
import com.MyProject.Ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
@Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  CartItemsRepository cartItemsRepository;
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  CouponRepository couponRepository;


    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto, Long id) {
        try {

            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            User user = userOptional.get();

            System.out.println("User ID: " + addProductInCartDto.getId());


            Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(addProductInCartDto.getId(), OrderStatus.Pending);
           System.out.println("User ID: " + activeOrder.getId());
            System.out.println("Order Status: " + OrderStatus.Pending);




            System.out.println("yhtttttttt");

            //Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(

                 //   addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getId());

            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());




            Product product = optionalProduct.get();
            CartItems cart = new CartItems();
            cart.setProduct(product);
            cart.setPrice(product.getPrice());
            cart.setQuantity(1L);
            cart.setUser(user);
            cart.setOrder(activeOrder);
            System.out.println("rrfrrrrrr");
            CartItems savedCart = cartItemsRepository.save(cart);

           activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
            activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
            activeOrder.getCartItems().add(savedCart);
            System.out.println("rrfrrrrrr");
            orderRepository.save(activeOrder);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);

        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    public OrderDto getCartByUserId(Long id) {

        Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(id, OrderStatus.Pending);
        System.out.println("tdtujfykgkug");

      //  System.out.println("User ID: " + activeOrder );
        // Check if the activeOrder is null



        // Map cart items to DTOs
       List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems()
                .stream()
                .map(CartItems::getCartDto)
                .collect(Collectors.toList());

        // Create the OrderDto and set its fields
        OrderDto ord = new OrderDto();
        ord.setId(activeOrder.getId());
        ord.setAmount(activeOrder.getAmount());
        ord.setOrderStatus(activeOrder.getOrderStatus());
        ord.setDiscount(activeOrder.getDiscount());
        ord.setTotalAmount(activeOrder.getTotalAmount());
        ord.setCartItems(cartItemsDtoList);

        // Optionally set the coupon name if it exists
        if (activeOrder.getCoupon() != null) {
            ord.setCouponName(activeOrder.getCoupon().getName());
        }

        System.out.println("Order retrieved successfully for user ID: " + id);
        return ord;
    }


    public OrderDto applyCoupon (Long id , String code){
        Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(id, OrderStatus.Pending);
        System.out.println("User ID: " + id);
        System.out.println("Code: " + code);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found"));


        if(couponIsExpired(coupon)){
            throw new ValidationException("coupon has expired");
        }
        double discountPercent = Double.parseDouble(coupon.getDiscount());
        double discountAmount = ((discountPercent / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;

        activeOrder.setAmount((long)netAmount);
        activeOrder.setDiscount((long)discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponIsExpired(Coupon coupon) {
        Date currentdate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return expirationDate != null && currentdate.after(expirationDate);
    }


    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto, Long id) {
        try {
            // Validate user existence
            Optional<User> userOptional = userRepository.findById(id);

            User user = userOptional.get();

            // Fetch the active order
            Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(id, OrderStatus.Pending);


            // Fetch the product
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());

            Product product = optionalProduct.get();

            // Fetch the cart items
            Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                    addProductInCartDto.getProductId(), activeOrder.getId(), id
            );

            CartItems cartItems = optionalCartItems.get();

            // Update the cart items and order amounts
            cartItems.setQuantity(cartItems.getQuantity() + 1);
            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

            // Apply discount if available
            if (activeOrder.getCoupon() != null) {
                double discountPercent = Double.parseDouble(activeOrder.getCoupon().getDiscount());
                double discountAmount = (discountPercent / 100.0) * activeOrder.getTotalAmount();
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            // Save the updated entities
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);

            // Return the updated order DTO
            return activeOrder.getOrderDto();

        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return null;

        }
    }


    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto, Long id) {
        try {
            // Validate user existence
            Optional<User> userOptional = userRepository.findById(id);

            User user = userOptional.get();

            // Fetch the active order
            Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(id, OrderStatus.Pending);


            // Fetch the product
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());

            Product product = optionalProduct.get();

            // Fetch the cart items
            Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                    addProductInCartDto.getProductId(), activeOrder.getId(), id
            );

            CartItems cartItems = optionalCartItems.get();

            // Update the cart items and order amounts
            cartItems.setQuantity(cartItems.getQuantity() - 1);
            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            // Apply discount if available
            if (activeOrder.getCoupon() != null) {
                double discountPercent = Double.parseDouble(activeOrder.getCoupon().getDiscount());
                double discountAmount = (discountPercent / 100.0) * activeOrder.getTotalAmount();
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            // Save the updated entities
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);

            // Return the updated order DTO
            return activeOrder.getOrderDto();

        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return null;

        }
    }


    public OrderDto placeOrder(PlaceOrderDto placeOrderDto, Long id){

        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.get();

        // Fetch the active order
        Order activeOrder = orderRepository.findByUser_IdAndOrderStatus(id, OrderStatus.Pending);

        System.out.println("User ID: " + id);
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
            activeOrder.setAdress(placeOrderDto.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());
            orderRepository.save(activeOrder);

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        System.out.println("User ID: " );
        order.setUser(userOptional.get());
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);
        return activeOrder.getOrderDto();



    }

    public List<OrderDto> getMyPlacedOrder(Long userId){
       return orderRepository.findByUser_IdAndOrderStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Delivered,
               OrderStatus.Shipped)).stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
}



