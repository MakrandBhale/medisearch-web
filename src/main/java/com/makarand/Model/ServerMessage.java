package com.makarand.Model;

public class ServerMessage {
    /*A class to wrap messages from server to client*/
    public String msg;
    public int msgType, code;

    public ServerMessage(String msg, int code, int msgType) {
        this.msg = msg;
        this.code = code;
        this.msgType = msgType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
