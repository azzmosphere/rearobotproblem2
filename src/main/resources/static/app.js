var stompClient = null;

// Current Grid Position of Robot
var currentGP = null;

/**
 * on a successful connection this will display true.
 */
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

/**
 * reposition and render robot.
 */
function showRoboGrid(message) {

    if (JSON.parse(message).status === "SUCCESS") {
        if (JSON.parse(message).message === "REPORT") {
            var gid = JSON.parse(message).xpos + "_" + JSON.parse(message).ypos;

            if (currentGP != null) {
                $('#' + currentGP).html("");
            }
            $(gid).prepend('<img id="robot" src="/images/robot.png" />');
            currentGP = gid;
            rotateImage("#robot", JSON.parse(message).direction);
        }
    }
}

/**
 *  rotate a image.
 */
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

    // Remove the old object before placing a new one.
 //   if (currentGP != null) {
 //       var coordinates = currentGP.split("_");
 //       setTimeout(function() {
 //           stompClient.send("/app/robot", {}, JSON.stringify({
 //               'type' : 'REMOVE',
 //               'xpos' : coordinates[0],
 //               'ypos' : coordinates[1]
 //           }));
 //       }, 30);
 //   }

    stompClient.send("/app/robot", {}, JSON.stringify({
        'xpos': $("#xpos").val(),
        'ypos': $("#ypos").val(),
        'perspective' : $("#direction").val(),
        'type' : 'PLACE'
    }));

    // Ideally we want the methods to be asyncronise
    // but since the async method is not supported
    // by all many browsers.  The time out
    // method is used delaying execution by 30ms
    // this should keep things relatively in
    // sync
    setTimeout(function() {stompClient.send("/app/robot", {}, JSON.stringify({
         'type' : "REPORT"
    }));}, 30);
}

function danceRobot(robotType) {
    stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : robotType
    }));
    setTimeout(function() {stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : "REPORT"
    }));}, 30);
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

function remove() {
    stompClient.send("/app/robot", {}, JSON.stringify({
        'type' : 'REMOVE'
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
    $("#remove").click(function() {remove();});
});


