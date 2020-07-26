var stompClient = null;

function connectSocket() {
    var e = new SockJS("/websocket");
    stompClient = Stomp.over(e);

    stompClient.connect({}, function(e) {
        setConnected(!0);

        // First subscribe to the public topic.
        stompClient.subscribe("/topic/public", function(e) {
            var message = JSON.parse(e.body);
            if (message.type === 'CHAT') {
                showMessage(message);
            } else {
                notification(message);
            }
        }),
        stompClient.subscribe("/topic/stats", function(e) {
            connectedUser(e.body);
        });

        // Then notify everyone (including yourself) that you joined the public topic.
        connectUser();
    })
}

// Close the connection when user disconnects
function disconnectSocket() {

    // Disconnect from the stomp client.
    null !== stompClient && stompClient.disconnect(), setConnected(!1);

    $("#name").attr("disabled", !1);
    $("#name").val("");
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

function connectedUser(n) {
    $("#badge").html(n);
}

function setConnected(e) {
    $("#switch-on-off").prop("disabled", !e);
}

// Notify all users about new user or if some user has left the chat.
function notification(message) {
    if (message.type === 'JOIN') {
        $('#userinfo').append('<tr><td class="user-information">' + capitalizeFirstLetter(message.user) + ' joined!</td></tr>');
    } else if (message.type === 'LEAVE') {
        $('#userinfo').append('<tr><td class="user-information">' + capitalizeFirstLetter(message.user) + ' left!</td></tr>');
    }
}

// Function is used to show message in chat
function showMessage(message) {
    $("#userinfo").append("<tr><td><span class='name-info'>" + capitalizeFirstLetter(message.user) + "</span> " + message.text + " <span class='time-info'>" + message.time + "</span></td ></tr > ")
}

// Toggle fields whenever user connects or disconnects.
function toggleMessageFields(e) {
    $("#send").attr("disabled", e);
    $("#message").attr("disabled", e);
}

// utility method to caps first letter of string
function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

// Init method
$(function() {

    $("form").on("submit", function(e) {
        e.preventDefault();
    });

    // Connect to web-socket server and toggle message fields
    $("#switch-on-off").change(function() {
        toggleMessageFields(!$(this).prop("checked"));
        $(this).prop("checked") ? connectSocket() : disconnectSocket();
    });

    // Send the message
    $("#send").click(function() {
        if($('#message').val().length != 0) {
            sendMessage();
        }
    });

    // Enable the connect/disconnect only if name is not empty
    $('#name').keyup(function(){
        if($(this).val().length !=0)
            $('#switch-on-off').attr('disabled', false);
        else
            $('#switch-on-off').attr('disabled',true);
    });
});