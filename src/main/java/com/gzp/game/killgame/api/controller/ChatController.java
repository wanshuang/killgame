package com.gzp.game.killgame.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.gzp.game.killgame.data.entity.Message;
import com.gzp.game.killgame.data.repository.MessageRepository;
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
    MessageRepository messageRepository;

    @ResponseBody
    @RequestMapping(value = "/message/publish", method = RequestMethod.GET)
    public JSONObject createMessage(HttpServletRequest request,
                                    @RequestParam(value = "content") String content) {

        Message message = messageRepository.save(new Message().setCreationTime(new Date()).setContentType(Message.ContentType.text).setContent(content).setMessageType(Message.MessageType.chat).setShowType(Message.ShowType.all));
        JSONObject result = new JSONObject();
        result.put("messages", message);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/message/getAll", method = RequestMethod.GET)
    public JSONObject getAllMessage(HttpServletRequest request) {
        List<Message> messages = Lists.newArrayList(messageRepository.findAll());

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        for (Message message : messages) {
            array.add(message);
        }
        result.put("messages", array.toJSONString());
        return result;
    }

}
