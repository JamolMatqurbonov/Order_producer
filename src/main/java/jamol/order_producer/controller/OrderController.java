package jamol.order_producer.controller;

import jamol.order_producer.dto.OrderDto;
import jamol.order_producer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderproducer")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }


    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long id) {
        OrderDto orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtos);
    }
}
