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