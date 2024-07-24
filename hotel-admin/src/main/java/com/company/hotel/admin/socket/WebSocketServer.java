package com.company.hotel.admin.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/chat/to/{sid}")
@Slf4j
public class WebSocketServer {
    private Session session;
    private String sid;
    /**
     * 静态变量，记录在线连接数
     */
    private static AtomicInteger onlineSessionClientCount = new AtomicInteger();
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> onlineSessionClientMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功的方法
     *
     * @param sid     每次页面建立连接时传入服务端的id
     * @param session 连接会话
     */
    @OnOpen
    public void onOpen(@PathParam("sid") String sid, Session session) {

        log.info("连接建立中===》session_id = {},sid={}", session.getId(), sid);

        onlineSessionClientMap.put(sid, session);

        onlineSessionClientCount.incrementAndGet();

        this.sid = sid;
        this.session = session;
        sendToOne(sid, "连接成功");
        log.info("连接成功，当前在线数为：{}===》开始监听新连接：session_id={}，sid={}", onlineSessionClientCount, session.getId(), sid);

    }

    @OnClose
    public void onClose(@PathParam("sid") String sid, Session session) {
        onlineSessionClientMap.remove(sid);
        onlineSessionClientCount.decrementAndGet();
        log.info("连接关闭成功，当前在线数为：{}==》关闭连接信息：session_id={},sid={}", onlineSessionClientCount, session.getId(), sid);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject jsonObject = JSON.parseObject(message);
        String toSid = jsonObject.getString("sid");
        String msg = jsonObject.getString("message");
        log.info("服务端接收到客户端信息==>fromSid={},toSid={},message={}", sid, toSid, message);

        //如果未指定Sid，则群发
        if (toSid == null||toSid == "" || "".equalsIgnoreCase(toSid)) {
        sendAll(msg);
        }else {
            sendToOne(toSid, msg);
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("WebSocket发生错误，错误信息为："+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 群发
     * @param msg
     */
    private void sendAll(String msg) {
        onlineSessionClientMap.forEach((onlineSid,toSesssion)->{
            //排除自己
            if (!sid.equalsIgnoreCase(onlineSid)){
                log.info("服务端给客户端群发消息==》sid={},toSid={},message={}",sid,onlineSid,msg);
                toSesssion.getAsyncRemote().sendText(msg);
            }
        });
    }

    /**
     * 指定发
     * @param toSid
     * @param message
     */
    private void sendToOne(String toSid, String message) {
        Session toSession = onlineSessionClientMap.get(toSid);
        if (toSession == null) {
            log.error("服务端给客户端发送信息==》toSid={}不存在，message = {}", toSid, message);
            return;
        }
        //异步发送
        log.info("服务端给客户端发送信息==》toSid={},message={}", toSid, message);
        toSession.getAsyncRemote().sendText(message);
//        //同步发送
//        try {
//            toSession.getBasicRemote().sendText(message);
//        }catch (IOException e){
//            log.error("发送消息失败，webSocket IO异常");
//            e.printStackTrace();
//        }
    }
}
