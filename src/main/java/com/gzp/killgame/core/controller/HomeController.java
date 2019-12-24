package com.gzp.killgame.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ws
 * @date 2019/12/24
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String root() {
        return "hello";
    }

    @RequestMapping("/home")
    public String home() {
        return "welcome to kill game!";
    }
}
