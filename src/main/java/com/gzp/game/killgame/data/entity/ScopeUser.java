package com.gzp.game.killgame.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ws
 * @date 2020/1/6
 */
@Entity
@Table(name = "t_scope_user")
@Accessors(chain = true)
@Data
public class ScopeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;

    private Long userId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    private Long scopeUserId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        active,
        invalid
    }

}
