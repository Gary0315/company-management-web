package tw.com.ispan.eeit48.storage;


import org.springframework.web.socket.WebSocketSession;

import tw.com.ispan.eeit48.model.WebSocketSessionModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 儲存websocketsession
public class WebSocketSessionStorage {
    private final static ConcurrentHashMap<String , WebSocketSessionModel> 
    webSocketSessionMap = new ConcurrentHashMap<>();

//增加session
    public static boolean add(WebSocketSession session){
        if(webSocketSessionMap.containsKey(session.getId())){
            return false;//已存在
        }
        webSocketSessionMap.put(session.getId(),new WebSocketSessionModel(session));
        return true;
    }

//移除session
    public static WebSocketSessionModel delete(String sessionId){
        if(!webSocketSessionMap.containsKey(sessionId)){
            return null;
        }
        return webSocketSessionMap.remove(sessionId);
    }

//指定暱稱
    public static boolean setName(String sessionId,String name){
        if(!webSocketSessionMap.containsKey(sessionId)){
            return false;
        }
        webSocketSessionMap.get(sessionId).setName(name);
        return true;
    }

//使用者中不會顯示自己
    public static ArrayList<WebSocketSessionModel> getUsersList(String sessionId,ArrayList<WebSocketSessionModel> all){
        ArrayList<WebSocketSessionModel> arrlist = new ArrayList<>();
        for (WebSocketSessionModel model:all) {
            if(model.getSessionId().equals(sessionId)){
                continue;
            }
            arrlist.add(model);
        }
        return arrlist;
    }

//查所有使用者,除了自己
    public static ArrayList<WebSocketSessionModel> getUsersList(String sessionId){
        ArrayList<WebSocketSessionModel> all = getAllUsersList();
        return getUsersList(sessionId,all);
    }

//讀取線上使用者
    public static ArrayList<WebSocketSessionModel> getAllUsersList(){
        ArrayList<WebSocketSessionModel> arrlist = new ArrayList<>();
        Iterator<Map.Entry<String,WebSocketSessionModel>> iterator = webSocketSessionMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,WebSocketSessionModel> next = iterator.next();
            arrlist.add(next.getValue());
        }
        return arrlist;
    }

//讀取訊息
    public static WebSocketSessionModel get(String sessionId){
        return webSocketSessionMap.get(sessionId);
    }
}
