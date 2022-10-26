

    // 預設資料欄位
    var colsSum=[
            ['原始薪資', 32000],
            ['扣除明細', 4780],
        ];
        
        
       

	
	

// 透過Ajax取得資料  
$.ajax({
	
        url: `/api/payslips/${id}/${year}/${month}`,
        dataType: 'json',
        success: function (data) {
			var totalpayInt = data.basicpay+data.workpay+data.attendancebonus+data.mealpay;
			var totalcostInt = data.laborinsurance+data.healthinsurance+data.laborpension+data.mealcost+data.leavecost;
			colsSum[0][1] = totalpayInt;
			colsSum[1][1] = totalcostInt;
			
			// 計算總薪資
		    var totalsumInt=totalpayInt-totalcostInt;
		    
		    $('#tbodySum').html("");			
			//表格加入欄位
            $('#tbodySum').append(`<tr><td>原始薪資</td><td>${totalpayInt.toLocaleString()}</td></tr>`);
            $('#tbodySum').append(`<tr><td>扣除明細</td><td>${totalcostInt.toLocaleString()}</td></tr>`);
            $('#tbodySum').append(`<tr><td>總計</td><td>${totalsumInt.toLocaleString()}</td></tr>`);
           

    
    // 引用c3圓餅圖    
    var chart = c3.generate({

    bindto: '#chartSum',
    data: {
        columns:colsSum,        
        type : 'donut',
        onclick: function (d, i) { console.log("onclick", d, i); },
        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
        onmouseout: function (d, i) { console.log("onmouseout", d, i); },
        // 設定顏色
        colors: {
          原始薪資: '#49678D',
          扣除明細: '#71A4D8',
        }
    },
    donut: {
        // 圓餅圖中的文字
        title: totalsumInt.toLocaleString(),
        
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
    
