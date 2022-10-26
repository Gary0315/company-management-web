var rtc_getTool = function(onmessage,onaddstream,onclose,onopen){

    var PeerConnection = RTCPeerConnection;
    //兼容不同瀏覽器已取得對方串流
    var getUserMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.mediaDevices.getUserMedia);
    //兼容不同瀏覽器
    var SessionDescription = (window.RTCSessionDescription || window.mozRTCSessionDescription || window.webkitRTCSessionDescription);
    var pc = null;//peerConnection
    var oppositeChannel = null;//遠端數據串流
    var localStream = null;//本機視訊串流
    var createPeerConnection = function(){
        //建立peerConnection
        pc = new PeerConnection();
        pc.localChannel = pc.createDataChannel({
            ordered: false,
            maxRetransmitTime: 3000,
        });
        //本機建立&接收遠端所發送的數據串流
        pc.localChannel.onerror = function (error) {
            console.log("數據串流建立錯誤:", error);
      	}; 
        pc.localChannel.onopen = function () {
            console.log("數據串流建立成功");
            onopen();
      	};
        pc.localChannel.onclose = function () {
            console.log("關閉數據串流");
            onclose();
            close();
            pc = null;
        };
        pc.localChannel.onmessage = function(event){
            onmessage(event);
        };
        pc.ondatachannel = function(event) {
            oppositeChannel = event.channel;
        };
        //如接收到視訊串流,則輸出到video標籤
        pc.onaddstream = function(event){
            onaddstream(event.stream);
        };
        pc.onicecandidate = function(event){
            if (event.candidate !== null) {
                var candidate = {"candidate":event.candidate,"type":"_candidate"};
                chat_sendMsg(8,JSON.stringify(candidate));
            }
        };
    }

    //發送訊息
    var sendOffer = function(){
        pc.createOffer(function(desc){
            pc.setLocalDescription(desc);
            chat_sendMsg(6,JSON.stringify({"sdp":desc,"type":"_offer"}));
        }, function (error) {
            console.log("發送訊息失敗:" + error);
        });
    }

    // 回應訊息
    var sendAnswer = function(){
        pc.createAnswer(function(desc){
            pc.setLocalDescription(desc);
            chat_sendMsg(7,JSON.stringify({"sdp":desc,"type":"_answer"}));
        }, function (error) {
            console.log("回應訊息失敗:" + error);
        });
    }

    // 處理接收到的訊息, 並回覆給指定人
    var signallingHandle = function(json){

        if(json.type === "_candidate" ){
            pc.addIceCandidate(new RTCIceCandidate(json.candidate));
        }else{
            pc.setRemoteDescription(new SessionDescription(json.sdp),
                function(){

                    if(json.type === "_offer") {
                        sendAnswer();
                    }
                }
            );
        }
    }


    //建立webrtc並發送數據給對方
    var send = function(msg){
        oppositeChannel.send(msg);
    }
    
    //啟動視訊,音訊 並將對方串流傳入回調函數
    var openVideoAudioLocal = function(callbackLocalVideo,video,audio){
        getUserMedia.call(navigator, {
            video: video,//啟動視訊
            audio: audio//啟動音訊
        },function(localMediaStream) {//擷取串流成功的回調函數
            callbackLocalVideo(localMediaStream);
        },function(error){
            console.log("创建本地媒体对象失败:" + error);
        });
    }

    //建立視訊串留給對方, 並發送offer
    var sendAddStream = function(stream){
        localStream = stream;
        pc.addStream(localStream);
        sendOffer();
    }

    // 關閉webrtc 
    var close = function(){
        if(pc != null){
            closeStream();
            pc.localChannel.close();
            pc.close();
            pc = null;
            oppositeChannel = null;
            chat_sendMsg(4,"");//發送關閉訊息
        }
    }

    //關閉視訊串流
    var closeStream = function(){
        if(localStream != null){
            if(localStream.getVideoTracks()[0]){
                localStream.getVideoTracks()[0].stop();
            }
            if(localStream.getAudioTracks()[0]){
                localStream.getAudioTracks()[0].stop();
            }
            if(localStream.getTracks()[0]){
                localStream.getTracks()[0].stop();
            }
            localStream = null;
        }
    }

    return {
        "closeStream":closeStream,
        "sendAddStream":sendAddStream,
        "openVideoAudioLocal":openVideoAudioLocal,
        "close":close,
        "signallingHandle":signallingHandle,
        "sendOffer":sendOffer,
        "sendAnswer":sendAnswer,
        "createPeerConnection":createPeerConnection,
        "send":send};
}