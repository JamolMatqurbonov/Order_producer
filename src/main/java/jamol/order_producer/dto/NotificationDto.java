package jamol.order_producer.dto;

import java.time.LocalDateTime;

public record NotificationDto(
        Long id,
        String message,
        LocalDateTime createdAt
) {
}
