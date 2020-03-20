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
<p>杀人游戏房 V0.0.1</p>
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
                    <textarea id="message-content"
                              placeholder="Type your message"
                              rows="3"></textarea>

            <button>Send</button>

        </div> <!-- end chat-message -->

    </div>
    　<!-- end chat -->

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
    <%--    <script src="/plugin/chat/dist/script.js"></script>--%>

    <script type="text/javascript">
        (function () {

            var chat = {
                messageToSend: '',
                messageResponses: [
                    'Why did the web developer leave the restaurant? Because of the table layout.',
                    'How do you comfort a JavaScript bug? You console it.',
                    'An SQL query enters a bar, approaches two tables and asks: "May I join you?"',
                    'What is the most used language in programming? Profanity.',
                    'What is the object-oriented way to become wealthy? Inheritance.',
                    'An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol'
                ],
                init: function () {
                    this.cacheDOM();
                    this.bindEvents();
                    this.getHistory();
                    this.render();
                },
                cacheDOM: function () {
                    this.$chatHistory = $('.chat-history');
                    this.$button = $('button');
                    this.$textarea = $('#message-content');
                    this.$chatHistoryList = this.$chatHistory.find('ul');
                },
                bindEvents: function () {
                    this.$button.on('click', this.addMessage.bind(this));
                    this.$textarea.on('keyup', this.addMessageEnter.bind(this));
                },
                render: function () {
                    this.scrollToBottom();
                    if (this.messageToSend.trim() !== '') {
                        var template = Handlebars.compile($("#message-template").html());
                        var context = {
                            messageOutput: this.messageToSend,
                            time: this.getCurrentTime()
                        };

                        this.$chatHistoryList.append(template(context));
                        this.scrollToBottom();
                        this.$textarea.val('');

                        // responses
                        var templateResponse = Handlebars.compile($("#message-response-template").html());
                        var contextResponse = {
                            response: this.getRandomItem(this.messageResponses),
                            time: this.getCurrentTime()
                        };

                        setTimeout(function () {
                            this.$chatHistoryList.append(templateResponse(contextResponse));
                            this.scrollToBottom();
                        }.bind(this), 1500);

                    }

                },
                getHistory: function () {
                    $.post( "${pageContext.request.contextPath}/chat/message/getAll", {roomId: ${roomId}}, function (data, status) {
                        if (status == "success") {
                            //对内容进行处理
                            var messages = JSON.parse(data.messages);
                            $('#lastId').val(data.lastId);
                            for (var index = 0; index < messages.length; index++) {
                                var message = messages[index];
                                var templateMe = Handlebars.compile($("#message-template").html());
                                var templateOther = Handlebars.compile($("#message-response-template").html());
                                var context = {
                                    messageOutput: message.content,
                                    time: message.creationTime,
                                    account: message.userAccount
                                };
                                if (message.userId == ${userId}) {
                                    $('.chat-history').find('ul').append(templateMe(context));
                                } else {
                                    $('.chat-history').find('ul').append(templateOther(context));
                                }
                                $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                                $('#message-content').val('');
                            }
                        }
                    });
                },

                addMessage: function () {
                    this.messageToSend = $("#message-content").val();
                    if (this.messageToSend.trim() == "") {
                        return;
                    }
                    $.post("${pageContext.request.contextPath}/chat/message/publish", {
                        roomId: ${roomId},
                        content: this.messageToSend
                    }, function (data, status) {
                        if (status == "success") {
                            var template = Handlebars.compile($("#message-template").html());
                            var message = JSON.parse(data.message);
                            var context = {
                                messageOutput: message.content,
                                time: message.creationTime,
                                account: message.userAccount
                            };
                            $('.chat-history').find('ul').append(template(context));
                            $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                            $('#message-content').val('');
                            $("#lastId").val(message.id);
                        }
                    });
                },
                addMessageEnter: function (event) {
                    //回车输入捕捉事件
                    if (event.keyCode === 13) {
                        this.addMessage();
                    }
                },
                scrollToBottom: function () {
                    this.$chatHistory.scrollTop(this.$chatHistory[0].scrollHeight);
                },
                getCurrentTime: function () {
                    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
                },
                getRandomItem: function (arr) {
                    return arr[Math.floor(Math.random() * arr.length)];
                }

            };

            chat.init();

            var searchFilter = {
                options: {valueNames: ['name']},
                init: function () {
                    var userList = new List('people-list', this.options);
                    var noItems = $('<li id="no-items-found">No items found</li>');

                    userList.on('updated', function (list) {
                        if (list.matchingItems.length === 0) {
                            $(list.list).append(noItems);
                        } else {
                            noItems.detach();
                        }
                    });
                }
            };

            searchFilter.init();

        })();
    </script>

    <script type="text/javascript" >
        function loadMessage() {
            var lastId = $("#lastId").val();
            if(lastId == 0){
                return;
            }
            var roomId = $("#roomId").val();
            $.post("${pageContext.request.contextPath}/chat/message/getLast", {roomId: roomId, lastId: lastId}, function (data, status) {
                if (status == "success") {
                    //对内容进行处理
                    var messages = JSON.parse(data.messages);
                    $('#lastId').val(data.lastId);
                    for (var index = 0; index < messages.length; index++) {
                        var message = messages[index];
                        var templateMe = Handlebars.compile($("#message-template").html());
                        var templateOther = Handlebars.compile($("#message-response-template").html());
                        var context = {
                            messageOutput: message.content,
                            time: message.creationTime,
                            account: message.userAccount
                        };
                        if (message.userId == $('#userId').val()) {
                            $('.chat-history').find('ul').append(templateMe(context));
                        } else {
                            $('.chat-history').find('ul').append(templateOther(context));
                        }
                        $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                    }
                }
            });
        }
        setInterval(loadMessage,1000);
    </script>
</div>
<input id="lastId" type="hidden"/>
<input id="userId" value="${userId}" type="hidden"/>
<input id="roomId" value="${roomId}" type="hidden"/>
</body>
</html>
