<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">

<head>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PositionPicker/examples/positionPicker.html -->
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>地图拖拽选址</title>
    <style>
    
    .map {
        height: 100%;
        width: 60%;
        float: left;
    }
    
    #right {
        color: #444;
        background-color: #f8f8f8;
        width: 40%;
        float: left;
        height: 100%;
    }
    
    #start,
    #stop,
    #right input {
        margin: 4px;
        margin-left: 15px;
    }
    
    .title {
        width: 100%;
        background-color: #dadada
    }
    
    button {
        border: solid 1px;
        margin-left: 15px;
        background-color: #dadafa;
    }
    
    .c {
        font-weight: 600;
        padding-left: 15px;
        padding-top: 4px;
    }
    
    #lnglat,
    #address
    .title {
        padding-left: 15px;
    }
    </style>
</head>

<body>
    <div id="container" class="map" tabindex="0" style="height: 300px"></div>

  <script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=a7aabd2ca9d45e881ed2a9fc45b52680&plugin=AMap.ToolBar"></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
    <script language="javascript" src="statics/js/amap.js"></script>
    
    
    <script type="text/javascript">
    
    // 先获取到当前餐厅的地址
    var address =  document.getElementById('address').value;
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
        	
        });
     // 通过地址得到经纬度
         geocoder.getLocation(address,function(status,result){
           if(status=='complete' && result.geocodes.length){
             console.log(result.geocodes[0].location);
             var lng = result.geocodes[0].location.lng;
        	 var lat = result.geocodes[0].location.lat;
        	 console.log(lng + "," +lat);
        	 // 调用拖拽事件
        	 getPositionPicker(lng, lat);
           }
         });
         
       // 通过经纬度或取到地址
         var lnglatXY=[121.611945, 31.253899];//地图上所标点的坐标
         geocoder.getAddress(lnglatXY, function(status, result) {
             if (status === 'complete' && result.info === 'OK') {
                //获得了有效的地址信息:
                //即，result.regeocode.formattedAddress
            	 console.log(result.regeocode.formattedAddress);
             }else{
                //获取地址失败
             }
         });  

    });
    

/**
 * 地图拖拽事件
 */
function getPositionPicker (lng, lat) {
    // 参考的API：http://lbs.amap.com/api/javascript-api/reference-amap-ui/other/positionpicker
    // 拖拽选址Demo:http://lbs.amap.com/api/javascript-api/example/amap-ui-positionpicker/position-picker
    
   //加载PositionPicker，loadUI的路径参数为模块名中 'ui/' 之后的部分
    AMapUI.loadUI(['misc/PositionPicker'], function(PositionPicker) {
        var map = new AMap.Map('container', {
            zoom: 16,
            scrollWheel: false,
            center: [lng,lat]
        });
        
        
        var positionPicker = new PositionPicker({
        	//设定为拖拽地图模式，可选'dragMap'(拖拽地图)、'dragMarker'(拖拽点)，默认为'dragMap'
            mode: 'dragMap',
            map: map //指定地图对象,依赖地图对象
        });

        // 事件绑定、结果处理等
        // 绑定事件处理函数
        // 当拖拽结束后，拖拽选址组件会进行服务查询获得所选位置的地址、周边信息等数据，如果获取成功，将触发success事件，否则将触发fail事件。
        // 创建完拖拽选址组件的对象之后，我们需要为其绑定success和fail事件
        positionPicker.on('success', function(positionResult) {
            document.getElementById('address').value = positionResult.address;
        });
        positionPicker.on('fail', function(positionResult) {
        	// 海上或海外无法获得地址信息
            document.getElementById('address').value = '';
        });
        var onModeChange = function(e) {
            positionPicker.setMode(e.target.value)
        }
        // 开启拖拽选址
        positionPicker.start();
        map.panBy(0, 1);

        map.addControl(new AMap.ToolBar({
            liteStyle: true
        }))
    });
}
    </script>
</body>

</html>