<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Доска</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/modal.css">
    <script src="/js/drag.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>
        function getInfoCard(columnId, cardId, title, description, deadline) {
            var modal = document.getElementById("myModal" + cardId);

            if (modal.style.display != 'block') {
                modal.style.display = 'block';
                $(".result").empty();

                $('.title').append(title);
                $('.descr').append(description);
                $('.deadline').append(deadline);
                $.ajax({
                    method: "GET",
                    url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/check_list",
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var checked = "";
                            if (data[i]['isDone'] === "true") {
                                checked = "checked";
                            }
                            var $tr = $('<tr/>');
                            $tr.append($('<td/>').html("<label><input id='ch" + i + "' type='checkbox' " + checked + "/><span> " + data[i]['description'] + "</span></label>"));
                            $('.check_lists').append($tr)
                        }
                    }
                });

                $.ajax({
                    method: "GET",
                    url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/comment",
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var mentions = "";
                            for (var j = 0; j < data[i]['mentions'].length; j++) {
                                mentions = mentions + "<a title='" + data[i]['mentions'][j]['email'] + "'>" + data[i]['mentions'][j]['name'] + "</a>, ";
                            }
                            $('.comments').append("<div style = \"border: 1px solid black; margin-top: 5px; padding: 10px; border-radius: 5px; background-color: white\">\n" +
                                "    <p style=\"font-size: 10px; margin: 0;\">" + data[i]['creationDate'] + "</p>\n" +
                                "    <p style=\"font-size: 16px; margin: 0;\"><b>" + data[i]['authorName'] + ": </b><br>" + mentions + data[i]['text'] + "</p> \n" +
                                "</div>");
                        }
                    }
                });

                $.ajax({
                    method: "GET",
                    url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/file",
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            $('.files').append("<p style='font-size: 14px; text-align: center;'><a download href='/api/board/" + ${boardId} + "/col/" + columnId + "/card/" + cardId + "/file/" + data[i]['id'] + "'>" + data[i]['name'] + "</a></p>")
                        }
                    }
                });

                $.ajax({
                    method: "GET",
                    url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/participants",
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            $('.participants').append("<a title='" + data[i]['email'] + "'>" + data[i]['name'] + "<br></a>")
                        }
                    }
                })
            }
        }

        // Or with jQuery
        $(document).ready(function () {
            $('.collapsible').collapsible();
        });

        function addBoardParticipant() {
            var data = {
                "email": document.getElementById("email").value
            }
            $.ajax({
                url: "/api/board/${boardId}",
                method: "PUT",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
        }
        function addCardParticipant(columnId, cardId) {
            const data = {
                "email": document.getElementById("email" + cardId).value
            };

            $.ajax({
                url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId,
                method: "PUT",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success : function (data) {
                    $('.participants').append("<a title='" + data['email'] + "'>" + data['name'] + "<br></a>")
                }
            })
        }

        function ajaxAboutCardReplacement(orderId1, orderId2, elemId1, elemId2, colId) {

            var data = {
                "orderId1": orderId1,
                "orderId2": orderId2,
                "elemId1": elemId1,
                "elemId2": elemId2
            }

            $.ajax({
                url: "/api/board/${boardId}/col/" + colId + "/card",
                method: "PATCH",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
        }

        function addCard(columnId) {
            var data = {
                "title": "New card",
                "description": "Its new card"
            }
            $.ajax({
                method: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                url: "/api/board/${boardId}/col/" + columnId + "/card",
                success: function (data) {
                    var elem = $('#outer-draggable-' + columnId);
                    $(elem).append("<div id=\"inner-target-"+data['orderId']+"\" ondragover=\"onDragOverInner(event);\"\n" +
                        "                         ondrop=\"onDropInner(event)\" ondragstart=\"onDragStartInner(event)\" class=\"trello-card-element\"\n" +
                        "                         draggable=\"true\">\n" +
                        "                        <div onclick=\"getInfoCard("+data['columnId']+", "+data['id']+", '"+data['title']+"', '"+data['description']+"', '"+data['deadline']+"');\"\n" +
                        "                             id=\"inner-draggable-"+data['id']+"\">\n" +
                        "                            "+data['title']+"\n" +
                        "                            <div id=\"myModal"+data['id']+"\" class=\"modal\" style=\"width: 700px; height: 400px;\">\n" +
                        "                                <!-- Modal content -->\n" +
                        "                                <div class=\"modal-content\" style=\"width: 700px; height: 400px\">\n" +
                        "                                    <span class=\"close\">x</span>\n" +
                        "                                    <div class=\"row\">\n" +
                        "                                        <div class=\"col s8\">\n" +
                        "                                            <p style=\"font-size: medium; margin-top: 2px\" class=\"result title\"></p>\n" +
                        "                                            <p style=\"font-size: small; margin-top: 2px\" class=\"result deadline\"\n" +
                        "                                            ></p>\n" +
                        "                                            <h6 style=\"font-size: 18px\">Описание</h6>\n" +
                        "                                            <p class=\"result descr\" style=\"font-size: medium; margin-top: 2px\"></p>\n" +
                        "                                            <div style=\"border-top:3px dotted black;width:400px; margin: 5px\"></div>\n" +
                        "                                            <h6 style=\"font-size: 18px\">Чек-листы</h6>\n" +
                        "                                            <table>\n" +
                        "                                                <div class=\"check_lists result\"></div>\n" +
                        "                                            </table>\n" +
                        "                                            <div style=\"border-top:3px dotted black;width:400px; margin: 5px\"></div>\n" +
                        "                                            <h6 style=\"font-size: 18px\">Прикрепленные файлы</h6>\n" +
                        "                                            <div class=\"result files\"></div>\n" +
                        "                                            <input type=\"file\"/>\n" +
                        "                                            <button onclick=\"uploadFile("+data['columnId']+", "+data['id']+")\">\n" +
                        "                                                Добавить\n" +
                        "                                                файл\n" +
                        "                                            </button>\n" +
                        "                                            <div style=\"border-top:3px dotted black;width:400px; margin: 10px\"></div>\n" +
                        "                                            <h6 style=\"font-size: 18px\">Коммнтарии</h6>\n" +
                        "                                            <textarea style=\"background-color: white\" id=\"text\"></textarea>\n" +
                        "                                            <button onclick=\"addComment("+data['columnId']+", "+data['id']+")\"\n" +
                        "                                                    type=\"submit\"\n" +
                        "                                                    style=\"width: 100px; height: 30px\" class=\"trello-button\">\n" +
                        "                                                Отправить\n" +
                        "                                            </button>\n" +
                        "                                            <div class=\"result comments\"></div>\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"col s4\">\n" +
                        "                                            <ul class=\"collapsible\">\n" +
                        "                                                <li>\n" +
                        "                                                    <div class=\"collapsible-header\">Статус</div>\n" +
                        "                                                    <div class=\"collapsible-body\">Текущий статус: <span>"+data['status']+"</span></div>" +
                        "                                                </li>\n" +
                        "                                                <li>\n" +
                        "                                                   <div class=\"collapsible-header\">Участники</div>\n" +
                        "                                                    <div class=\"collapsible-body\">\n" +
                        "<div class=\"result participants\"></div>" +
                        "                                                        <div>\n" +
                        "                                                            <input id=\"email"+data['id']+"\" style=\"background-color: rgba(0,0,0,0.4); width: 150px; height: 30px;\" placeholder=\"Email\">\n" +
                        "                                                            <button onclick=\"addCardParticipant(" + columnId + "," + data['id'] + ")\" style=\"background-color: white;\" type=\"submit\" class=\"small-button\"\n" +
                        "                                                                    title=\"Добавить участника\">+\n" +
                        "                                                            </button>\n" +
                        "                                                        </div>\n" +
                        "                                                    </div>" +
                        "                                                </li>\n" +
                        "                                            </ul>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>")
                }
            });
        }

        function changeCardStatus(columnId, cardId) {
            var status = document.getElementById("card-status-" + cardId).value;
            var data = {
                "status" : status
            }
            $.ajax({
                url : "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/status",
                method : "PATCH",
                data: JSON.stringify(data),
                dataType: "text",
                contentType: "application/json",
                success : function (data) {
                    $('#status-'+cardId).empty();
                    $('#status-'+cardId).append(status);

                }
            });
        }

        function deleteColumn(columnId) {
            $.ajax({
                url: "/api/board/${boardId}/col/" + columnId,
                method : "DELETE",
                success: function (data) {
                    document.getElementById("outer-draggable-" + columnId).parentElement.remove();
                }
            })
        }

        function ajaxAboutColumnReplacement(orderId1, orderId2, elemId1, elemId2) {

            var data = {
                "orderId1": orderId1,
                "orderId2": orderId2,
                "elemId1": elemId1,
                "elemId2": elemId2
            }

            $.ajax({
                url: "/api/board/${boardId}/col",
                method: "PATCH",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
        }

        function addColumn() {
            $.ajax({
                method: "POST",
                url: "/api/board/${boardId}/col",
                success: function (data) {
                    $('#outer').append("<div id=\"outer-target-" + data['id'] + "\" ondragover=\"onDragOverOuter(event);\" ondrop=\"onDropOuter(event);\" ondragstart=\"onDragStartOuter(event);\"  class=\"trello-card\" style=\"display: inline-block; white-space: normal\" draggable=\"true\">\n" +
                        "            <div id=\"outer-draggable-" + data['id'] + "\"><span class=\"delete\" onclick=\"deleteColumn("+data['id']+")\">х</span><br><button onclick=\"addCard(" + data['id'] + ")\" class=\"trello-button\"\n" +
                        "                        style=\"background-color: rgba(0,0,0,0); !important;\">Добавить новую карточку\n" +
                        "                </button></div></div>");
                }

            })
        }
        function addComment(columnId, cardId) {
            var data = {
                "text": document.getElementById('text').value
            }
            $.ajax({
                method: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/comment",
                success: function (data) {
                    $('.comments').append("<div style = \"border: 1px solid black; margin-top: 5px; padding: 10px; border-radius: 5px; background-color: white\">\n" +
                        "    <p style=\"font-size: 10px; margin: 0;\">" + data['creationDate'] + "</p>\n" +
                        "    <p style=\"font-size: 16px; margin: 0;\"><b>" + data['authorName'] + ": </b><br>" + data['text'] + "</p> \n" +
                        "</div>");
                }
            });
        }

        function uploadFile(columnId, cardId) {
            var formData = new FormData();
            formData.append('file', $('input[type=file]')[0].files[0]);
            $.ajax({
                url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/file",
                data: formData,
                processData: false,
                contentType: false,
                type: 'POST',
                success: function (data) {
                    $('.files').append("<p style='font-size: 14px; text-align: center;'><a download href='/api/board/" + ${boardId} + "/col/" + columnId + "/card/" + cardId + "/file/" + data['id'] + "'>" + data['name'] + "</a></p>")
                }
            });
        }


    </script>
    <style>
        div::-webkit-scrollbar {
            width: 0;
        }

        .small-button {

            width: 30px;
            height: 30px;
            color: #9ba9cd;
            border-width: 0;
            border-radius: 5px;
            background: url("/img/exit.png") no-repeat left center;
            background-size: 30px;
        }

        .collapsible-body {
            padding: 4px;
        }
    </style>
</head>
<body style="background-color: #a3cf93" scroll="yes">
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 40px">
        <div class="col s4"></div>
        <div class="col s4 center-align"><img src="/img/white-logo.png" alt="Logo of Trello"
                                              style="margin: 1%; width: 80px">
        </div>
        <div class="col s4">
            <a href="/board/${boardId}/chat">
                <button class="small-button" style="margin-left: 340px; margin-top: 2%; background: none; " id="myBtn2" title="В чат">
                    <img style="width: 25px;" src="/img/image_icon_chat_4_pic_512x512.png">
                </button>
            </a>
            <a href="/profile">
                <button class="small-button" style="margin-top: 2%" id="myBtn" title="В профиль">
                </button>
            </a>
        </div>
    </div>
</header>
<div style="text-align: center">
    <button onclick="addColumn()" class="trello-button"
            style="background-color: rgba(0,0,0,0); !important;">Добавить новую колонку
    </button>
</div>


<div id="outer" style="overflow-x: scroll; overflow-y: hidden; white-space: nowrap">
    <#list columns as column>
        <div id="outer-target-${column.getOrderId()}" ondragover="onDragOverOuter(event);" ondrop="onDropOuter(event);"
             ondragstart="onDragStartOuter(event);" class="trello-card"
             style="display: inline-block; white-space: normal" draggable="true">
            <div id="outer-draggable-${column.getId()}">
                <span class="delete" onclick="deleteColumn(${column.getId()})">&times;</span><br>
                <button onclick="addCard(${column.getId()})" class="trello-button"
                        style="background-color: rgba(0,0,0,0); !important;">Добавить новую карточку
                </button>
                <#list column.getCards() as card>
                    <div id="inner-target-${card.getOrderId()}" ondragover="onDragOverInner(event);"
                         ondrop="onDropInner(event)" ondragstart="onDragStartInner(event)" class="trello-card-element"
                         draggable="true">
                        <div onclick="getInfoCard(${column.getId()}, ${card.getId()}, '${card.getTitle()}', '${card.getDescription()}', '${card.getDeadline()}');"
                             id="inner-draggable-${card.getId()}">
                            ${card.getTitle()}
                            <div id="myModal${card.getId()}" class="modal" style="width: 700px; height: 400px;">
                                <!-- Modal content -->
                                <div class="modal-content" style="width: 700px; height: 400px">
                                    <span class="close">&times;</span>
                                    <div class="row">
                                        <div class="col s8">
                                            <p style="font-size: medium; margin-top: 2px" class="result title"></p>
                                            <p style="font-size: small; margin-top: 2px" class="result deadline"
                                            ></p>
                                            <h6 style="font-size: 18px">Описание</h6>
                                            <p class="result descr" style="font-size: medium; margin-top: 2px"></p>
                                            <div style="border-top:3px dotted black;width:400px; margin: 5px"></div>
                                            <h6 style="font-size: 18px">Чек-листы</h6>
                                            <table>
                                                <div class="check_lists result"></div>
                                            </table>
                                            <div style="border-top:3px dotted black;width:400px; margin: 5px"></div>
                                            <h6 style="font-size: 18px">Прикрепленные файлы</h6>
                                            <div class="result files"></div>
                                            <input type="file"/>
                                            <button onclick="uploadFile(${column.getId()}, ${card.getId()})">
                                                Добавить
                                                файл
                                            </button>
                                            <div style="border-top:3px dotted black;width:400px; margin: 10px"></div>
                                            <h6 style="font-size: 18px">Комментарии</h6>
                                            <textarea style="background-color: white" id="text"></textarea>
                                            <button onclick="addComment(${column.getId()}, ${card.getId()})"
                                                    type="submit"
                                                    style="width: 100px; height: 30px" class="trello-button">
                                                Отправить
                                            </button>
                                            <div class="result comments"></div>
                                        </div>
                                        <div class="col s4">
                                            <ul class="collapsible">
                                                <li>
                                                    <div class="collapsible-header">Статус</div>
                                                    <div class="collapsible-body">Текущий статус:
                                                        <div id="status-${card.getId()}">${card.getStatus()}</div>
                                                        <label for="card-status-${card.getId()}"></label>
                                                        <select id="card-status-${card.getId()}" name="status">
                                                            <option value="WAITING" selected>В ожидании</option>
                                                            <option value="EXPIRED">Просрочено</option>
                                                            <option value="PROCESS">В процессе</option>
                                                            <option value="DONE">Готово</option>
                                                            <option value="ARCHIVE">В архив</option>
                                                        </select>
                                                        <button onclick="changeCardStatus(${column.getId()}, ${card.getId()})"
                                                                style="width: auto; background-color: white;"
                                                                type="submit" class="small-button"
                                                                title="Сменить статус">Сменить
                                                        </button>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="collapsible-header">Участники</div>
                                                    <div class="collapsible-body">
                                                        <div>
                                                            <input id="email${card.getId()}"
                                                                   style="background-color: rgba(0,0,0,0.4); width: 150px; height: 30px;"
                                                                   placeholder="Email">
                                                            <button onclick="addCardParticipant(${column.getId()}, ${card.getId()})"
                                                                    style="background-color: white;" type="submit"
                                                                    class="small-button"
                                                                    title="Добавить участника">+
                                                            </button>
                                                        </div>
                                                        <div class="result participants"></div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </#list>
</div>


<div style="margin: 20px">
    <input id="email" style="background-color: rgba(0,0,0,0.4); width: 200px; height: 30px;" placeholder="Email">
    <button onclick="addBoardParticipant()" style="background-color: white;" type="submit" class="small-button"
            title="Добавить участника">+
    </button>
</div>
<script src="/js/drag.js"></script>
<script>
    var span = document.getElementsByClassName("close");

    var modal = document.getElementsByClassName("modal")

    window.onclick = function (event) {
        for (var i = 0; i < span.length; i++) {
            if (event.target === span[i]) {
                $(".result").empty();
                modal[i].style.display = "none";
            }
        }
    }
</script>
</body>
</html>