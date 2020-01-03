package com.gzp.game.killgame.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzp.game.killgame.data.entity.Message;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.service.play.MessageService;
import com.gzp.game.killgame.util.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author ws
 * @date 2020/1/2
 */
@Controller
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    MessageService messageService;

    @ResponseBody
    @RequestMapping(value = "/message/publish", method = RequestMethod.POST)
    public JSONObject createMessage(HttpServletRequest request,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "roomId") Long roomId) {
        User user = WebUtils.getUser(request);
        Message message = messageService.createMessage(new Message().setCreationTime(new Date()).setContentType(Message.ContentType.text).setContent(content).setMessageType(Message.MessageType.chat).setShowType(Message.ShowType.all).setRoomId(roomId).setUserId(user.getId()).setUserAccount(user.getAccount()));
        JSONObject result = new JSONObject();
        result.put("message", JSON.toJSONString(message));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/message/getAll", method = RequestMethod.GET)
    public JSONObject getAllMessage(HttpServletRequest request,
                                    @RequestParam(value = "roomId") Long roomId) {
        List<Message> messages = messageService.getAllMessage(roomId, WebUtils.getUser(request).getId());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        array.addAll(messages);
        result.put("messages", array.toJSONString());
        return result;
    }

}
