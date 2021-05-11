<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/modal.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/talk-me.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script>
        var stompClient = null;

        function setConnected(connected) {
            $("#connect").prop("disabled", connected);
            $("#disconnect").prop("disabled", !connected);
            if (connected) {
                $("#conversation").show();
            } else {
                $("#conversation").hide();
            }
            $("#messages").html("");
        }

        function connect() {
            var socket = new SockJS('/gs-guide-websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/${boardId}', function (message) {
                    showMessages(JSON.parse(message.body));
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
            stompClient.send("/app/chat/${boardId}", {}, JSON.stringify({'text': $("#text").val()}));
        }

        function showMessages(message) {
            if (message['author_name'] != '${authorName}') {
                $("#messages").append("<tr><td style='padding: 5px;'><div style='padding: 8px; background: dodgerblue; display: inline-block;'><b>" + message['author_name'] + ":</b> " + message['text'] + "</div><tr/></td>");
            } else {
                $("#messages").append("<tr><td style='padding: 5px; text-align: right'><div style='padding: 8px; background: dodgerblue; display: inline-block;'><b>Вы: " + "</b> " + message['text'] + "</div><tr/></td>");
            }
        }

        $(function () {
            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            $("#connect").click(function () {
                connect();
            });
            $("#disconnect").click(function () {
                disconnect();
            });
            $("#send").click(function () {
                sendName();
            });
        });
    </script>
    <style>
        .small-button {

            width: 30px;
            height: 30px;
            color: #9ba9cd;
            border-width: 0;
            border-radius: 5px;
            background: url("/img/exit.png") no-repeat left center;
            background-size: 30px;
        }
    </style>
</head>
<body onload="connect()" style="background-color: #a3cf93">
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 40px">
        <div class="col s4"></div>
        <div class="col s4 center-align"><img src="/img/white-logo.png" alt="Logo of Trello"
                                              style="margin: 1%; width: 80px">
        </div>
        <div class="col s4">
            <a href="/board/${boardId}">
                <button class="small-button" style="margin-left: 380px; margin-top: 2%;" id="myBtn" title="В профиль">
                </button>
            </a>
        </div>
    </div>
</header>

<div class="row">
    <div class="col s4"></div>
    <div class="col s4">
        <div id="conversation" style="max-height: 400px; overflow-x: hidden; overflow-y: scroll; white-space: nowrap">
            <div style="border: 1px solid black; margin-top: 5px; padding: 10px; border-radius: 5px; background-color: white">
                <table id="messages"></table>
            </div>
        </div>
        <form>
            <textarea style="background-color: white; margin-top: 10px;" id="text"></textarea>
            <button id="send"
                    type="submit"
                    style="width: 100px; height: 30px" class="trello-button">
                Отправить
            </button>
        </form>
    </div>
    <div class="col s4"></div>
</div>
</body>
</html>