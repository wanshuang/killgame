<%--
  Created by IntelliJ IDEA.
  User: ws
  Date: 2019/12/25
  Time: 下午6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic Panel - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="/jquery/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/jquery/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/jquery/demo/demo.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery/jquery.easyui.min.js"></script>
</head>
<body>

<div style="margin:20px 0;"></div>
<div align="center">
    <div class="easyui-panel" title="账号登录" style="width:500px">
        <div style="padding:10px 60px 20px 60px">
            <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
                <table cellpadding="5">
                    <tr>
                        <td>登录:</td>
                        <td><input class="easyui-textbox" type="text" name="account"
                                   data-options="required:true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td><input class="easyui-textbox" type="text" name="pwd"
                                   data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>切换语言:</td>
                        <td>
                            <select class="easyui-combobox" name="language">
                                <option value="ar">Arabic</option>
                                <option value="bg">Bulgarian</option>
                                <option value="ca">Catalan</option>
                                <option value="zh-cht">Chinese Traditional</option>
                                <option value="cs">Czech</option>
                                <option value="da">Danish</option>
                                <option value="nl">Dutch</option>
                                <option value="en" selected="selected">English</option>
                                <option value="et">Estonian</option>
                                <option value="fi">Finnish</option>
                                <option value="fr">French</option>
                                <option value="de">German</option>
                                <option value="el">Greek</option>
                                <option value="ht">Haitian Creole</option>
                                <option value="he">Hebrew</option>
                                <option value="hi">Hindi</option>
                                <option value="mww">Hmong Daw</option>
                                <option value="hu">Hungarian</option>
                                <option value="id">Indonesian</option>
                                <option value="it">Italian</option>
                                <option value="ja">Japanese</option>
                                <option value="ko">Korean</option>
                                <option value="lv">Latvian</option>
                                <option value="lt">Lithuanian</option>
                                <option value="no">Norwegian</option>
                                <option value="fa">Persian</option>
                                <option value="pl">Polish</option>
                                <option value="pt">Portuguese</option>
                                <option value="ro">Romanian</option>
                                <option value="ru">Russian</option>
                                <option value="sk">Slovak</option>
                                <option value="sl">Slovenian</option>
                                <option value="es">Spanish</option>
                                <option value="sv">Swedish</option>
                                <option value="th">Thai</option>
                                <option value="tr">Turkish</option>
                                <option value="uk">Ukrainian</option>
                                <option value="vi">Vietnamese</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
            </div>
        </div>
    </div>
</div>
<script>
    function submitForm() {
        $('#ff').form('submit', {
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            }
        });
    }

    function clearForm() {
        $('#ff').form('clear');
    }
</script>
</body>
</html>
