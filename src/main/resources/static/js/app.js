let stompClient = null;

function connect() {
    const socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function (message) {
            respondMessage(JSON.parse(message.body).content);
        });
    });
}

function sendMessage() {
    const message = $('#input-message').val();

    $(".direct-chat-messages").append(`
          <div class="direct-chat-msg">
              <div class="direct-chat-infos clearfix">
                  <span class="direct-chat-name float-left">Uno</span>
              </div>
              <div class="direct-chat-text">
                  ${message}
              </div>
          </div>
      `);

    scrollToBottom();
    stompClient.send("/app/hello", {}, JSON.stringify({'content': message}));
    $('#input-message').val('');  // Clear the input field after sending the message
}

function respondMessage(message) {
    $(".direct-chat-messages").append(`
              <div class="direct-chat-msg right">
                  <div class="direct-chat-infos clearfix">
                      <span class="direct-chat-name float-right">Bot</span>
                  </div>
                  <div class="direct-chat-text">
                      ${message}
                  </div>
              </div>
          `);

    scrollToBottom();
}

function scrollToBottom() {
    const chatMessages = $(".direct-chat-messages");
    chatMessages.scrollTop(chatMessages.prop("scrollHeight"));
}

$(function () {
    $("#chat-form").on('submit', function (e) {
        e.preventDefault();
        sendMessage();
    });

    $("#chat-form button").click(function(e) {
        sendMessage();
    });
});