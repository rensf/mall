package com.sys.user.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author rensf
 * @date 2021/7/14 16:19
 */
@ServerEndpoint("/webSocket/{qrcodeId}")
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

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
        logger.debug("收到来自：{}的请求，信息：{}", qrcodeId, message);
        for (WebSocketServer webSocketServer : webSocketSet) {
            try {
                webSocketServer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("qrcodeId") String qrcodeId) {
        logger.debug("推送消息到窗口：{}，推送内容：{}", qrcodeId, message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (qrcodeId == null) {
                    item.sendMessage(message);
                } else if (item.qrcodeId.equals(qrcodeId)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

}
