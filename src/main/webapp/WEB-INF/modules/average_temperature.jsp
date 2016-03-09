<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    startupFunctions.push(gauges-${collectionId});

    var gauges-${collectionId} = {
       
    }
    

            function initCharts() {
            var ws = new WebSocket("ws://localhost:8080/nodes/websocket/${param.collectionId}");
            ws.chart = new google.charts.Line(document.getElementById(gauges-${collectionId}));
            ws.onopen = function ()
            {
                console.log("Websocket for " + gauges-${collectionId} + " connected");

            };

            ws.onmessage = function (data)
            {
                console.log("Received data for " + collection);
                console.log(data);
                load_data_and_draw_node(ws.chart, collection, false);
            };

            ws.onclose = function ()
            {
                console.log("Websocket for " + collection + " closed");
            };

            load_data_and_draw_node(ws.chart, collection, true);
        };
    
    
    function drawChart-${param.collectionId}(chart, data, init) {
        var options = {
            animation: {
                duration: 1000,
                easing: 'linear'
            },
            axes: {
                x: {
                    0: {side: 'top'}
                }
            }
        };
        if (init) {
            options.animation.duration = 1000;
            options.hAxis = {};
            options.hAxis.baseline = 0;
            options.vAxis = {};
            options.vAxis.baseline = 0;
        }
        chart.draw(data, options);
    }



    function load_data_and_draw_node(chart, node, init) {
        $.ajax({
            url: "webresources/datapoint/${param.moduleUser}/${param.collectionId}",
            //url: "webresources/datapoint/admin/testNode",
            headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
            type: 'GET',
            success: function (data) {
                console.log(data);
                var tableData = new google.visualization.DataTable();
                tableData.addColumn('string', 'Date');
                tableData.addColumn('number', node);
                if (data.length > 0) {
                    for (dataPointIndex in data) {
                        tableData.addRow([new Date(data[dataPointIndex].datapointPK.time).toLocaleString(), data[dataPointIndex].data]);
                    }
                }
                else{
                    tableData.addRow(["", null]);
                }
                drawChart-${param.collectionId}(chart, tableData, init);
            }
        });
    }

    function load_data_for_all_nodes() {
        nodes.forEach(function (node) {
            load_data_and_draw_node(node);
        });
    }



</script>

<div id="gauges-${collectionId}"/>
