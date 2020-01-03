package com.gzp.game.killgame.service.play;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzp.game.killgame.data.entity.Message;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.data.repository.MessageRepository;
import com.gzp.game.killgame.data.repository.UserRepository;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ws
 * @date 2020/1/2
 */

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public Message createMessage(Message m) {
        return messageRepository.save(m);
    }

    public List<Message> getAllMessage(Long roomId, Long userId) {
        List<Message> messages = Lists.newArrayList(messageRepository.findByRoomId(roomId));
        this.packageMessages(messages);
        return messages;
    }

    public List<Message> getNewMessage(Long roomId, Long userId, Long lastId) {
        List<Message> messages = Lists.newArrayList(messageRepository.findByIdGreaterThanAndRoomId(lastId, roomId));
        this.packageMessages(messages);
        return messages;
    }

    public Long getMaxMessage(Long roomId, Long userId, Long lastId){

        return 0l;
    }

    private void packageMessages(List<Message> messages) {
        //封装useraccount用
        Map<Long, User> uMap = Maps.uniqueIndex(userRepository.findAll(), new Function<User, Long>() {

            @Nullable
            @Override
            public Long apply(@Nullable User user) {
                return user.getId();
            }
        });

        messages.forEach(message -> {
            if (uMap.get(message.getUserId()) != null) {
                message.setUserAccount(uMap.get(message.getUserId()).getAccount());
            }
        });
    }

}
