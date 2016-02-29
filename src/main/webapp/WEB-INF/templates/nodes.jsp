<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
<script src="${res}/js/nodes.js"></script>
<table>
    Collections
    <c:forEach items="${collections}" var="collection">
        <tr>
            <td>${collection.getName()}</td>
        </tr>
    </c:forEach>
        
    Nodes
    <c:forEach items="${nodes}" var="node">
        <tr>
            <td>${node.getNodePK().getName()}</td>
        </tr>
    </c:forEach>
</table>
    
</div>
