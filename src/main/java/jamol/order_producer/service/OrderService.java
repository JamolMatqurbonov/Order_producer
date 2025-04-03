package jamol.order_producer.service;


import jamol.order_producer.dto.OrderDto;
import jamol.order_producer.entity.Order;
import jamol.order_producer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String ORDER_TOPIC = "order-topic";


    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setProductName(orderDto.productName());;
        order.setPrice(orderDto.price());
        order = orderRepository.save(order);

        String message = "New order created: " + order.getId();
        kafkaTemplate.send(ORDER_TOPIC, message); // Kafka ga yuborish

        orderDto.id();
        return orderDto;
    }
    @Cacheable(value = "orders", key = "#id")
    public OrderDto getOrder(Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderDto.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .price(order.getPrice())
                .build();
    }
    @Cacheable(value = "orders", key = "#id")
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);


    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(s -> {
                    return OrderDto.builder()
                            .id(s.getId())
                            .productName(s.getProductName())
                            .price(s.getPrice())
                            .build();
                })
                .collect(Collectors.toList());
    }






}




