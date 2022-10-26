
// 預設資料欄位
     var cols=[
            ['基本薪資', 0],
            ['職務津貼', 0],
            ['全勤獎金', 0],
            ['伙食津貼', 0],
        ];   
        

               
// 透過Ajax取得資料  
$.ajax({
	
        url: `/api/payslips/${id}/${year}/${month}`,
        dataType: 'json',
        success: function (data) {
			cols[0][1]=data.basicpay;
			cols[1][1]=data.workpay;
			cols[2][1]=data.attendancebonus;
			cols[3][1]=data.mealpay;
			
			// 計算總薪資
		    var totalpayInt=0;
		    for(i=0;i<cols.length;i++){
		      totalpayInt += cols[i][1];
		    }
		    
		    $('#tbodyPay').html("");			
			//表格加入欄位
            $('#tbodyPay').append(`<tr><td>基礎薪資</td><td>${data.basicpay.toLocaleString()}</td></tr>`);
            $('#tbodyPay').append(`<tr><td>職務津貼</td><td>${data.workpay.toLocaleString()}</td></tr>`);
            $('#tbodyPay').append(`<tr><td>全勤獎金</td><td>${data.attendancebonus.toLocaleString()}</td></tr>`);
            $('#tbodyPay').append(`<tr><td>伙食津貼</td><td>${data.mealpay.toLocaleString()}</td></tr>`);
            $('#tbodyPay').append(`<tr><td>總計</td><td>${totalpayInt.toLocaleString()}</td></tr>`);
                

    // 引用c3圓餅圖    
    var chart = c3.generate({

    bindto: '#chartPay',
    data: {
        columns:cols,        
        type : 'donut',
        onclick: function (d, i) { console.log("onclick", d, i); },
        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
        onmouseout: function (d, i) { console.log("onmouseout", d, i); },
        // 設定顏色
        colors: {
          基本薪資: '#49678D',
          職務津貼: '#71A4D8',
          全勤獎金: '#ACC8D6',
          伙食津貼: '#97AAB0'
        }
    },
    donut: {
        // 圓餅圖中的文字
        title: totalpayInt.toLocaleString(),
        
        label:{
          format: function(value, ratio, id) {
            return value;
          },
        show:false
        },
        
    },
    // 各類別放置下方
    legend: {
       position: 'bottom'
    },
    // 標籤格式化(千分位)
    tooltip: {
        format: {            
           value: d3.format(',') 
        }
    },
    // 整體圖形大小
    size:{
      width: 420,
      height: 420
    }

     
    });
    
    
}
});

