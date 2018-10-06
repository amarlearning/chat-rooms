var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        sendName();
        stompClient.subscribe('/topic/user', function (response) {
            newUserAlert(JSON.parse(response.body).name);
        });
        stompClient.subscribe('/topic/message', function (response) {
            var userResponse = JSON.parse(response.body);
            showMessage(userResponse.name, userResponse.content, userResponse.time);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#name").attr('disabled', false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({ 'name': $("#name").val() }));
    $("#name").attr('disabled', true);
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({ 
        'name': $("#name").val(),
        'content': $("#message").val() 
    }));
    
    $("#message").val('');
}

function newUserAlert(name) {
    $("#userinfo").append("<tr><td class='new-user-joined'>" + name + "</td></tr>");
}
function showMessage(name, message, time) {
    $("#userinfo").append("<tr><td><span class='name-info'>" + name + "</span> " + message +" <span class='time-info'>" + time + "</span></td ></tr > ");
}

function toogleMessageFeilds(isConnected) {
    $('#send').attr('disabled', isConnected);
    $('#message').attr('disabled', isConnected);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $(function () {
        $('#toggle-event').change(function () {
            if ($(this).prop('checked')) {
                connect();
            } else {
                disconnect();
            }
            toogleMessageFeilds(!$(this).prop('checked'));
        })
    })
    $("#send").click(function () {
        sendMessage();
    });
});