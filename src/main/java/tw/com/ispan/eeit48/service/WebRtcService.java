package tw.com.ispan.eeit48.service;

import com.alibaba.fastjson.JSON;
import tw.com.ispan.eeit48.handle.OperationHandle;
import tw.com.ispan.eeit48.model.RequestModel;
import tw.com.ispan.eeit48.storage.WebSocketSessionStorage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


@Service
public class WebRtcService implements WebSocketHandler {

    private static final Log logger = LogFactory.getLog(WebRtcService.class);


//成功連接WebSocket
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        WebSocketSessionStorage.add(webSocketSession);
        logger.debug("連接新使用者,sessionId:{" + webSocketSession.getId() +
                "},LocalAddress:{" +webSocketSession.getLocalAddress()+
                "},RemoteAddress:{"+webSocketSession.getRemoteAddress()+
                "}");
    }

//WebSocket取得新訊息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String websocketmessage = webSocketMessage.getPayload().toString();
        RequestModel requestModel = JSON.parseObject(websocketmessage, RequestModel.class);
        String sessionId = webSocketSession.getId();
        logger.debug("收到訊息," +
               "sessionId:{" + sessionId +
               "},狀態:{" +requestModel.getType()+
               "},訊息內容:{" +requestModel.getMessage()+
               "}");
        //处理操作
        new OperationHandle().execute(webSocketSession,requestModel);
    }


//處理WebSocket錯誤訊息
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        //如正在對話, 則通知關閉
        new OperationHandle().execute(webSocketSession,new RequestModel("4"));
        WebSocketSessionStorage.delete(webSocketSession.getId());
        //更新最新狀態
        new OperationHandle().execute(webSocketSession,new RequestModel("5"));
        logger.debug("錯誤",throwable);
    }

//處理WebSocket關閉後的錯誤訊息
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

        new OperationHandle().execute(webSocketSession,new RequestModel("4"));
        WebSocketSessionStorage.delete(webSocketSession.getId());
        new OperationHandle().execute(webSocketSession,new RequestModel("5"));
        logger.debug("關閉:"+closeStatus);
    }

    
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
