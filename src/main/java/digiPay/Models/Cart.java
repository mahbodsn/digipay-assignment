package digiPay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "carts")
public class Cart extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserInfo user;

    private Boolean isCompleted = false;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CartItem> cartItems;

    public Cart() {
    }

    public Cart(
            UserInfo user
    ) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }
}
