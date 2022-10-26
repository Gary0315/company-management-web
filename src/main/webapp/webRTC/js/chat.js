
$("body").append(
    '<div class="modal fade" id="chat_dialog" tabindex="-1" role="dialog" data-keyboard="false" aria-hidden="true" data-backdrop="static" aria-labelledby="myModalLabel">'+
        '<div class="modal-dialog">'+
            '<div class="modal-content">'+
                '<div class="modal-header">'+
                    '<h4 class="modal-title"><span id="chat_ready_id" data-uid=""></span>想要與你通話</h4>'+
                '</div>'+
                // '<div class="modal-body"></div>'+
                '<div class="modal-footer">'+
                '<button type="button" class="btn btn-primary" onclick="chat_ready(1)">確定</button>'+
                    '<button type="button" class="btn btn-default" onclick="chat_ready(0)">取消</button>'+
                '</div>'+
            '</div>'+
        '</div>'+
    '</div>'
);
$("body").append(
    '<div class="modal fade" id="chat_name_modal" tabindex="-1" role="dialog" data-keyboard="true" data-backdrop="static" aria-labelledby="myModalLabel">'+
        '<div class="modal-dialog">'+
            '<div class="modal-content">'+
                '<div class="modal-header">'+
                    '<h3 class="modal-title">請輸入名稱</h3>'+
                '</div>'+
                '<div class="modal-body"><input type="text" id="chat_name" class="form-control" ></div>'+
                '<div class="modal-footer">'+
                '<button type="button" class="btn btn-primary" onclick="chat_setName()">確定</button>'+
                    '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'+
                '</div>'+
            '</div>'+
        '</div>'+
    '</div>'
);
$("body").append(
    '<div class="modal fade" id="chat_dialogForOne" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="overflow:auto;">' +
    '<div class="modal-dialog" style="width: 904px;">' +
        '<div class="modal-content">' +
            '<div class="modal-header">' +
                '<h4 class="modal-title">正在與<label name="name"></label>通話</h4>' +
            '</div>' +
            '<!-- 聊天框 -->' +
            '<div class="modal-body" style="margin: 0px;padding: 0px;">' +
                '<div style="height: 647px;">' +
                    '<!-- 聊天信息展示 -->' +
                    '<div style="width: 400px;height: 647px;float: right;">' +
                        '<div style="overflow-y:auto;height: 500px;" class="overflow-3">' +
                            '<!-- 聊天紀錄-->' +
                            '<ul class="bubbleDiv overflow-3" name="bubbleDiv"></ul>' +
                        '</div>' +
                        '<!-- 聊天输入 -->' +
                        '<div style="height: 171px;">' +
                            '<div style="height: 146px;width: 99%;">' +
                                '<textarea class="overflow-3" name = "message" style="width: 100%;height: 100%;resize:none;border: 0px;background-color: rgb(235, 233, 233);" placeholder="請輸入訊息..."></textarea>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                    '<!-- 視訊視窗 -->' +
                    '<div style="width: 500px;height: 645px;float: right;">' +
                        '<!-- 對方 -->' +
                        '<div class="panel panel-default" style="margin: 0px;padding: 0px;">' +
                            '<div class="panel-body">' +
                                '<video style="height:240px;width:470px;" name="remote" autoplay></video>' +
                            '</div>' +
                            '<div class="panel-footer" align="center"></div>' +
                        '</div>' +
                        '<!-- 本人 -->' +
                        '<div class="panel panel-default" style="margin: 0px;padding: 0px;">' +
                            '<div class="panel-body">' +
                               '<video style="height:240px;width:470px;margin: 0px;padding: 0px;" name="video" autoplay>' +'</video>' +
                            '</div>' +
                            '<div class="panel-footer" align="center">' +
                                '<button type="button" class="btn btn-default btn-lg btn-m" name="openVideo" data-use="false"><i class="glyphicon glyphicon-facetime-video"></i><span>  開始視訊</span></button>' +
                                '<button type="button" class="btn btn-default btn-lg btn-m" name="openAudio" data-use="false"><i class="glyphicon glyphicon-earphone"></i><span>  開始語音</span></button>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>' +
            '<div class="modal-footer">' +
                '<button type="button" class="btn btn-default" data-dismiss="modal">離開</button>' +
                '<button type="button" class="btn btn-success" id="chat_sendString">發送</button>' +
            '</div>' +
        '</div>' +
    '</div>'
);

var chat_socket = null;
var rtc = null;
var chat_name = null;

//建立使用者
var chat_setName = function(){
    var name = $("#chat_name").val();
    if(name){
        chat_name = name;
        chat_socketCon();
        chat_sendMsg(1,"");
        $("#chat_name_modal").modal('hide');
    }
}

// 建立websocket
var chat_socketCon = function(){
    if(chat_socket != null){
        return;
    }
    chat_socket = 0;//避免創建兩次socket
    var t_socket = createWebSocket("webrtc",websocketPath,
    function(){//連接成功發送身分
        chat_socket = t_socket;
        chat_sendMsg(0,chat_name);
    },function(){//連接關閉
        chat_socket = null;
        $("#chat_socketCon li a").eq(0).html("尚未連接");
        var u = $("#chat_socketCon");
        var li = u.find("li");
        for(var i = 2;i < li.length; i++){
            li[i].remove();
        }
        $("#chat_ready_id").html("");
        $("#chat_ready_id").data("uid","");
        $("#chat_dialog").modal('hide');
        $("#chat_dialogForOne").modal("hide");
    },function(){//連接錯誤
        chat_socket = null;
        $("#chat_socketCon li a").eq(0).html("尚未連接");
        var u = $("#chat_socketCon");
        var li = u.find("li");
        for(var i = 2;i < li.length; i++){
            li[i].remove();
        }
    },function(msg){//收到消息
        var data = JSON.parse(msg);
        chat_response(data.type,data.message);
    });
}


//處理請求&回應 伺服器回應的訊息
var chat_response = function(type,msg){
    if(type == "0"){//接收登入者id
        $("#chat_socketCon li a").eq(0).html("<span id='uid'>"+msg+"</span> - 已連線");
        chat_sendMsg(1,"");
    }else if(type == "1"){//顯示其他登入者
        if(msg.length != 0){
            var users = JSON.parse(msg);
            var u = $("#chat_socketCon");
            var li = u.find("li");
            for(var i = 2;i < li.length; i++){
                li[i].remove();
            }
            if(users.length == 0){
                u.append('<li><a href="javascript:void(0)">暫無其他登入者</a></li>');
                return;
            }
            for(var i in users){
                var uid = users[i]["sessionId"];
                var name = users[i]["name"];
                var status = users[i]["status"];
                var ready = users[i]["callId"] == uid ? 2 : 1;
                u.append("<li name='chat_user'><a href=\"javascript:void(0)\"><span class='glyphicon glyphicon-user'></span>&nbsp;&nbsp;"+name+"&nbsp;&nbsp;<button data-userid='"+uid+"' type='button' class='btn "+(status == 0 ? "btn-success" : ready == 2 ? "btn-primary" : "btn-warning")+" btn-m' id='u"+uid+"'>"+(status == 0 ? "撥打通話" : ready == 2 ? "取消通話" : "通話中...")+"</button></a></li>");
            }
            
            //綁定通話事件
            $("#chat_socketCon li[name='chat_user']").on("click",function(e){
                e.stopPropagation();
            });
            $("#chat_socketCon button").off("click").on("click",function(e){
                e.stopPropagation();
                var userid = $(this).data("userid");
                if(userid){
                    var status = $(this).html();
                    if(status == "撥打通話"){
                        //檢查是否與其他人通話
                        var bts = $("#chat_socketCon button");
                        for(var i = 0;i < bts.length; i++){
                            if($(bts[i]).html() == "取消通話"){
                                return;
                            }
                        }
                        chat_sendMsg(2,userid);
                        $(this).html("取消通話");
                        $(this).toggleClass("btn-success");
                        $(this).toggleClass("btn-primary");
                    }else if(status == "取消通話"){
                        chat_sendMsg(4,"");
                    }
                }
            });
        }
    }else if(type == "2"){//接收他人通話請求
        if(msg.length != 0){
            var users = JSON.parse(msg);
            $("#chat_ready_id").html(users.name);
            $("#chat_ready_id").data("uid",users.sessionId);
            $("#chat_dialog").modal('show');
        }
    }else if(type == "3"){//回應他人通話請求
        if(msg.length != 0){
            var users = JSON.parse(msg);
            var ready = users.ready;
            var uid = users.sessionId;
            if(ready == "1"){
                var dialogfor = $("#chat_dialogForOne");
                var dialogforname = dialogfor.find("label[name='name']");
                rtc = rtc_getTool(onmessage,onaddstream,function(){
                    dialogfor.modal("hide");
                },function(){
                    dialogforname.html(users.name);
                    dialogfor.modal("show");
                });
                rtc.createPeerConnection();
                rtc.sendOffer();
            }else if(ready == "0"){
                var bt = $("#u" + uid);
                bt.html("撥打通話");
                bt.removeClass("btn-primary");
                bt.addClass("btn-success");
            }
        }
    }else if(type == "4"){//接收取消通話請求
        if(msg.length != 0){
            $("#chat_dialog").modal('hide');
            $("#chat_dialogForOne").modal("hide");
            var u = $("#u" + msg);
            u.html("撥打通話");
            u.removeClass("btn-primary");
            u.addClass("btn-success");
            $("#chat_ready_id").html("");
            $("#chat_ready_id").data("uid","");
            if(rtc != null){
                rtc.close();
            }
        }
    }else if(type == "6"){//接收伺服器的 offer
        if(msg.length != 0){
            var json = JSON.parse(msg);
            rtc.signallingHandle(json);
        }
    }else if(type == "7"){//接收伺服器的 answer
        if(msg.length != 0){
            var json = JSON.parse(msg);
            rtc.signallingHandle(json);
        }
    }else if(type == "8"){
        if(msg.length != 0){
            var json = JSON.parse(msg);
            rtc.signallingHandle(json);
        }
    }
}


//點擊連接websocket
$("#rct_con").on("click",function(e){
    e.stopPropagation();
    if(chat_socket == null){
        $("#chat_name_modal").modal('show');
    }else{
        chat_sendMsg(1,"");//獲取在線使用者, 除了自己
    }
    
});

var chat_sendClick = function(){
    var value = $("#chat_dialogForOne textarea[name='message']").val();
    var msg = {"data":value,"type":"text","id":$("#uid").html()};
    showMessage("chat_dialogForOne",msg,"right");
    rtc.send(JSON.stringify(msg));
}

//發送訊息
$("#chat_sendString").on("click",function(e){
    chat_sendClick();
});

//Enter 發送訊息
$("#chat_dialogForOne textarea[name='message']").on("keydown",function(e){
    if(e.keyCode == 13 && e.ctrlKey){
        $(this).val($(this).val() + "\n");
    }else if(e.keyCode == 13){
        e.preventDefault();
        chat_sendClick();
    }
});

//發送文件
$("#chat_fileMsgForOne").on("change",function(){
	//todo 不會寫...
});

//接收webrtc 回傳的視訊
var filequeue = {};//陣列,可接收多份文件
var onmessage = function(event){
    var msg = JSON.parse(event.data);
    if(msg.type == "text"){//文字内容
        showMessage("chat_dialogForOne",msg,"left");
    }else if(msg.type == "file"){
        //todo 不會寫...
    }
}

//接收webrtc 回傳的視訊&音訊
var onaddstream = function(remoteStream){
    var video = $("#chat_dialogForOne video[name='remote']")[0];
    video.srcObject = remoteStream;
    video.onloadedmetadata = function(e) {
        video.play();
    };
}


//重置視訊&語音按鈕
var resetVideoButton = function(){
	var openVideo = $("#chat_dialogForOne button[name='openVideo']");
	var openAudio = $("#chat_dialogForOne button[name='openAudio']");
	openVideo.find(" > span").html("開始視訊");
	openAudio.show();
	openAudio.find(" > span").html("語音聊天");
	openVideo.show();
	openVideo.removeClass("active");
    openAudio.removeClass("active");
    openAudio.data("use",false);
    openVideo.data("use",false);
}

//開啟&關閉視訊聊天
$("#chat_dialogForOne button[name='openVideo']").on("click",function(){
	$(this).toggleClass("active");
    if(!$(this).data("use")){//開啟視訊聊天
        $(this).data("use",true);
        rtc.openVideoAudioLocal(function(localStream){
            var video = $("#chat_dialogForOne video[name='video']")[0]; 
            video.srcObject=localStream;
            video.onloadedmetadata = function(e) {
                video.play();
            };
            rtc.sendAddStream(localStream);
        },true,true);//啟動後音訊只傳給對方,避免聽到自己的聲音
		$(this).find(" > span").html("關閉視訊");
        $("#chat_dialogForOne button[name='openAudio']").hide();
    }else{//關閉視訊聊天
        rtc.closeStream();
        resetVideoButton();
	}
});

//開啟&關閉語音聊天
$("#chat_dialogForOne button[name='openAudio']").on("click",function(){
	$(this).toggleClass("active");
	$(this).data("use",$(this).data("use") ? false : true);
	if($(this).data("use")){//開啟語音聊天
		rtc.openVideoAudioLocal(function(localStream){
            var video = $("#chat_dialogForOne video[name='video']")[0]; 
            video.srcObject=localStream;
            video.onloadedmetadata = function(e) {
                video.play();
            };
            rtc.sendAddStream(localStream);
        },false,true);//啟動後音訊只傳給對方,避免聽到自己的聲音
		$(this).find(" > span").html("關閉語音");
		$("#chat_dialogForOne button[name='openVideo']").hide();
	}else{//關閉語音聊天
		rtc.closeStream();
        resetVideoButton();
	}
});

//關閉聊天室
$("#chat_dialogForOne").on("hidden.bs.modal",function(e){
    resetVideoButton();
    if(rtc != null){
        rtc.close();
        rtc = null;
        chat_sendMsg(4,"");
    }
    //清除聊天紀錄
    var lis = $(this).find("ul[name='bubbleDiv'] > li");
    for (var i = 0 ; i < lis.length; i++){
        $(lis[i]).remove();
    }
});


//向伺服器發送數據,確認是否成功
var chat_sendMsg = function(type,msg){
    if(chat_socket == null || chat_socket == 0){
        return false;
    }
    chat_socket.send(type,msg);
    return true;
}

//是否通話
var chat_ready = function(i){
    chat_sendMsg("3",i);//回復
    if(i == 1){//同意
        var uid = $("#chat_ready_id").data("uid");
        var dialogfor = $("#chat_dialogForOne");
        var dialogforname = dialogfor.find("label[name='name']");
        rtc = rtc_getTool(onmessage,onaddstream,function(){
            dialogfor.modal("hide");
        },function(){
            dialogforname.html($("#chat_ready_id").html());
            dialogfor.modal("show");
        });
        rtc.createPeerConnection();
        var bt = $("#u" + uid);
        bt.html("取消通話");
        bt.removeClass("btn-success");
        bt.addClass("btn-primary");
    }
    $("#chat_dialog").modal('hide');
}

//發送聊天訊息
var showMessage = function(showId,message,is_i){
    if(message.data.length != 0){
        var li = $('<li class="bubbleItem clearfix">');
        var img = $('<img src="./man.png" height="35px;" style="float: '+is_i+';">');
        var span = $('<span class="bubble '+is_i+'Bubble">');
        var you_msg = $('<span>');
        you_msg.html(message.data);
        span.append(you_msg);
        var bottomLevel = $('<span class="bottomLevel">');
        span.append(bottomLevel);
        var bottomLevel = $('<span class="topLevel">');
        span.append(bottomLevel);
        li.append(img);
        var div = $("<div style='float:"+is_i+";max-width: 60%;'>");
        var name = $('<label style="font-size:12px;float:'+is_i+';margin-'+is_i+':10px;">'+message.id+'</label>');
        div.append(name);
        div.append($('<br/>'));
        div.append(span);
        li.append(div);
        $("#"+showId+" ul[name='bubbleDiv']").append(li);
        $("#"+showId + " textarea[name='message']").val("");
    }
}