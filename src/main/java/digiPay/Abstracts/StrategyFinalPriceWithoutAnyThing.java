package digiPay.Abstracts;

import digiPay.Interfaces.CalculateFinalPrice;
import digiPay.Models.Cart;
import digiPay.Models.CartItem;

import java.util.List;

abstract public class StrategyFinalPriceWithoutAnyThing implements CalculateFinalPrice {
    protected Cart cart;
    protected List<CartItem> cartItems;

    public StrategyFinalPriceWithoutAnyThing(
            Cart cart,
            List<CartItem> cartItems
    ) {
        this.cart = cart;
        this.cartItems = cartItems;
    }
}
