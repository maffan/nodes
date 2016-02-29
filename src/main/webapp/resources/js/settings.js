$(document).ready(function(){
    
    $("#genHash").click(function() {
        var current_date = (new Date()).valueOf().toString();
        var random = Math.random().toString();
        var hash = CryptoJS.MD5(current_date+random);
        $("#hash").val(hash);
      });
});


