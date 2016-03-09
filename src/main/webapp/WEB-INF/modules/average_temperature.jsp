<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    startupFunctions.push(function () {
        var collection = ${param.collectionId};
        var gauge = new google.visualization.Gauge(document.getElementById('gauges_div_${param.collectionId}'));
        var gaugeData = 0;
        
        function drawTable() {
            var data = google.visualization.arrayToDataTable([
                ['Label', 'Value'],
                ['Temperature', gaugeData]
              ]);
            
            if (!$('#doPause')[0].checked) {
                gauge.draw(data, {width: '100%', height: '100%'});
            }
        }

        function load_data_and_draw_collection() {
            var averageCall = $.ajax({
                    url: "webresources/datapoint/collection/${param.collectionId}/average/${param.resolution}",
                    headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                    type: 'GET'
                });
                
            $.when(averageCall).done(function (avg) {
                console.log("Collection: " + collection + " average temp: " + avg);
                gaugeData = avg;
                drawTable();
            });
        }

        
        var ws = new WebSocket("ws://localhost:8080/nodes/websocket/collection/" + collection);

        ws.onopen = function ()
        {
            console.log("Websocket for collection: " + collection + " connected");
        };

        ws.onmessage = function (data)
        {
            console.log("Received data for collection: " + collection);
            console.log(data);
            if ($('#doPause').val() == 'false') {
                load_data_and_draw_collection();
            }
        };

        ws.onclose = function ()
        {
            console.log("Websocket for collection: " + collection + " closed");
        };
        load_data_and_draw_collection();
        
    });
</script>
<div id="gauges_div_${param.collectionId}"></div>

