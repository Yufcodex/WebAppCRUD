/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.WebTestCRUD.controller;

import com.santander.WebTestCRUD.model.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ferno
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    
    private static UserBean USER = new UserBean();
    private static String URL = "jdbc:mysql://localhost:3306/santandertest";
    private static String USERDB = "root";
    
    @RequestMapping(value = "/{usrmail}/and/{usrpass}", method= RequestMethod.GET)
    public UserBean getLogin(@PathVariable("usrmail") String usrmail, @PathVariable("usrpass") String usrpass){
        Connection con = null;
        Statement stmt = null;
        String query = "select * from users where USER_MAIL='"+usrmail+"' and USER_PASS='"+usrpass+"'";             
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USERDB, "");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
             stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next())
                {
                   String username = rs.getString("USER_NAME");
                   String status = rs.getString("USER_PASS");
                   String mail = rs.getString("USER_MAIL");
                   USER = new UserBean(username, status, mail);
                }
                stmt.close();
                return USER;              
            }
            catch(SQLException e)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            finally 
            {
                if (stmt != null)
                {   try
                    {
                        USER = new UserBean();
                        stmt.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }            
        return USER;
    }
    
    @RequestMapping(value = "/{usrmail},{usrname},{usrpass}", method= RequestMethod.POST)
    public UserBean registerUser(@PathVariable("usrmail") String usrmail,@PathVariable("usrname") String usrname, @PathVariable("usrpass") String usrpass){
        Connection con = null;
        Statement stmt = null;
        String query = "INSERT INTO users (USER_NAME,USER_PASS,USER_MAIL ) VALUES " + "('"+usrname+"','"+usrpass+"','"+usrmail+"')"; 
        String queryUser = "select * from users where USER_MAIL='"+usrmail+"' and USER_PASS='"+usrpass+"'"; 
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USERDB, "");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
                ResultSet rs = stmt.executeQuery(queryUser);
                while(rs.next())
                {
                   String username = rs.getString("USER_NAME");
                   String status = "success";
                   String mail = rs.getString("USER_MAIL");
                   USER = new UserBean(username, status, mail);
                }
                stmt.close();
                return USER;                 
            }
            catch(SQLException e)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            finally 
            {
                if (stmt != null)
                {   try
                    {
                        stmt.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        return USER;
    }
    
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public static List<UserBean>getAllUsers(){
        Connection con = null;
        Statement stmt = null;
        List<UserBean> allusers = new ArrayList<UserBean>();
        String queryUser = "select * from users"; 
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USERDB, "");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(queryUser);
                while(rs.next())
                {
                   String username = rs.getString("USER_NAME");
                   String password = rs.getString("USER_PASS");
                   String mail = rs.getString("USER_MAIL");
                   USER = new UserBean(username, password, mail);
                   allusers.add(USER);
                }
                stmt.close();
                return allusers;                 
            }
            catch(SQLException e)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            finally 
            {
                if (stmt != null)
                {   try
                    {
                        stmt.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        return allusers;
    }
    
    @RequestMapping(value = "/{usrmail},{usrname},{usrpass}", method= RequestMethod.PUT)
    public static List<UserBean>updateUser(@PathVariable("usrmail") String usrmail,@PathVariable("usrname") String usrname, @PathVariable("usrpass") String usrpass){
        Connection con = null;
        Statement stmt = null;
        List<UserBean> users = new ArrayList<UserBean>();
        String queryUser = "UPDATE users SET USER_NAME = '"+usrname+"',USER_PASS = '"+usrpass+"',USER_MAIL = '"+usrmail+"' WHERE USER_MAIL = '"+usrmail+"'"; 
        String query = "SELECT * FROM users WHERE USER_MAIL = '"+usrmail+"'";
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USERDB, "");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt = con.createStatement();
                stmt.executeUpdate(queryUser);
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                   String username = rs.getString("USER_NAME");
                   String status = rs.getString("USER_PASS");
                   String mail = rs.getString("USER_MAIL");
                   USER = new UserBean(username, status, mail);
                   users.add(USER);
                }
                stmt.close();
                return users;                 
            }
            catch(SQLException e)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            finally 
            {
                if (stmt != null)
                {   try
                    {
                        stmt.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        return users; 
    }
    @RequestMapping(value = "/{usrmail}", method = RequestMethod.DELETE)
    public List<UserBean> deleteUser(@PathVariable("usrmail") String usrmail) {
        Connection con = null;
        Statement stmt = null;
        List<UserBean> users = new ArrayList<UserBean>();
        String queryUser = "DELETE FROM users WHERE USER_MAIL = '"+usrmail+"'"; 
        String query = "SELECT * FROM users";
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USERDB, "");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt = con.createStatement();
                stmt.executeUpdate(queryUser);
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                   String username = rs.getString("USER_NAME");
                   String password = rs.getString("USER_PASS");
                   String mail = rs.getString("USER_MAIL");
                   USER = new UserBean(username, password, mail);
                   users.add(USER);
                }
                stmt.close();
                return users;                 
            }
            catch(SQLException e)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            finally 
            {
                if (stmt != null)
                {   try
                    {
                        stmt.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
	return users;
    }
}
