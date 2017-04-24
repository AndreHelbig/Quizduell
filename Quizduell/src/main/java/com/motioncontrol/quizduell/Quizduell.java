/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motioncontrol.quizduell;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class Quizduell {
    public static void main(String[] args) {
        System.out.println("1. Login");
        System.out.println("2. Register");
        char tmp;
        try {
            tmp = (char) System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Quizduell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
