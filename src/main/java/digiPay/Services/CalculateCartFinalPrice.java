package digiPay.Services;

import digiPay.Abstracts.StrategyFinalPriceWithoutAnyThing;
import digiPay.Interfaces.CalculateFinalPrice;
import digiPay.Models.Cart;
import digiPay.Models.CartItem;

import java.util.List;

public class CalculateCartFinalPrice extends StrategyFinalPriceWithoutAnyThing {
    public CalculateCartFinalPrice(Cart cart, List<CartItem> cartItems) {
        super(cart, cartItems);
    }

    public int calculate() {
        return this.getAllCartItemsValues();
    }

    private int getAllCartItemsValues() {
        int finalPrice = 0;
        for (CartItem cartItem : cartItems) {
            finalPrice += (cartItem.getProduct().getValue() * cartItem.getCount());
        }
        return finalPrice;
    }
}
