package com.gzp.game.killgame.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.service.user.IdentityService;
import com.gzp.game.killgame.util.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ws
 * @date 2019/12/25
 */
@Controller
public class LoginController {

    @Autowired
    IdentityService identityService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @ResponseBody
    @RequestMapping(value = "/login_do", method = RequestMethod.POST)
    public JSONObject identity(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "pwd", required = false) String pwd) {
        User user = identityService.identityVerification(account, pwd);
        JSONObject result = new JSONObject();
        if (user == null) {
            result.put("flag", false);
            result.put("message", "别添乱!");
        } else {
            result.put("flag", true);
            WebUtils.setUser(response, user);
        }
        return result;
    }

}
