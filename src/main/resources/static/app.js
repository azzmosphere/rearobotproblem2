var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#robodance").show();
    }
    else {
        $("#robodance").hide();
    }
    $("#robogrid").html("");
}

/**
 * connect to stomp server.
 */
function connect() {
    var socket = new SockJS('/toy-robot');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/toyrobot', function (roboDance) {
                showRoboGrid(JSON.parse(roboDance.body).content);
            });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function place() {
    stompClient.send("/app/robot", {}, JSON.stringify({
        'xpos': $("#xpos").val(),
        'ypos': $("#ypos").val(),
        'perspective' : $("#direction").val(),
        'type' : 'PLACE'
    }));
}


/*
 * start the robot application, this calls the main methods above.
 */
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#place" ).click(function() {place();} );
});

$(document).ready(function(){
    $("#robodance").hide();
});
