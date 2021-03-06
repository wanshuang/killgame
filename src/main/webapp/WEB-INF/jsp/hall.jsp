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
            gameTypeClose();
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
                            {field: 'userName', title: '创建用户', width: 100},
                            {field: 'creationTime', title: '创建时间', width: 200},
                            {field: 'gameName', title: '类型', width: 100},
                            {field: 'status', title: '状态', width: 100}
                        ]]
                    });
                }
            });
        }

        function gameTypeOpen() {
            $('#game_type_choose').dialog('open');
        }

        function gameTypeClose() {
            $('#game_type_choose').dialog('close');
        }

        function createRoom() {
            var typeId = $("#game_type_select").find("option:selected").val()
            if(typeId != null){
                $.post("${pageContext.request.contextPath}/gameroom/create", {typeId: typeId}, function (data, status) {
                    if (status == "success") {
                        gameTypeClose();
                        refresh();
                    }
                });
            }
        }

        function intoRoom() {
            var rows = $('#game_room_dg').datagrid('getSelections');
            if (rows.length > 0) {
                var row = rows[0];
                $(window).attr('location', '${pageContext.request.contextPath}/gameroom/into?id=' + row.id);
            }
            // for(var i=0; i<rows.length; i++){
            //     var row = rows[i];
            // }
        }

    </script>
</head>
<body>
<div style="margin:20px 0 10px 0;"></div>
<div class="easyui-tabs" style="text-align:center;margin:1px auto;width:700px;height:500px">
    <div title="游戏大厅" style="padding:10px">
        <table id="game_room_dg" class="easyui-datagrid" title="当前在线用户" style="width:650px;height:450px"
               data-options="iconCls: 'icon-edit',toolbar: '#game_room_tb',singleSelect:true ,selected:true">

        </table>

        <div id="game_room_tb" style="height:auto">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
               onclick="gameTypeOpen()">创建房间</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true"
               onclick="intoRoom()">进入房间</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
               onclick="refresh()">刷新</a>
        </div>

    </div>
    <div title="个人信息" style="padding:10px" data-options="iconCls:'icon-man'">
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
<div id="game_type_choose" class="easyui-dialog" title="选择房间类型" data-options="iconCls:'icon-add'" style="width:400px;height:100px;padding:10px">
    <div style="padding:10px">
        <select class="easyui-combobox" id="game_type_select" style="width:200px;">
            <option value="1">聊天室</option>
            <option value="2">杀人游戏</option>
        </select>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="createRoom()">Submit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="gameTypeClose()">Close</a>
    </div>
</div>
</body>
</html>
