package digiPay.Controllers;

import digiPay.Exceptions.ModelNotFoundException;
import digiPay.Models.Order;
import digiPay.Repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    final private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Order getOne(@PathVariable String id) throws ModelNotFoundException {
        return orderRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new ModelNotFoundException("Order Not Found"));
    }

    @PostMapping("/finalize/{id}")
    public Order finalize(@PathVariable String id)
    {
        Integer productId = Integer.parseInt(id);

        return orderRepository.findById(productId).orElseThrow(
                () -> new ModelNotFoundException("Not Found")
        );
    }
}
