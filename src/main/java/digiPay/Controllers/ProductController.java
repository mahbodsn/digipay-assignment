package digiPay.Controllers;

import digiPay.Models.Product;
import digiPay.Repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    final private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping("/")
    public Product create(@RequestBody Map<String, String> body) {
        return productRepository.save(
                new Product(
                        body.get("title"),
                        body.get("description"),
                        Integer.parseInt(body.get("value")),
                        Integer.parseInt(body.get("count"))
                )
        );
    }
}
