// 建立websocket, 並傳入回調函數
var createWebSocket = function(name,ws,onopen,onclose,onerror,onmessage){
    var socket = new WebSocket(ws);
    var int = null;
    socket.onopen = function(){
        console.log("WebSocket連接成功:[" + name +"]");
        if(onopen){
            onopen();
        }
    };  
    socket.onclose = function(event){
        console.log("WebSocket已關閉:[" + name +"]");
        if(onclose){
            onclose();
        }
        window.clearInterval(int);
    }; 
    socket.onerror = function(event){
        console.log("WebSocket錯誤:[" + name +"]");
        if(onerror){
            onerror();
        }
    };
    socket.onmessage = function(event){
        if(onmessage){
            onmessage(event.data);
        }
    };
    var send = function(type,msg){
        socket.send(JSON.stringify({"type":type,"message":msg}));
    }
    return {"send":send};
}
