/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.WebTestCRUD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ferno
 */
@Controller
@RequestMapping("/")
public class AppController {
    
    @RequestMapping(method = RequestMethod.GET)
    String getLogin() {
        return "index";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String getHome() {
        return "logedPage";
    }
}
