<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
<script src="${res}/js/nodes.js"></script>
<div class="col-md-6">
    <table class="table table-striped">
        <th colspan="">Nodes</th>
        <th>Collection</th>
        <th>Delete</th>
    <c:forEach items="${user.getNodeList()}" var="node">
        <c:set var="gotCollection" value="${node.getCollectionList().size() > 0 ? true : false }"></c:set>
        <c:set var="gotDataPoints" value="${node.getDatapointList().size() > 0 ? true : false}"></c:set>
        <tr>
            <td class="center-table">${node.getNodePK().getName()}</td>
            <td><div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        ${node.getCollectionList().size() > 0 ? node.getCollectionList().get(0).getName() : "Select collection" }
                        
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <c:forEach items="${user.getCollectionList()}" var="collection">
                            <li><button type="button" class="btn btn-link" onclick="addToCollection('${user.getMail()}','${node.getNodePK().getName()}', '${collection.getId()}')">${collection.getName()}</button></li>
                        </c:forEach>
                        <c:if test="${gotCollection == true}">
                            <li><button type="button" class="btn btn-link" onclick="removeFromCollection('${user.getMail()}','${node.getNodePK().getName()}', '${collection.getId()}')">-- Remove --</button></li>
                        </c:if>
                    </ul>
                  </div>
                </td>
                <td>
                    <button class="btn btn-danger delete_node ${gotDataPoints ? 'disabled' : ''}" ${gotDataPoints ? ("data-toggle='tooltip' data-placement='top' title='Remove datapoints to delete node'") : "enabled"} <c:if test="${gotDataPoints == false}"> onclick="deleteNode('${user.getMail()}','${node.getNodePK().getName()}')" </c:if>>Node</button>
                    <c:if test="${gotDataPoints == true}"><button class="btn btn-warning" onclick="deleteDataPoints('${user.getMail()}','${node.getNodePK().getName()}')" >Datapoints</button></c:if>
                </td>
        </tr>
    </c:forEach>
    </table>
      <button class="btn btn-default" type="button" data-toggle="collapse" data-target="#createNodeCollapse" aria-expanded="false" aria-controls="createNodeCollapse">
        Create node
      </button>
      <div class="collapse" id="createNodeCollapse">
        <div class="well">
            <form id="createNodeForm" method="POST">
              <div class="form-group">
                <label for="newnodename">Node name</label>
                <input type="text" class="form-control" name="newnodename" id="newnodename" placeholder="Node name">
              </div>
                <input type="hidden" name="username" id="username" value="${user.getMail()}">
                <input type="hidden" name="action" id="action" value="createnode">
              <button form="createNodeForm" type="submit" class="btn btn-default">Create</button>
            </form>
        </div>
      </div>
</div>

<div class="col-md-6">
    <table class="table table-striped">
            <th colspan="2">Collections</th>
        <c:forEach items="${user.getCollectionList()}" var="collection">
            <tr>
                <td class="center-table" width="100%">${collection.getName()}</td>
                <td class="pull-right"><button class="btn btn-danger delete_node" onclick="deleteCollection('${user.getMail()}','${collection.getId()}')">Delete</button></td>
            </tr>
        </c:forEach>
    </table>
          <button class="btn btn-default" type="button" data-toggle="collapse" data-target="#createCollectionCollapse" aria-expanded="false" aria-controls="createCollectionCollapse">
        Create collection
      </button>
      <div class="collapse" id="createCollectionCollapse">
        <div class="well">
            <form id="createCollectionForm" method="POST">
              <div class="form-group">
                <label for="newcollectioname">Collection name</label>
                <input type="text" class="form-control" name="newcollectioname" id="newcollectioname" placeholder="Collection name">
              </div>
                <input type="hidden" name="username" id="username" value="${user.getMail()}">
                <input type="hidden" name="action" id="action" value="createcollection">
              <button form="createCollectionForm" type="submit" class="btn btn-default">Create</button>
            </form>
        </div>
      </div>
</div>
    
</div>
