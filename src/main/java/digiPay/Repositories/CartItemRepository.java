package digiPay.Repositories;

import digiPay.Models.Cart;
import digiPay.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> getCartItemsByCart(Cart cart);
}
