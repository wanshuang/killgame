package com.gzp.game.killgame.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ws
 * @date 2019/12/26
 */
@Controller
@RequestMapping(value = "/hall")
public class HallController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("hall");
    }

}
