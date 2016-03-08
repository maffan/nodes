<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    var nodes = ${param.moduleNodeList}
    $(function() {       
        nodes.forEach(function(node){
        var div = document.createElement('div');
        div.id = node;
        $('#nodes').append(div);
        });
    });
    
    google.charts.load('current', {packages: ['line']});
    google.charts.setOnLoadCallback(load_data);
      function drawChart(data, node) {
      var options = {
        axes: {
          x: {
            0: {side: 'top'}
          }
        }
      };

      var chart = new google.charts.Line(document.getElementById(node));

      chart.draw(data, options);
    }
    
    nodes.forEach(function(node){
        var ws = new WebSocket("ws://localhost:8080/nodes/websocket/${param.moduleUser}/" + node);
        ws.onopen = function()
        {
           console.log("Websocket for " + node + " connected");
        };

        ws.onmessage = function (data) 
        { 
            console.log("Received data for " + node);
            console.log(data);
            load_data();
        };

        ws.onclose = function()
        { 
           console.log("Websocket for " + node + " closed");
        };
    });
    
    
  function load_data(){
      nodes.forEach(function(node){
          $.ajax({
            url: "webresources/datapoint/${param.moduleUser}/" + node + "/${param.resolution}",
            //url: "webresources/datapoint/admin/testNode",
            headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
            type: 'GET',
            success: function (data) {
                              console.log(data);
                              var tableData = new google.visualization.DataTable();
                              tableData.addColumn('string', 'Date');   
                              tableData.addColumn('number', node );
                              for(dataPointIndex in data){                           
                                  tableData.addRow([data[dataPointIndex].datapointPK.time, data[dataPointIndex].data]);
                              }
                              drawChart(tableData, node);
                          }
        });
      });
  }

  
    
</script>

<div id="nodes"/>
