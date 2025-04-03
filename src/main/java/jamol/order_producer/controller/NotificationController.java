package jamol.order_producer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    @GetMapping("all")
    public ResponseEntity<String> getAllNotifications() {
        return ResponseEntity.ok("All notifications received");
    }


}
