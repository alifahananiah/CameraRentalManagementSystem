/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camerarentalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class database {
    
     public static Connection connectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // NOTE!! MAKE YOUR OWN DATABASE
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/rentcamera", "root", ""); 
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
