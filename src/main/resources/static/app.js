var stompClient = null;

function setConnected(e) {
    $("#connect").prop("disabled", e), $("#disconnect").prop("disabled", !e)
}

function connect() {
    var e = new SockJS("/websocket-example");
    (stompClient = Stomp.over(e)).connect({}, function (e) {
        setConnected(!0), console.log("Connected: " + e), 
        sendName(),
        stompClient.subscribe("/topic/connect", function (e) {
            var n = JSON.parse(e.body);
            userAlert(n)
        }),
        stompClient.subscribe("/topic/message", function (e) {
            var n = JSON.parse(e.body);
            showMessage(n)
        }),
        stompClient.subscribe("/topic/stats", function (e) {
            var n = JSON.parse(e.body);
            updateBadge(n)
        })
    })
}

function disconnect() {
    null !== stompClient && stompClient.disconnect(),
        setConnected(!1),
        $("#name").attr("disabled", !1),
        console.log("Disconnected")
}

function sendName() {
    stompClient.send("/app/connect", {}, $("#name").val());
    $("#name").attr("disabled", !0);
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({
        name: $("#name").val(),
        content: $("#message").val()
    }));
    $("#message").val("");
}

function updateBadge(n) {
    $("#badge").html(n);
}

function userAlert(e) {
    $("#userinfo").append("<tr><td class='new-user-joined'>" + e.name + " " + e.content + "</td></tr>")
}

function showMessage(e) {
    if (e.type === "LEAVE") {
        $("#userinfo").append("<tr><td class='new-user-joined'>" + e.name + " " + e.content + "</td></tr>")
    } else {
        $("#userinfo").append("<tr><td><span class='name-info'>" + e.name + "</span> " + e.content + " <span class='time-info'>" + e.time + "</span></td ></tr > ")
    }
}

function toogleMessageFeilds(e) {
    $("#send").attr("disabled", e), $("#message").attr("disabled", e)
}
$(function () {
    $("form").on("submit", function (e) {
        e.preventDefault()
    }), $(function () {
        $("#toggle-event").change(function () {
            $(this).prop("checked") ? connect() : disconnect(), toogleMessageFeilds(!$(this).prop("checked"))
        })
    }), $("#send").click(function () {
        sendMessage()
    })
});