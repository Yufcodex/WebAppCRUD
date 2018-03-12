/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.WebTestCRUD.model;

/**
 *
 * @author ferno
 */
public class UserBean {
    
    private String username;
    
    private String userpass;
    
    private String usermail;

    public UserBean() {
    }

    public UserBean(String username, String userpass, String usermail) {
        this.username = username;
        this.userpass = userpass;
        this.usermail = usermail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }
    
    
    
}
