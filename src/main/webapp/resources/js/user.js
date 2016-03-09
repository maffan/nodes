google.charts.load('current', {packages: ['corechart', 'gauge', 'line', 'table']});
google.charts.setOnLoadCallback(initiateCharts);

function initiateCharts(){
    startupFunctions.forEach(function(fun){
       fun(); 
    });    
}

function setPause(){
    var pause = $("#doPause").val();
    if(pause == 'false'){
        $("#doPause").val("true");
        $("#pauseBtn").removeClass("btn-success").addClass("btn-danger");
        $("#pauseBtn").html("Module updates disabled <span class='glyphicon glyphicon-play' aria-hidden='true'></span>");
    }else{
        $("#doPause").val("false");
        $("#pauseBtn").removeClass("btn-danger").addClass("btn-success");
        $("#pauseBtn").html("Module updates enabled <span class='glyphicon glyphicon-pause' aria-hidden='true'></span>");
    }

}
