package food.service;

import food.model.FoodOrder;

public class RegularOrder extends FoodOrder implements Discountable {

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return 80;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * 0.10;
    }
}
