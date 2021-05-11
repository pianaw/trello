<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/modal.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="/js/talk-me.js"></script>
    <script TYPE="application/javascript">
        function createBoard() {
            let data = {
                "title": getElementById('title')
            }
            $.ajax({
                type: "POST",
                url: "/api/board",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",

                success: function (data) {
                    $('#cards').append("<div class=\"col s3\">\n" +
                        "                    <div class=\"my_card\" style=\"text-decoration: none; color: black\">\n" +
                        "                        <a href=\"/board/" + data['id'] + "\">"+ data['title'] +"</a>\n" +
                        "                    </div>\n" +
                        "                </div>");
                }
            });


            function getElementById(data) {
                return document.getElementById(data).value;
            }
        }
    </script>

    <style>
        a:hover {
            text-decoration: none;
            color: dodgerblue;
        }

        input:focus {
            color: black !important;
        }

        /* label underline focus color */
        input:focus {
            border-bottom: 1px solid white !important;
            box-shadow: 0 1px 0 0 white !important;
        }

        .my_card {
            height: 120px;
            background-color: rgba(255, 255, 255, 0.5);
            border-radius: 15px;
            padding: 15px;
            margin: 6px;
            font-size: 22px;
            margin-left: 0 !important;
            margin-right: 0 !important;
            font-family: "Sitka Display", fantasy;
        }

        .my_card:hover {
            background-color: rgb(255, 255, 255)
        }

        h6 {
            font-size: 20px;
        }

        .small-button {
            width: 25px;
            height: 25px;
            background-color: white;
            color: #9ba9cd;
            border-width: 0;
            border-radius: 5px;
        }

        .small-button:hover, .small-button:focus {
            background-color: rgba(255, 255, 255, 0.5);
        }
    </style>
</head>
<body style="background-color: #9ba9cd">
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 40px">
        <div class="col s4"></div>
        <div class="col s4 center-align"><img src="/img/white-logo.png" alt="Logo of Trello"
                                              style="margin: 1%; width: 80px">
        </div>
        <div class="col s4">
            <button class="small-button" style="margin-left: 380px; margin-top: 2%" id="myBtn" title="Создать доску">+</button>
        </div>
        <div id="myModal" class="modal" style="margin-top: 15%">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <input maxlength="30" id="title" style="width: 300px; margin-left: 30px" placeholder="Название доски">
                <button class="trello-button" style="height: 30px; width: 100px; margin-left: 30px; font-size: 14px"
                        onclick="createBoard()">Создать
                </button>
            </div>

        </div>
    </div>
</header>
<div class="row" style="margin-top: 20px; padding: 20px">
    <div class="col s3" style="margin-left: 0 !important; border-radius: 5px; width: 20%"><h6 style="margin-left: 2%;">
            <b>Обо мне</b></h6></div>
    <div class="col s9" style="margin-right: 0 !important; width: 78%; border-radius: 5px "><h6
                style="margin-left: 2%;"><b>Персональные доски</b></h6>
        <div id="cards" class="row" style="margin-left: 1%; margin-top: 30px; margin-right: 1%">
            <#list boards as board>
                <div class="col s3">
                    <div class="my_card" style="text-decoration: none; color: black">
                        <a href="/board/${board.getId()}/">${board.getTitle()}</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>


<script>
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }
</script>
</body>
</html>