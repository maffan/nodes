<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Hello ${user.name}</h2>
<h3>Here is a list of your nodes</h3>
<table>
    <c:forEach items="${nodes}" var="node">
        <tr>
            <td>${node.getNodePK().getName()}</td>
        </tr>
    </c:forEach>
</table>
