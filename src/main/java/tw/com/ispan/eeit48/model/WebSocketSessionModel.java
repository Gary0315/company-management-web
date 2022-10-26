package tw.com.ispan.eeit48.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.socket.WebSocketSession;


public class WebSocketSessionModel {
	//使用者id
    private String sessionId; 
    //使用者暱稱
    private String name; 
    // 狀態:  0閒置, 1正在對話, 2準備對話
    private int status; 
    // 呼叫對方id
    private String callId;
    // 呼叫群組裡的id... 不會寫
    private String callGroupId;

    @JSONField(serialize = false)// 避免被fastjson轉換
    private WebSocketSession webSocketSession;

    public WebSocketSessionModel(WebSocketSession webSocketSession){
        setWebSocketSession(webSocketSession);
        setSessionId(webSocketSession.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallGroupId() {
        return callGroupId;
    }

    public void setCallGroupId(String callGroupId) {
        this.callGroupId = callGroupId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
