/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mendezfrancogabriel.onlineshop.onlineshop.utilities;

/**
 *
 * @author alu15d
 */
public class Utilities {
    
    public static boolean validateDni(String dni){
        
        // Verificar que el DNI tiene un formato válido
        if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }
        
        // Extraer el número y la letra del DNI
        String stringNumber = dni.substring(0, 8);
        char letter = dni.charAt(8);

        // Calcular la letra correspondiente al número del DNI
        char calculatedLetter = calculateDniLetter(Integer.parseInt(stringNumber));
        
        // Comparar la letra calculada con la letra proporcionada y devolver
        // el resultado de la comparación TRUE/FALSE
        return letter == calculatedLetter; 
    }
    
    public static char calculateDniLetter(int number) {
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letters.charAt(number % 23);
    }
    
    public static boolean intChecker(String s){
        int n;
        try {
            n = Integer.parseInt(s);
            return true;
            
        } catch (NumberFormatException e) {
            return false;
            
        }
    }
    
    public static boolean doubleChecker(String s){
        Double n;
        try {
            n = Double.parseDouble(s);
            return true;   
            
        } catch (NumberFormatException e) {
            
            return false;
        }
    }
}
