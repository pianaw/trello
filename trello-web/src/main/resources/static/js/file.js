function uploadFile(columnId, cardId) {
    var formData = new FormData();
    formData.append('file', $('input[type=file]')[0].files[0]);
    $.ajax({
        url: "/api/board/${boardId}/col/" + columnId + "/card/" + cardId + "/file",
        data: formData,
        processData: false,
        contentType: false,
        type: 'POST',
    });
}
