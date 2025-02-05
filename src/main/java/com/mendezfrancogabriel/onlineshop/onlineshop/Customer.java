
package com.mendezfrancogabriel.onlineshop.onlineshop;

/**
 *
 * @author alu15d
 */
public class Customer {
    
    private String dni;
    private String name;
    private String phone;
    private String email;

    public Customer(String dni, String name, String phone, String email) {
        this.dni = dni;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
