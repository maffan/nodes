<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script>
    google.charts.load('current', {packages: ['line']});
    google.charts.setOnLoadCallback(load_data);
    var socket = io("ws://localhost:8080/nodes/nodedataendpoint/${user.getMail()}/testNode");
    socket.onmessage = function(event){
        console.log(event);
    };
    
  function load_data(){
  $.ajax({
      //url: "webresources/datapoint/${moduleUser}/${moduleCollection[0]}",
      url: "webresources/datapoint/admin/testNode",
      headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
      type: 'GET',
      success: function (data) {
                        console.log(data);
                        var tableData = new google.visualization.DataTable();
                        tableData.addColumn('string', 'Date');   
                        tableData.addColumn('number','testNode' );
                        for(dataPointIndex in data){                           
                            tableData.addRow([data[dataPointIndex].datapointPK.time, data[dataPointIndex].data]);
                        }
                        drawChart(tableData);
                    }
  });
  }
  function drawChart(data) {
      var options = {
        chart: {
          title: 'Values reported in by this Node',
          subtitle: 'Presented in a cool fasion'
        },
        width: 900,
        height: 500,
        axes: {
          x: {
            0: {side: 'top'}
          }
        }
      };

      var chart = new google.charts.Line(document.getElementById('line_top_x'));

      chart.draw(data, options);
    }
  
    
</script>

<div id="line_top_x"/>