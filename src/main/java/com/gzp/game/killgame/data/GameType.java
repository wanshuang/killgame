package com.gzp.game.killgame.data;

/**
 * @author ws
 * @date 2019/12/31
 */
public enum GameType {

    CHAT("聊天");

    private String describe;

    private GameType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return this.describe;
    }
}
