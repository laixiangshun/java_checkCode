<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../included/bootstrap_lib.jsp" %>
<%@ include file="../included/taglib_lib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>验证码</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div>
    	<img id="img" src="${path }/index2.html"/>
    	<a href="#" onclick="javascript:changeImg();">看不清？</a>
    </div>
    <script type="text/javascript">
    	$(document).ready(function(){
    		
    	});
    	function changeImg(){
    	//加时间戳，为了使每次加载图片不一致，让浏览器不读缓存
    		var url="${path}/index2.html?date="+new Date().getTime();
    		$("#img").attr("src",url);
    	}
    </script>
  </body>
</html>
