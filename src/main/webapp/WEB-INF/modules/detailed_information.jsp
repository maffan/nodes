<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
    This module draws a table containing detailed information about the provided
    nodes.

    Variables available:
        user:                   The currently logged in entities.User object
        param.collectionId:     The Id number of the collection related to this 
                                module instance
        param.resolution:       The amount of hours in the past to show data for.
        param.moduleUser:       The owner of this module instance
        param.moduleNodeList:   A json-formated string containing all the 
                                names of the nodes in the collection/module.
-->
<script>
    startupFunctions.push(function () {
        var nodes = ${param.moduleNodeList};
        var table = new google.visualization.Table(document.getElementById('table_div_${param.collectionId}'));
        var tableData = {};
        
        function drawTable() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Node');
            data.addColumn('number', 'Current');
            data.addColumn('number', 'Max');
            data.addColumn('number', 'Min');
            data.addColumn('number', 'Average');
            
            for(var node in tableData){
                data.addRow([node, tableData[node][0], tableData[node][1], tableData[node][2], tableData[node][3]]);
            }
            if ($('#doPause').val() == 'false'){
                table.draw(data, {width: '100%', height: '100%'});
            }
        }

        function load_data_and_draw_node(node) {
            var currentCall = $.ajax({
                url: "webresources/datapoint/${param.moduleUser}/" + node + "/latest",
                headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                type: 'GET'
                }),
                averageCall = $.ajax({
                    url: "webresources/datapoint/${param.moduleUser}/" + node + "/average/${param.resolution}",
                    headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                    type: 'GET'
                }),
                maxCall = $.ajax({
                    url: "webresources/datapoint/${param.moduleUser}/" + node + "/max/${param.resolution}",
                    headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                    type: 'GET'
                }),
                minCall = $.ajax({
                    url: "webresources/datapoint/${param.moduleUser}/" + node + "/min/${param.resolution}",
                    headers: {"APIKey": "${user.getApi()}", "owner": "${user.getMail()}"},
                    type: 'GET'
                });
            $.when(currentCall, averageCall, maxCall, minCall).done(function (current, avg, max, min) {
                tableData[node] = [current[0], max[0], min[0], avg[0]];
                drawTable();
            });
        }

        nodes.forEach(function (node) {
            var ws = new WebSocket("ws://localhost:8080/nodes/websocket/${param.moduleUser}/" + node);

            ws.onopen = function ()
            {
                console.log("Websocket for " + node + " connected");
            };

            ws.onmessage = function (data)
            {
                if ($('#doPause').val() == 'false'){
                    load_data_and_draw_node(node);
                }
            };

            ws.onclose = function ()
            {
                console.log("Websocket for " + node + " closed");
            };
            load_data_and_draw_node(node);
        });
    });
</script>
<div id="table_div_${param.collectionId}"></div>
