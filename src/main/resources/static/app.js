var groupid = null;
var stompClient = null;
var sendurl = "/app"
var fetchurl = "/topic"
var isPrivateModeEnabled = false;

function getName() {
    if (isPrivateModeEnabled) {
        return $('.name:last').val()
    } else {
        return $('.name:first').val()
    }
}

function reset() {
    disconnect()
    $('.name').val('')
    $('#userinfo').html('')
    toogleMessageFeilds(true)
    $('#groupid').val('').attr('readonly', false)
    $('.toggle-event').each(function () {
        $(this).bootstrapToggle('off')
        $(this).bootstrapToggle('disable');
    });
}

function private() {
    reset();
    $('#public').hide();
    $('#private').show();
    isPrivateModeEnabled = true;
    $('#room-code-box').show()
    $(".margin-zero > .toggle").css("cssText", "width: 82.7% !important; height: 40px");
}

function public() {
    reset();
    groupid = null
    $('#public').show();
    $('#private').hide();
    $('#room-code-box').hide()
    isPrivateModeEnabled = false;
    $(".margin-zero > .toggle").css("cssText", "width: 100% !important;height: 40px");
}

function setConnected(e) {
    $('#connect').prop('disabled', e);
    $('#disconnect').prop('disabled', !e)
}

function connect() {
    var e = new SockJS('/websocket-example');
    stompClient = Stomp.over(e);
    groupid = $('#groupid').val()
    if(isPrivateModeEnabled) {
        sendurl = "/app/" + groupid
        fetchurl = "/topic/" + groupid
    } else {
        sendurl = "/app"
        fetchurl = "/topic"
    }

    stompClient.connect({}, function (e) {
        setConnected(!0);
        sendName();
        stompClient.subscribe(fetchurl + '/connect', function (e) {
            var n = JSON.parse(e.body);
            userAlert(n)
        }),
            stompClient.subscribe(fetchurl + '/message', function (e) {
                var n = JSON.parse(e.body);
                showMessage(n)
            }),
            stompClient.subscribe('/topic/stats', function (e) {
                var n = JSON.parse(e.body);
                updateBadge(n)
            })
    })
}

function disconnect() {
    null !== stompClient && stompClient.disconnect(),
        setConnected(!1);
    $('.name').attr('disabled', !1);
    $('#badge').html(0);
}

function sendName() {
    stompClient.send(sendurl + '/connect', {}, getName());
    $('.name').attr('disabled', !0);
}

function sendMessage() {
    var message = $('#message').val()
    if (message != null && message.trim().length > 0) {
        stompClient.send(sendurl + '/message', {}, JSON.stringify({
            name: getName(),
            content: message
        }));
        $('#message').val('');
    }
}

function updateBadge(n) {
    $('#badge').html(n);
}

function userAlert(e) {
    $('#userinfo').append("<tr><td class='new-user-joined'>" + e.name + " " + e.content + "</td></tr>")
}

function showMessage(e) {
    if (e.type === 'LEAVE') {
        $('#userinfo').append("<tr><td class='new-user-joined'>" + e.name + " " + e.content + "</td></tr>")
    } else {
        $('#userinfo').append("<tr><td><span class='name-info'>" + e.name + "</span> " + e.content + " <span class='time-info'>" + e.time + "</span></td ></tr > ")
    }
}

function toogleMessageFeilds(e) {
    $('#send').attr('disabled', e),
    $('#message').attr('disabled', e)
    $('#generate-room-code').attr('disabled', !e)
}

$(function () {
    $('#private').hide()

    $('form').on('submit', function (e) {
        e.preventDefault()
    }), 

    $(function () {
        $('.toggle-event').change(function () {
            $(this).prop('checked') ? connect() : disconnect()
            toogleMessageFeilds(!$(this).prop('checked'))
        })
    }),

    $(function () {
        $('#toggle-chat-type').change(function () {
            $(this).prop('checked') ? private() : public()
        })
    }),

    $('#send').click(function () {
        sendMessage()
    }), 

    $('.name').on('keyup', function () {
        var object = $(this).val();
        if (object.length <= 0) {
            $('.toggle-event').bootstrapToggle('disable');
        } else {
            $('.toggle-event').bootstrapToggle('enable');
        }
    }),

    $('#private input[type=text]').on('keyup', function () {

        var groupid = $('input[name=groupid]').val().length;
        var username = $('input[name=username]').val().length;

        if (groupid <= 6 || username <= 0) {
            $('.toggle-event').bootstrapToggle('disable');
        } else {
            $('.toggle-event').bootstrapToggle('enable');
        }
    }) 

    $('.toggle-event').bootstrapToggle('disable'),

    $('#generate-room-code').click(function () {
        $('#groupid').val((new Date().valueOf()).toString(36)).attr('readonly', true)
    });
});
