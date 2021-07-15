package com.sys.user.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author rensf
 * @date 2021/7/14 16:19
 */
@ServerEndpoint("/webSocket")
@Component
@Data
public class WebSocketServer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 用来记录当前连接数
     */
    private static volatile int onlineCount = 0;

    /**
     * concurrent包的线程安全 Set，用来存放每个客户端对应的 WebSocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private String qrcodeId;

    @OnOpen
    public void onOpen(Session session, @PathParam("qrcodeId") String qrcodeId) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.debug("新增连接：{}，当前在线人数：{}", qrcodeId, onlineCount);
        this.qrcodeId = qrcodeId;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

}
