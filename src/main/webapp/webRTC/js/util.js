//將時間戳轉換日期格式
function timeToYYYY_MM_DD_HH_mm_ss(timestamp) {
        if(("" + timestamp).length == 10){
            timestamp = timestamp * 1000;
        }
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() < 10 ?  '0'+date.getDate()+ ' ' : date.getDate()+ ' ';
        var h = date.getHours() < 10 ? '0'+date.getHours()+ ':' : date.getHours()+ ':';
        var m = date.getMinutes() < 10 ? '0'+date.getMinutes()+ ':' : date.getMinutes()+ ':';
        var s = date.getSeconds()< 10 ? '0'+date.getSeconds() : date.getSeconds();
        return Y+M+D+h+m+s;
}
 

function ab16str(buf) {
    return String.fromCharCode.apply(null, new Uint16Array(buf));
}
  

function str16ab(str) {
  var buf = new ArrayBuffer(str.length * 2); 
  var bufView = new Uint16Array(buf);
  for (var i = 0, strLen = str.length; i < strLen; i++) {
    bufView[i] = str.charCodeAt(i);
  }
  return buf;
}


String.prototype.getBytesLength = function() {
  var totalLength = 0;
  var charCode;
  for (var i = 0; i < this.length; i++) {
    charCode = this.charCodeAt(i);
    if (charCode < 0x007f)  {
        totalLength++;
    } else if ((0x0080 <= charCode) && (charCode <= 0x07ff))  {
        totalLength += 2;
    } else if ((0x0800 <= charCode) && (charCode <= 0xffff))  {
        totalLength += 3;
    } else{
        totalLength += 4;
    }
  }
  return totalLength;
}