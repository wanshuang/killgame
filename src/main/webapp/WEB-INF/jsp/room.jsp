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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="/plugin/chat/dist/style.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery/jquery.easyui.min.js"></script>
</head>
<body>
<p>聊天房 V0.0.1</p>
<div style="margin:20px 0;"></div>
<div class="easyui-layout" style="text-align:center;margin:1px auto;width:700px;height:800px;">

    <div data-options="region:'west',split:true" title="可隐藏" style="width:150px;">
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
    <div class="chat" data-options="region:'center',title:'信息'">
        <!-- partial:index.partial.html -->

        <div class="chat-history">
            <ul>
                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:10 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>

                    </div>
                    <div class="message other-message float-right">
                        Hi Vincent, how are you? How is the project coming along?
                    </div>
                </li>

                <li>
                    <div class="message-data">
                        <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
                        <span class="message-data-time">10:12 AM, Today</span>
                    </div>
                    <div class="message my-message">
                        Are we meeting today? Project has been already finished and I have results to show you.
                    </div>
                </li>

                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:14 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>

                    </div>
                    <div class="message other-message float-right">
                        Well I am not sure. The rest of the team is not here yet. Maybe in an hour or so? Have
                        you faced any problems at the last phase of the project?
                    </div>
                </li>

                <li>
                    <div class="message-data">
                        <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
                        <span class="message-data-time">10:20 AM, Today</span>
                    </div>
                    <div class="message my-message">
                        Actually everything was fine. I'm very excited to show this to our team.
                    </div>
                </li>

                <!-- 正在输入的样式
                <li>
                    <div class="message-data">
                        <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
                        <span class="message-data-time">10:31 AM, Today</span>
                    </div>
                    <i class="fa fa-circle online"></i>
                    <i class="fa fa-circle online" style="color: #AED2A6"></i>
                    <i class="fa fa-circle online" style="color:#DAE9DA"></i>
                </li>
                -->

            </ul>

        </div> <!-- end chat-history -->

        <div class="chat-message clearfix" data-options="frozen:true" style="height:100px;">
                    <textarea id="message-content" name="message-to-send" id="message-to-send" placeholder="Type your message"
                              rows="3"></textarea>

            <button>Send</button>

        </div> <!-- end chat-message -->

    </div>　<!-- end chat -->

    <script id="message-template" type="text/x-handlebars-template">
        <li class="clearfix">
            <div class="message-data align-right">
                <span class="message-data-time">{{time}}</span> &nbsp; &nbsp;
                <span class="message-data-name">{{account}}</span> <i class="fa fa-circle me"></i>
            </div>
            <div class="message other-message float-right">
                {{messageOutput}}
            </div>
        </li>
    </script>

    <script id="message-response-template" type="text/x-handlebars-template">
        <li>
            <div class="message-data">
                <span class="message-data-name"><i class="fa fa-circle online"></i> {{account}}</span>
                <span class="message-data-time">{{time}}</span>
            </div>
            <div class="message my-message">
                {{messageOutput}}
            </div>
        </li>
    </script>
    <script src='/plugin/chat/dist/handlebars.min.js'></script>
    <script src='/plugin/chat/dist/list.min.js'></script>
    <script src="/plugin/chat/dist/script.js"></script>

</div>
</body>
</html>
