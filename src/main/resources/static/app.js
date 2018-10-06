var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({ 'name': $("#name").val() }));
    $("#name").val('');
}

function showGreeting(message) {
    $("#userinfo").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $(function () {
        $('#toggle-event').change(function () {
            if ($(this).prop('checked')) {
                connect();
                $('input[type="submit"]').removeAttr('disabled');
            } else {
                disconnect();
            }
        })
    })
    $("#send").click(function () {
        sendName();
    });
});