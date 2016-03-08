<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    var nodes = ${param.moduleNodeList}
    var doUpdates = true;
    $(function () {
        nodes.forEach(function (node) {
            var div = document.createElement('div');
            div.id = node;
            $('#nodes').append(div);
        });
    });

    google.charts.load('current', {packages: ['line']});
    google.charts.setOnLoadCallback(initCharts);
    function initCharts() {
        nodes.forEach(function (node) {
            var ws = new WebSocket("ws://localhost:8080/nodes/websocket/${param.moduleUser}/" + node);
            ws.chart = new google.charts.Line(document.getElementById(node));
            ws.onopen = function ()
            {
                console.log("Websocket for " + node + " connected");

            };

            ws.onmessage = function (data)
            {
                console.log("Received data for " + node);
                console.log(data);
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
    }
    function drawChart(chart, data, init) {
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
            url: "webresources/datapoint/${param.moduleUser}/" + node + "/${param.resolution}",
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
                        tableData.addRow([data[dataPointIndex].datapointPK.time, data[dataPointIndex].data]);
                    }
                }
                else{
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



</script>

<label for="doPause">Pause</label><input type="checkbox" id="doPause">

<div id="nodes"/>
