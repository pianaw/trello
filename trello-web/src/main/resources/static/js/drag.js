function onDragStartOuter(event) {
    event
        .dataTransfer
        .setData('text/plain', event.target.id);
}

function onDragOverOuter(event) {
    event.preventDefault();
}

function onDropOuter(event) {
    const id = event
        .dataTransfer
        .getData('text');

    const draggableElement = document.getElementById(id);
    const dropzone = event.currentTarget.id;

    if (!draggableElement.id.startsWith("outer-target") || !dropzone.startsWith("outer-target")) {
        return;
    }

    ajaxAboutColumnReplacement(dropzone.substring(13), draggableElement.id.substring(13), document.getElementById(dropzone).children[0].id.substring(16), draggableElement.children[0].id.substring(16));

    const draggableElementChild = draggableElement.children[0].outerHTML;
    const dropzoneChild = document.getElementById(dropzone).children[0].outerHTML;

    draggableElement.innerHTML = dropzoneChild;
    document.getElementById(dropzone).innerHTML = draggableElementChild;


    event
        .dataTransfer
        .clearData();
}


function onDragStartInner(event) {
    event
        .dataTransfer
        .setData('text/plain', event.target.id);

    // event
    //     .currentTarget
    //     .style
    //     .backgroundColor = 'black';
}

function onDragOverInner(event) {
    event.preventDefault();
}

function onDropInner(event) {
    const id = event
        .dataTransfer
        .getData('text');

    const draggableElement = document.getElementById(id);
    const dropzone = event.currentTarget.id;

    if (!draggableElement.id.startsWith("inner-target") || !dropzone.startsWith("inner-target")) {
        return;
    }

    if (draggableElement.parentElement !== document.getElementById(dropzone).parentElement) {
        return;
    }

    ajaxAboutCardReplacement(dropzone.substring(13), draggableElement.id.substring(13), document.getElementById(dropzone).children[0].id.substring(16), draggableElement.children[0].id.substring(16), draggableElement.parentElement.id.substring(16));

    const draggableElementChild = draggableElement.children[0].outerHTML;
    const dropzoneChild = document.getElementById(dropzone).children[0].outerHTML;

    draggableElement.innerHTML = dropzoneChild;
    document.getElementById(dropzone).innerHTML = draggableElementChild;

}
