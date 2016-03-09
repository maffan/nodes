<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    startupFunctions.push(function () {
        var nodes = ${param.moduleNodeList} 
        $('#graph_div_${param.collectionId}').css("height", 200*nodes.length);
        nodes.forEach(function (node) {
            var div = document.createElement('div');
            div.id = "graph_div_${param.collectionId}_" + node;
            $('#graph_div_${param.collectionId}').append(div);
        });


        nodes.forEach(function (node) {
            var ws = new WebSocket("ws://localhost:8080/nodes/websocket/${param.moduleUser}/" + node);
            ws.chart = new google.charts.Line(document.getElementById("graph_div_${param.collectionId}_" + node));
            ws.onopen = function ()
            {
                console.log("Websocket for " + node + " connected");

            };

            ws.onmessage = function (data)
            {
                if (!$('#doPause')[0].checked) {
                    load_data_and_draw_node(ws.chart, node, false);
                }
            };

            ws.onclose = function ()
            {
                console.log("Websocket for " + node + " closed");
            };
            load_data_and_draw_node(ws.chart, node, true);
        });

        function drawChart(chart, data, init) {
            var options = {
                height: 200,
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
            if (!$('#doPause')[0].checked) {
                chart.draw(data, options);
            }
        }



        function load_data_and_draw_node(chart, node, init) {
            $.ajax({
                url: "webresources/datapoint/${param.moduleUser}/" + node + "/${param.resolution}",
                //url: "webresources/datapoint/admin/testNode",
                headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                type: 'GET',
                success: function (data) {
                    var tableData = new google.visualization.DataTable();
                    tableData.addColumn('string', 'Date');
                    tableData.addColumn('number', node);
                    if (data.length > 0) {
                        for (dataPointIndex in data) {
                            tableData.addRow([new Date(data[dataPointIndex].datapointPK.time).toLocaleString(), data[dataPointIndex].data]);
                        }
                    } else {
                        tableData.addRow(["", null]);
                    }
                    drawChart(chart, tableData, init);
                }
            });
        }

        function load_data_for_all_nodes() {
            nodes.forEach(function (node) {
                load_data_and_draw_node(node);
            });
        }

    });



</script>

<div id="graph_div_${param.collectionId}"></div>
