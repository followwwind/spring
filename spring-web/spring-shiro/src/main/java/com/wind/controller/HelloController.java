package com.wind.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author com.wind
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayHello(){
        return "hello";
    }

    @RequestMapping(value = "sayWorld")
    public String sayWorld(){
        return "world";
    }
}