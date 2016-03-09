google.charts.load('current', {packages: ['gauge', 'line', 'table']});
google.charts.setOnLoadCallback(initiateCharts());

var startupFunctions = [];

function initiateCharts(){
    startupFunctions.forEach(function(fun){
       fun(); 
    });    
}