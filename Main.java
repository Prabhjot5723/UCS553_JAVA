package food.main;

import food.model.FoodOrder;
import food.service.Discountable;
import food.service.PremiumOrder;
import food.service.RegularOrder;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {

        FoodOrder.setRestaurantName("FoodExpress");

        // At least six orders stored in a FoodOrder array
        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "Rahul", 500);
        orders[1] = new PremiumOrder(102, "Aman", 1000);
        orders[2] = new RegularOrder(103, "Priya", 750);
        orders[3] = new PremiumOrder(104, "Neha", 1200);
        orders[4] = new RegularOrder(105, "Rohit", 600);
        orders[5] = new PremiumOrder(106, "Simran", 900);

        System.out.println("========================================");
        System.out.println("       ONLINE FOOD DELIVERY SYSTEM");
        System.out.println("       Restaurant: " + FoodOrder.getRestaurantName());
        System.out.println("========================================");

        for (FoodOrder order : orders) {

            if (!OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid customer name for Order ID: " + order.getOrderId());
                continue;
            }

            if (!OrderUtility.validateAmount(order.getAmount())) {
                System.out.println("Invalid amount for Order ID: " + order.getOrderId());
                continue;
            }

            double discount = 0;

            if (order instanceof Discountable) {
                discount = ((Discountable) order).applyDiscount();
            }

            double deliveryCharge = order.calculateDeliveryCharge();
            double finalPayable = order.getAmount() - discount + deliveryCharge;

            System.out.println("----------------------------------------");
            System.out.println("Order ID      : " + order.getOrderId());
            System.out.println("Customer Name : " + order.getCustomerName());
            System.out.println("Bill Amount   : Rs. " + order.getAmount());
            System.out.println("Discount      : Rs. " + discount);
            System.out.println("Delivery      : Rs. " + deliveryCharge);
            System.out.println("Final Payable : Rs. " + finalPayable);
            System.out.println("----------------------------------------");
        }

        System.out.println();
        System.out.println("========== COMPLETE ORDER SUMMARIES ==========");

        for (FoodOrder order : orders) {
            OrderUtility.generateOrderSummary(order);
        }

        System.out.println();
        System.out.println("========================================");
        FoodOrder.displayTotalOrders();
        System.out.println("========================================");
    }
}
