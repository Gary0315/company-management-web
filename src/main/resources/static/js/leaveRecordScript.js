
// 假單狀態設定顏色
$(document).ready(function(){

    $('.leavePersonal:contains(取消)')            
    .each(function(){
      $(this).css('color', 'gray');
    })

    $('.leavePersonal:contains(完成)')            
    .each(function(){
      $(this).css('color', 'green');
    })

    $('.leavePersonal:contains(審核)')            
    .each(function(){
      $(this).css('color', 'red');
    })


});