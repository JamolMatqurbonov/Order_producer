package jamol.order_producer.dto;

import lombok.Builder;

@Builder
public record OrderDto(
        Long id,
        String productName,
        double price
) {
}
