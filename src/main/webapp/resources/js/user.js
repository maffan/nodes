google.charts.load('current', {packages: ['corechart', 'gauge', 'line', 'table']});
google.charts.setOnLoadCallback(initiateCharts);



function initiateCharts(){
    startupFunctions.forEach(function(fun){
       fun(); 
    });    
}