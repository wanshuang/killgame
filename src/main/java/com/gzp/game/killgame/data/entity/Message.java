package com.gzp.game.killgame.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    private MessageType messageType;

    private ShowType showType;

    private Long creationTime;

    private Long userId;

    public enum MessageType{
        chat,
        notice
    }

    public enum ShowType{
        all
    }

}
