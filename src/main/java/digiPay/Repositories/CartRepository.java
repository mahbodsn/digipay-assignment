package digiPay.Repositories;

import digiPay.Models.Cart;
import digiPay.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserAndIsCompletedFalse(UserInfo user);

}
