function addModule(username, collection, module){
    doPost("addmodule", username, collection, module)
}

function removeModule(username, collection, module){
    doPost("removemodule", username, collection, module);
}


function doPost(method, username, collection, module){
    $.post(window.location, {action: method, username: username, collection: collection, module: module}, function(data,status,xhr) {
        location.reload();
      });
};



$(function () {
  $('[data-toggle="tooltip"]').tooltip();
  $("form").submit(function( event ) {

  var values = $(this).serializeArray();
  event.preventDefault();
  addModule(values[0].value, values[1].value, values[2].value);
});
});