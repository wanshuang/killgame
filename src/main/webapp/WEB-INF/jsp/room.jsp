<%--
  Created by IntelliJ IDEA.
  User: ws
  Date: 2019/12/31
  Time: 下午5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>响应党的号召</title>
    <link rel="stylesheet" type="text/css" href="/jquery/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/jquery/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/jquery/demo/demo.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery/jquery.easyui.min.js"></script>
</head>
<body>
<p>聊天房 V0.0.1</p>
<div style="margin:20px 0;"></div>
<div class="easyui-layout" style="width:700px;height:350px;">

    <div data-options="region:'west',split:true" title="可隐藏" style="width:100px;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="当前用户" style="padding:10px;">
                当前用户
            </div>
            <div title="身份信息" data-options="selected:true" style="padding:10px;">
                身份信息
            </div>
            <div title="帮助" style="padding:10px">
                帮助
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'信息',iconCls:'icon-ok'">

    </div>
</div>
</body>
</html>
