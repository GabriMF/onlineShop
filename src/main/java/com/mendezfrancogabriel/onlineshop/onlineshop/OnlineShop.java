
package com.mendezfrancogabriel.onlineshop.onlineshop;

import com.mendezfrancogabriel.onlineshop.onlineshop.exceptions.EmptyStock;
import com.mendezfrancogabriel.onlineshop.onlineshop.exceptions.InsufficientStock;
import com.mendezfrancogabriel.onlineshop.onlineshop.utilities.Utilities;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alu15d
 */
public class OnlineShop implements Serializable{
    
    Scanner sc = new Scanner(System.in);
    
    private ArrayList <Order> orders;
    private HashMap <String, Item> items;
    private HashMap <String, Customer> customers;

    public OnlineShop(ArrayList<Order> orders, HashMap<String, Item> items, HashMap<String, Customer> clientes) {
        this.orders = orders;
        this.items = items;
        this.customers = clientes;
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    /*
    -------------------------------- Menus --------------------------------
    */
    
    public void stockChecker (int orderAmount, String id) throws EmptyStock, InsufficientStock{
        
        int n = items.get(id).getStock();
        
        if (n==0) {
            throw new EmptyStock("Stock agotado para el articulo " + items.get(id).getDescription());
        }
        else if(n< orderAmount){
            throw new InsufficientStock("No hay stock suficiente para el pedido. Solicita "
                    + orderAmount + " de "+items.get(id).getDescription() 
                    + " y solo se dispone de "+n);
        }
    }
    
    public void newOrder() {
        //ARRAYLIST AUXILIAR PARA CREAR EL PEDIDO
        ArrayList<OrderLine> ShoppingCartAux = new ArrayList();
        String dniT, idT, opc, orderedS;
        int ordered=0;
       
        sc.nextLine();
       
        do{
            System.out.println("CLIENTE PEDIDO (DNI):");
            dniT=sc.nextLine().toUpperCase();
            
            if (dniT.isBlank()){
                System.out.println("DNI en blanco. Saliendo del pedido...");
                break;
            }
            if (!Utilities.validateDni(dniT)){
                System.out.println("El DNI no es un DNI válido");
            }
        }while (!customers.containsKey(dniT));
        
        if (!dniT.isBlank()){
          
            System.out.println("INTRODUZCA LOS ARTÍCULOS DEL PEDIDO UNO A UNO: ");
            
            do{
                System.out.println("INTRODUCE CODIGO ARTICULO (99 PARA TERMINAR): ");
                idT=sc.next();
                
                if (!idT.equals("99") && items.containsKey(idT)){
                    System.out.print("(" + items.get(idT).getDescription()+ ") - UNIDADES? ");
                    
                    do {
                        orderedS=sc.next();
                        
                    }while(!Utilities.intChecker(orderedS)); 
                    
                    ordered=Integer.parseInt(orderedS);
                                     
                    try{
                        stockChecker(ordered,idT); // LLAMO AL METODO STOCK, PUEDEN SALTAR 2 EXCEPCIONES
                        ShoppingCartAux.add(new OrderLine(idT,ordered));
                        
                    }catch (EmptyStock e){
                        System.out.println(e.getMessage());
                        
                    }catch (InsufficientStock e){
                        System.out.println(e.getMessage());
                        int disponibles=items.get(idT).getStock();
                        
                        System.out.print("QUIERES LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N) ");
                        opc=sc.next();
                        
                        if (opc.equalsIgnoreCase("S")){
                            ShoppingCartAux.add(new OrderLine(idT,disponibles));
                        }
                    }  
                }
            }while (!idT.equals("99"));
         
            //IMPRIMO EL PEDIDO Y SOLICITO ACEPTACION DEFINITIVA DEL MISMO 
            for (OrderLine l:ShoppingCartAux){
                System.out.println(items.get(l.getItemId()).getDescription() + " - ("+ l.getAmount() + ")");
            }
            
            System.out.println("ESTE ES TU PEDIDO. PROCEDEMOS? (S/N)   ");
            opc=sc.next();
            
            if (opc.equalsIgnoreCase("S")){
            // ESCRIBO EL PEDIDO DEFINITIVAMENTE Y DESCUENTO LAS EXISTENCIAS PEDIDAS DE CADA ARTICULO
                LocalDate today=LocalDate.now();
                orders.add(new Order(orderIdGenerator(dniT),customers.get(dniT),today,ShoppingCartAux));
                
                for (OrderLine l:ShoppingCartAux){
                    items.get(l.getItemId()).setStock(items.get(l.getItemId()).getStock()-l.getAmount());
                } 
            }
        }
    }
    
    public String orderIdGenerator(String customerId){
        
        int counter = 0;
        String newId;
        
        for(Order p: orders){
            if (p.getCustomerOrder().getDni().equalsIgnoreCase(customerId)) {
                counter ++;
            }
        }
        counter++;
        newId = customerId + "-" + String.format("%03d", counter) + "/" + LocalDate.now().getYear();
        return newId;
    }
    
    
    public void cargaDatos(){
        
       customers.put("80580845T",new Customer("80580845T","ANA ","658111111","ana@gmail.com"));
       customers.put("36347775R",new Customer("36347775R","LOLA","649222222","lola@gmail.com"));
       customers.put("63921307Y",new Customer("63921307Y","JUAN","652333333","juan@gmail.com"));
       customers.put("02337565Y",new Customer("02337565Y","EDU","634567890","edu@gmail.com"));
              
       items.put("1-11",new Item("1-11","RATON LOGITECH ST ",14,15));
       items.put("1-22",new Item("1-22","TECLADO STANDARD  ",9,18));
       items.put("2-11",new Item("2-11","HDD SEAGATE 1 TB  ",16,80));
       items.put("2-22",new Item("2-22","SSD KINGSTOM 256GB",9,70));
       items.put("2-33",new Item("2-33","SSD KINGSTOM 512GB",0,200));
       items.put("3-22",new Item("3-22","EPSON PRINT XP300 ",5,80));
       items.put("4-11",new Item("4-11","ASUS  MONITOR  22 ",5,100));
       items.put("4-22",new Item("4-22","HP MONITOR LED 28 ",5,180));
       items.put("4-33",new Item("4-33","SAMSUNG ODISSEY G5",12,580));
       
       LocalDate hoy = LocalDate.now();
       orders.add(new Order("80580845T-001/2024",customers.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new OrderLine("1-11",3),new OrderLine("4-22",3)))));                                                                                                                                                               
       orders.add(new Order("80580845T-002/2024",customers.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new OrderLine("4-11",3),new OrderLine("4-22",2),new OrderLine("4-33",4)))));
       orders.add(new Order("36347775R-001/2024",customers.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new OrderLine("4-22",1),new OrderLine("2-22",3)))));
       orders.add(new Order("36347775R-002/2024",customers.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new OrderLine("4-33",3),new OrderLine("2-11",3)))));
       orders.add(new Order("63921307Y-001/2024",customers.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new OrderLine("2-11",5),new OrderLine("2-33",3),new OrderLine("4-33",2)))));
    } 
}
