// 引用c3圓餅圖

    // 資料來源
    var colsCost=[
			['伙食費', 1500],
            ['勞退', 1998],
			['請假扣款', 1500],
			['勞保費', 766],
            ['健保費', 516],
                        
        ];
        


       
// 透過Ajax取得資料  
$.ajax({
	
        url: `/api/payslips/${id}/${year}/${month}`,
        dataType: 'json',
        success: function (data) {
			colsCost[0][1]=data.mealcost;
			colsCost[1][1]=data.laborpension;
			colsCost[2][1]=data.leavecost;
			colsCost[3][1]=data.laborinsurance;
			colsCost[4][1]=data.healthinsurance;
			
			// 計算總薪資
		    var totalcostInt=0;
		    for(i=0;i<colsCost.length;i++){
		      totalcostInt += colsCost[i][1];
		    }
		    
		    $('#tbodyCost').html("");			
			//表格加入欄位
            $('#tbodyCost').append(`<tr><td>伙食費</td><td>${data.mealcost.toLocaleString()}</td></tr>`);
            $('#tbodyCost').append(`<tr><td>勞退</td><td>${data.laborpension.toLocaleString()}</td></tr>`);
            $('#tbodyCost').append(`<tr><td>請假扣款</td><td>${data.leavecost.toLocaleString()}</td></tr>`);
            $('#tbodyCost').append(`<tr><td>勞保費</td><td>${data.laborinsurance.toLocaleString()}</td></tr>`);
            $('#tbodyCost').append(`<tr><td>健保費</td><td>${data.healthinsurance.toLocaleString()}</td></tr>`);
            $('#tbodyCost').append(`<tr><td>總計</td><td>${totalcostInt.toLocaleString()}</td></tr>`);
                

    // 引用c3圓餅圖
    var chart = c3.generate({

    bindto: '#chartCost',
    data: {
        columns:colsCost,        
        type : 'donut',
        onclick: function (d, i) { console.log("onclick", d, i); },
        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
        onmouseout: function (d, i) { console.log("onmouseout", d, i); },
        // 設定顏色
        colors: {
          伙食費: '#49678D',
          勞退: '#71A4D8',
          請假扣款: '#ACC8D6',
          勞保費: '#97AAB0',
          健保費: '#C0C0C0',

        }
    },
    donut: {
        // 圓餅圖中的文字
        title: totalcostInt.toLocaleString(),
        
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
    

