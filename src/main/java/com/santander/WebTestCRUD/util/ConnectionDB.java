/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.WebTestCRUD.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ferno
 */
public class ConnectionDB {
    
    public static Connection getConnection(){
        Connection con = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@10.119.142.84:1521:datamart", "postpago", "postpago");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
}
