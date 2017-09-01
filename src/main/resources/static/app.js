var stompClient = null;
var currentGP = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#robodance").show();
        $("#board").show();
    }
    else {
        $("#robodance").hide();
        $("#board").hide();
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
                showRoboGrid(roboDance.body);
            });
    });
}

function showRoboGrid(message) {

    $("#robogrid").show();
    $("#robogrid").html("<tr><td>" + message + "</td></tr>");

    if (JSON.parse(message).status === "SUCCESS") {
        if (JSON.parse(message).message === "REPORT") {
            var gid = "#" + JSON.parse(message).xpos + "_" + JSON.parse(message).ypos;

            if (currentGP != null) {
                $(currentGP).html("");
            }
            $(gid).prepend('<img id="robot" src="/images/robot.png" />');
            currentGP = gid;
            rotateImage("#robot", JSON.parse(message).direction);
        }
    }
}

function rotateImage(tag, direction) {
    var angle = 0;
    if (direction === "EAST") {
        angle = 90;
    }
    else if (direction === "SOUTH") {
        angle = 180;
    }
    else if (direction === "WEST") {
        angle = 270;
    }

    $(tag).rotate(angle);
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

    stompClient.send("/app/robot", {}, JSON.stringify({
         'type' : "REPORT"
    }));
}

function danceRobot(robotType) {
    stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : robotType
    }));
    stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : "REPORT"
    }));
}

function move() {
    danceRobot("MOVE");
}

function left() {
    danceRobot("LEFT");
}

function right() {
    danceRobot("RIGHT");
}

function report() {
    stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : 'REPORT'
    }));
}


/*
 * start the robot application, this calls the main methods above.
 */
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#place").click(function() {place();} );
    $("#move").click(function() {move();});
    $("#left").click(function() {left();});
    $("#right").click(function() {right();});
    $("#report").click(function() {report();});
});


