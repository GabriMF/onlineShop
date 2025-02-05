
package com.mendezfrancogabriel.onlineshop.onlineshop;

import java.io.Serializable;

/**
 *
 * @author alu15d
 */
public class OrderLine implements Serializable {
    
    private String itemId;
    private int amount;

    public OrderLine(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
    

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
