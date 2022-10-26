package tw.com.ispan.eeit48.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tw.com.ispan.eeit48.model.RequestModel;
import tw.com.ispan.eeit48.model.ResponseModel;
import tw.com.ispan.eeit48.model.WebSocketSessionModel;
import tw.com.ispan.eeit48.storage.WebSocketSessionStorage;

import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;


public class OperationHandle {

    public void execute(WebSocketSession thisSession, RequestModel requestModel)throws Exception{
        ResponseModel responseModel = new ResponseModel(requestModel.getType(),requestModel.getMessage());
        switch (requestModel.getType()){
            case "a":
                thisSession.sendMessage(new TextMessage(JSON.toJSONString(responseModel)));
                break;
            case "0"://接收登入者信息,返回該身份id
                WebSocketSessionStorage.setName(thisSession.getId(),requestModel.getMessage());
                responseModel.setMessage(requestModel.getMessage());//返回自己的名字
                thisSession.sendMessage(new TextMessage(JSON.toJSONString(responseModel)));//回應已成功連接的訊息
                execute(thisSession,new RequestModel("5"));//新登入者, 將身分顯示在列表中
                break;
            case "1"://接收登入者的請求
                ArrayList<WebSocketSessionModel> users = WebSocketSessionStorage.getUsersList(thisSession.getId());
                responseModel.setMessage(JSON.toJSONString(users));
                thisSession.sendMessage(new TextMessage(JSON.toJSONString(responseModel)));//回應於列表
                break;
            case "2"://向登入者發出通話請求
                WebSocketSessionModel otherModel = WebSocketSessionStorage.get(requestModel.getMessage());
                if(otherModel.getStatus() == 0){//在線
                    WebSocketSessionModel thisModel = WebSocketSessionStorage.get(thisSession.getId());
                    thisModel.setStatus(2);//將自己設為通話中
                    thisModel.setCallId(otherModel.getSessionId());// 將自己列表中顯示對方通話者
                    otherModel.setStatus(2);//將對方設為通話中
                    otherModel.setCallId(thisModel.getSessionId());// 將對方列表中顯示為自己
                    responseModel.setMessage(JSON.toJSONString(thisModel));
                    otherModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));//傳送訊息給對方
                    execute(thisSession,new RequestModel("5"));//更新狀態於列表
                }
                break;
            case "3"://回應對方的通話請求
                WebSocketSessionModel thisModel = WebSocketSessionStorage.get(thisSession.getId());
                if(thisModel.getStatus() == 2){// 顯示自己準備通話
                    WebSocketSessionModel callModel = WebSocketSessionStorage.get(thisModel.getCallId());
                    if(callModel.getStatus() == 2){//顯示對方準備通話
                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(thisModel));
                        jsonObject.put("ready",requestModel.getMessage());
                        responseModel.setMessage(JSON.toJSONString(jsonObject));
                        callModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));//傳送訊息給對方
                        if ("1".equals(requestModel.getMessage())){//接受通話,修改狀態
                            thisModel.setStatus(1);
                            callModel.setStatus(1);
                        }else if("0".equals(requestModel.getMessage())){//不同意通話,移除狀態&id
                            thisModel.setStatus(0);
                            callModel.setStatus(0);
                            thisModel.setCallId(null);
                            callModel.setCallId(null);
                        }
                        execute(thisSession,new RequestModel("5"));
                    }
                }
                break;
            case "4"://取消對方通話要求
                WebSocketSessionModel iModel = WebSocketSessionStorage.get(thisSession.getId());
                if(iModel.getStatus() == 1 && StringUtils.hasText(iModel.getCallId())){
                    WebSocketSessionModel callModel =  WebSocketSessionStorage.get(iModel.getCallId());
                    //判對對方是否為通話中
                    if(callModel.getStatus() == 1 && iModel.getSessionId().equals(callModel.getCallId())){
                        //顯示雙方取消通話
                        try {
                            responseModel.setMessage(callModel.getSessionId());
                            iModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));
                            iModel.setCallId(null);
                            iModel.setStatus(0);
                        }catch (java.lang.IllegalStateException e){//排除斷線意外,刪除暫存
                            WebSocketSessionStorage.delete(iModel.getSessionId());
                        }
                        try {
                            responseModel.setMessage(iModel.getSessionId());
                            callModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));//通知對方關閉連線
                            callModel.setCallId(null);
                            callModel.setStatus(0);
                        }catch (java.lang.IllegalStateException e) {
                            WebSocketSessionStorage.delete(callModel.getSessionId());
                        }
                        execute(thisSession,new RequestModel("5"));
                    }
                }
                break;
            case "5"://列表顯示當前的登入者
                ArrayList<WebSocketSessionModel> allUsersList = WebSocketSessionStorage.getAllUsersList();
                for (WebSocketSessionModel user:allUsersList) {
                    ArrayList<WebSocketSessionModel> others = WebSocketSessionStorage.getUsersList(user.getSessionId(),allUsersList);
                    user.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(new ResponseModel("1",JSON.toJSONString(others)))));
                }
                break;
            case "6"://收到offer訊息,回應給對方
                WebSocketSessionModel isModel = WebSocketSessionStorage.get(thisSession.getId());
                if(isModel.getStatus() == 1 && StringUtils.hasText(isModel.getCallId())){
                    WebSocketSessionModel callModel = WebSocketSessionStorage.get(isModel.getCallId());
                    if(callModel.getStatus() == 1 && isModel.getSessionId().equals(callModel.getCallId())){
                        callModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));
                    }
                }
                break;
            case "7"://收到answer訊息,回應給對方
                WebSocketSessionModel woModel = WebSocketSessionStorage.get(thisSession.getId());
                if(woModel.getStatus() == 1 && StringUtils.hasText(woModel.getCallId())){
                    WebSocketSessionModel callModel = WebSocketSessionStorage.get(woModel.getCallId());
                    if(callModel.getStatus() == 1 && woModel.getSessionId().equals(callModel.getCallId())){
                        callModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));
                    }
                }
                break;
            case "8"://發送訊息給對方
                WebSocketSessionModel wModel = WebSocketSessionStorage.get(thisSession.getId());
                if(wModel.getStatus() == 1 && StringUtils.hasText(wModel.getCallId())){
                    WebSocketSessionModel callModel = WebSocketSessionStorage.get(wModel.getCallId());
                    if(callModel.getStatus() == 1 && wModel.getSessionId().equals(callModel.getCallId())){
                        callModel.getWebSocketSession().sendMessage(new TextMessage(JSON.toJSONString(responseModel)));
                    }
                }
                break;
        }
    }
}
