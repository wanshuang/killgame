(function(){

  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);

  var roomId;

  var chat = {
    messageToSend : '',
    messageResponses: [
      'Why did the web developer leave the restaurant? Because of the table layout.',
      'How do you comfort a JavaScript bug? You console it.',
      'An SQL query enters a bar, approaches two tables and asks: "May I join you?"',
      'What is the most used language in programming? Profanity.',
      'What is the object-oriented way to become wealthy? Inheritance.',
      'An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol'
    ],
    init: function() {
      this.cacheDOM();
      this.bindEvents();
      this.loadMessage();
      this.render();
    },
    cacheDOM: function() {
      this.$chatHistory = $('.chat-history');
      this.$button = $('button');
      this.$textarea = $('#message-to-send');
      this.$chatHistoryList =  this.$chatHistory.find('ul');
    },
    bindEvents: function() {
      this.$button.on('click', this.addMessage.bind(this));
      this.$textarea.on('keyup', this.addMessageEnter.bind(this));
    },
    render: function() {
      this.scrollToBottom();
      if (this.messageToSend.trim() !== '') {
        var template = Handlebars.compile( $("#message-template").html());
        var context = { 
          messageOutput: this.messageToSend,
          time: this.getCurrentTime()
        };

        this.$chatHistoryList.append(template(context));
        this.scrollToBottom();
        this.$textarea.val('');
        
        // responses
        var templateResponse = Handlebars.compile( $("#message-response-template").html());
        var contextResponse = { 
          response: this.getRandomItem(this.messageResponses),
          time: this.getCurrentTime()
        };

        setTimeout(function() {
          this.$chatHistoryList.append(templateResponse(contextResponse));
          this.scrollToBottom();
        }.bind(this), 1500);
        
      }
      
    },
    loadMessage: function() {
      this.roomId = $("#roomId").val();
      $.get(localhostPaht+"/chat/message/getAll", { roomId: "1" }, function (data, status) {
        if (status == "success") {
          //对内容进行处理
          var messages = JSON.parse(data.messages);
          for(var index=0;index<messages.length;index++){
            var message = messages[index];
            var templateMe = Handlebars.compile( $("#message-template").html());
            var templateOther = Handlebars.compile( $("#message-response-template").html());
            var context = {
              messageOutput: message.content,
              time: message.creationTime,
              account: message.userAccount
            };
            $('.chat-history').find('ul').append(templateOther(context));
            $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
            $('#message-content').val('');
          }
        }
      });
    },
    
    addMessage: function() {
      this.messageToSend = $("#message-content").val();
      if(this.messageToSend.trim() == ""){
        return;
      }
      $.post(localhostPaht+"/chat/message/publish", { roomId: "1", content: this.messageToSend }, function (data, status) {
        if (status == "success") {
          var template = Handlebars.compile( $("#message-template").html());
          var message = JSON.parse(data.message);
          var context = {
            messageOutput: message.content,
            time: message.creationTime,
            account: message.userAccount
          };
          $('.chat-history').find('ul').append(template(context));
          $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
          $('#message-content').val('');
        }
      });
    },
    addMessageEnter: function(event) {
        // enter was pressed
        if (event.keyCode === 13) {
          this.addMessage();
        }
    },
    scrollToBottom: function() {
       this.$chatHistory.scrollTop(this.$chatHistory[0].scrollHeight);
    },
    getCurrentTime: function() {
      return new Date().toLocaleTimeString().
              replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
    },
    getRandomItem: function(arr) {
      return arr[Math.floor(Math.random()*arr.length)];
    }
    
  };
  
  chat.init();
  
  var searchFilter = {
    options: { valueNames: ['name'] },
    init: function() {
      var userList = new List('people-list', this.options);
      var noItems = $('<li id="no-items-found">No items found</li>');
      
      userList.on('updated', function(list) {
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