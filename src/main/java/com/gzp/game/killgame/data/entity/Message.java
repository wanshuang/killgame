package com.gzp.game.killgame.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ws
 * @date 2019/12/27
 */
@Entity
@Table(name = "t_message")
@Accessors(chain = true)
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private ShowType showType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    private Long userId;

    private Long roomId;

    @Transient
    private String userAccount;

    public enum ContentType {
        text,
        emoji
    }

    public enum MessageType {
        chat,
        notice
    }

    public enum ShowType {
        all
    }

}
