<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
<script src="${res}/js/nodes.js"></script>
<div class="col-md-6">
    <table class="table table-striped">
        <th colspan="3">Nodes</th>
    <c:forEach items="${user.getNodeList()}" var="node">
        <tr>
            <td class="center-table">${node.getNodePK().getName()}</td>
            <td><button class="btn">Assign to group</button></td>
            <td class="pull-right"><button class="btn btn-danger delete_node">Delete</button></td>
        </tr>
    </c:forEach>
    </table>
    <button class="btn">New collection</button>
</div>

<div class="col-md-6">
    <table class="table table-striped">
            <th colspan="2">Collections</th>
        <c:forEach items="${user.getCollectionList()}" var="collection">
            <tr>
                <td class="center-table">${collection.getName()}</td>
                <td class="pull-right"><button class="btn btn-danger delete_node">Delete</button></td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn">New node</button>
</div>
    
</div>
