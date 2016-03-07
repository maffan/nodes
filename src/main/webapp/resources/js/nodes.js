function addToCollection(username, node, collection){
    doPost("addtocollection", username, node, collection)
}

function deleteNode(username, node){
    doPost("deletenode", username, node, "");
}

function deleteCollection(username, collection){
    doPost("deletecollection", username, collection, "");
}

function removeFromCollection(username, node){
    doPost("removefromcollection", username, node, "")
}

function deleteDataPoints(username, node){
    doPost("deletedatapoints", username, node, "")
}

function doPost(method, username, value, value2){
    $.post(window.location, {action: method, username: username, value: value, value2: value2}, function(data,status,xhr) {
        location.reload();
      });
};

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})


