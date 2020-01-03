package digiPay.Controllers;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import digiPay.Exceptions.ModelNotFoundException;
import digiPay.Models.Cart;
import digiPay.Models.CartItem;
import digiPay.Models.Product;
import digiPay.Models.UserInfo;
import digiPay.Repositories.CartItemRepository;
import digiPay.Repositories.CartRepository;
import digiPay.Repositories.ProductRepository;
import digiPay.Repositories.UserInfoRepository;
import digiPay.Services.CalculateCartFinalPrice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carts")
public class CartController {
    private CartRepository cartRepository;
    private UserInfoRepository userInfoRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    public CartController(
            CartRepository cartRepository,
            UserInfoRepository userInfoRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository
    ) {
        this.cartRepository = cartRepository;
        this.userInfoRepository = userInfoRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public Map<String, Object> getCart(@AuthenticationPrincipal User user) {
        UserInfo userInfo = this.userInfoRepository.findByUsername(user.getUsername());

        Cart cart = this.cartRepository.findByUserAndIsCompletedFalse(userInfo);
        List<CartItem> cartItems = this.cartItemRepository.getCartItemsByCart(cart);

        HashMap<String, Object> response = new HashMap<>();

        response.put("cart", cart);
        response.put("cartItems", cartItems);
        response.put("finalPrice", (new CalculateCartFinalPrice(cart, cartItems)).calculate());

        return response;
    }

    @PostMapping("/")
    public Cart addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> body
    ) {
        UserInfo userInfo = this.userInfoRepository.findByUsername(user.getUsername());

        Cart cart = this.cartRepository.findByUserAndIsCompletedFalse(userInfo);

        if (cart == null) {
            cart = this.cartRepository.save(
                    new Cart(userInfo)
            );
        }

        Product product = this.productRepository.findById(Integer.parseInt(body.get("productId"))).orElseThrow(
                () -> new ModelNotFoundException("Product not found1")
        );

        CartItem cartItem = new CartItem(
                cart,
                product,
                Integer.parseInt(body.get("count"))
        );

        this.cartItemRepository.save(cartItem);

        return cart;
    }
}
