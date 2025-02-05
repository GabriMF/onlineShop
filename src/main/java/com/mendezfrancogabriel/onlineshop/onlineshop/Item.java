
package com.mendezfrancogabriel.onlineshop.onlineshop;

/**
 *
 * @author alu15d
 */
public class Item {
    
    private String itemId;
    private String description;
    private int stock;
    private double pvp;

    public Item(String itemId, String description, int stock, double pvp) {
        this.itemId = itemId;
        this.description = description;
        this.stock = stock;
        this.pvp = pvp;
    }

    public String getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPvp() {
        return pvp;
    }

    
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
}
