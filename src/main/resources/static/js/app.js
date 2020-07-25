var stompClient = null;

function connect() {
    var e = new SockJS("/websocket");
    stompClient = Stomp.over(e);

    stompClient.connect({}, function(e) {
        setConnected(!0);

        // First subscribe to the public topic.
        stompClient.subscribe("/topic/public", function(e) {
            var message = JSON.parse(e.body);
            if (message.type === 'CHAT') {
                showMessage(message)
            } else {
                notification(message);
            }
        });

        // Then notify everyone (including yourself) that you joined the public topic.
        connectUser();
    })
}

// Close the connection when user disconnects
function disconnect() {

    // Disconnect from the stomp client.
    null !== stompClient && stompClient.disconnect(), setConnected(!1);

    $("#name").attr("disabled", !1);
    $("#badge").html(0);
}

// Add the user in the connection
function connectUser() {

    stompClient.send("/app/chat.user", {}, JSON.stringify({
        user: $("#name").val(),
        text: 'User has joined the chat',
        type: 'JOIN'
    }));

    // Lock the Input field name, since the user has already connected.
    $("#name").attr("disabled", !0);
}

// Send message to the connection
function sendMessage() {

    stompClient.send("/app/chat.message", {}, JSON.stringify({
        user: $("#name").val(),
        text: $("#message").val(),
        type: 'CHAT'
    }));

    // Setting the message field empty once the message has been sent.
    $("#message").val("");
}

function noOfUsersConnected(n) {
    $("#badge").html(n);
}

function setConnected(e) {
    $("#connect").prop("disabled", e);
    $("#disconnect").prop("disabled", !e)
}

// Notify all users about new user or if some user has left the chat.
function notification(message) {
    if (message.type === 'JOIN') {
        $("#userinfo").append("<tr><td class='new-user-joined'>" + capitalizeFirstLetter(message.user) + " joined!</td></tr>")
    } else if (message.type === 'LEAVE') {
        $("#userinfo").append("<tr><td class='new-user-joined'>" + capitalizeFirstLetter(message.user) + " left!</td></tr>")
    }
}

// Function is used to show message in chat
function showMessage(message) {
    $("#userinfo").append("<tr><td><span class='name-info'>" + capitalizeFirstLetter(message.user) + "</span> " + message.text + " <span class='time-info'>" + message.time + "</span></td ></tr > ")
}

// Toggle fields whenever user connects or disconnects.
function toggleFields(e) {
    $("#send").attr("disabled", e),
        $("#message").attr("disabled", e)
}

// utility method to caps first letter of string
function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

// Init method
$(function() {
    $("form").on("submit", function(e) {
        e.preventDefault()
    }),

    $(function() {
        $("#toggle-event").change(function() {
            $(this).prop("checked") ? connect() : disconnect(), toggleFields(!$(this).prop("checked"))
        })
    }),

    $("#send").click(function() {
        sendMessage()
    })
});