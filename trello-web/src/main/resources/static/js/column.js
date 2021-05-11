function deleteColumn(columnId) {
    console.log("HELLO")
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