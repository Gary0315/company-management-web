function displayDiv(bntName) {
    var x = document.getElementById(bntName);
    if (x.style.display === "none" || x.style.display === "") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

$('#tool').on('focus', function () {
    $('#divTool').css('display', 'none');
});

$('#annoucement').on('focus', function () {
    $('#divAnnoucement').css('display', 'none');
});

$('#leave').on('focus', function () {
    $('#divLeave').css('display', 'none');
});

$('#package').on('focus', function () {
    $('#divPackage').css('display', 'none');
});

$('#order').on('focus', function () {
    $('#divOrder').css('display', 'none');
});


var positionId= $.cookie('session_position');
console.log("positionId="+positionId);
if(positionId==1){
	$('.boss').css('display','block');
}

var deptId=$.cookie('session_dept');
console.log("deptId="+deptId);
if(deptId==2){
	$('.personnel').css('display','block');
}
if(deptId==3){
	$('.generalAffairs').css('display','block');
	$('.receiveRoom').css('display','block');
}

  
