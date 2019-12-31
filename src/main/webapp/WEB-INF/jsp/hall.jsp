<%--
  Created by IntelliJ IDEA.
  User: ws
  Date: 2019/12/26
  Time: 下午12:22
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
    <style>
        body {
            text-align: center
        }
    </style>
    <script>
        $(function () {
            $.get("${pageContext.request.contextPath}/user/info", function (data, status) {
                var user = jQuery.parseJSON(data.user);
                if (status == "success") {
                    $("#userInfo").append("欢迎贵宾:" + user.account);
                }
            });
            refresh();
        });

        function refresh() {
            $.get("${pageContext.request.contextPath}/gameroom/get/active", function (data, status) {
                if (status == "success") {
                    $('#game_room_dg').datagrid({
                        data: JSON.parse(data.rooms),
                        columns: [[
                            {field: 'id', title: 'id', width: 100},
                            {field: 'createUserId', title: 'createUserId', width: 100},
                            {field: 'creationTime', title: 'creationTime', width: 200},
                            {field: 'status', title: 'status', width: 100}
                        ]]
                    });
                }
            });
        }

        function createRoom() {
            $.get("${pageContext.request.contextPath}/gameroom/create", function (data, status) {
                if (status == "success") {
                    refresh();
                }
            });
        }

        function intoRoom() {
            var rows = $('#game_room_dg').datagrid('getSelections');
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                alert(row.id);
            }
        }

    </script>
</head>
<body>
<div style="margin:20px 0 10px 0;"></div>
<div class="easyui-tabs" style="text-align:center;margin:1px auto;width:700px;height:500px">
    <div title="游戏大厅" style="padding:10px">
        <table id="game_room_dg" class="easyui-datagrid" title="当前在线用户" style="width:650px;height:450px"
               data-options="iconCls: 'icon-edit',toolbar: '#game_room_tb',singleSelect:true">

        </table>

        <div id="game_room_tb" style="height:auto">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
               onclick="createRoom()">创建房间</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true"
               onclick="intoRoom()">进入房间</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
               onclick="refresh()">刷新</a>
        </div>

    </div>
    <div title="个人信息" style="padding:10px" data-options="iconCls:'icon-man',selected:true">
        <div class="easyui-accordion" style="width:500px;height:300px;">
            <div title="个人资料" data-options="iconCls:'icon-man',selected:true" style="overflow:auto;padding:10px;">
                <h3 style="color:#0099FF;">
                    <div id="userInfo"></div>
                </h3>

            </div>
            <div title="战绩统计" style="padding:10px" data-options="
                iconCls:'icon-search',
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#dg').datagrid('reload');
					}
				}]">
                功能施工中...
                <table id="user_dg" class="easyui-datagrid">
                </table>
            </div>
        </div>
    </div>
    <div title="帮助" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
        功能施工中...<br>
        版权归国家所有
    </div>
</div>
</body>
</html>
