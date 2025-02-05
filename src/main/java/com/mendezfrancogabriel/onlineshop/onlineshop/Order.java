
package com.mendezfrancogabriel.onlineshop.onlineshop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alu15d
 */
public class Order implements Serializable {
    
    private String orderId;
    private Customer customerOrder;
    private LocalDate ordersDate;
    private ArrayList <OrderLine> shoppingCart;
    
    public Order (String orderId, Customer customerOrder, LocalDate ordersDate, ArrayList <OrderLine> orderLine){
        
        this.orderId = orderId;
        this.customerOrder = customerOrder;
        this.ordersDate = ordersDate;
        this.shoppingCart= orderLine;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomerOrder() {
        return customerOrder;
    }

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public ArrayList<OrderLine> getShoppingCart() {
        return shoppingCart;
    }

    
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerOrder(Customer customerOrder) {
        this.customerOrder = customerOrder;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
    }

    public void setShoppingCart(ArrayList<OrderLine> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
