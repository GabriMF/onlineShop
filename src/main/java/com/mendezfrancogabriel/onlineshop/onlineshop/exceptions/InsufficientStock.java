/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mendezfrancogabriel.onlineshop.onlineshop.exceptions;

/**
 *
 * @author alu15d
 */
public class InsufficientStock extends Exception {

    public InsufficientStock( String chain) {
        super(chain);
    }
}
