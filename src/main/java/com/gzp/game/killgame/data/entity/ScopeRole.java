package com.gzp.game.killgame.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author ws
 * @date 2020/1/6
 */
@Entity
@Table(name = "t_scope_role")
@Accessors(chain = true)
@Data
public class ScopeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private Long gameTypeId;

}
