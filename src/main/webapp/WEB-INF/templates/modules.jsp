<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script src="${res}/js/modules.js"></script>
<c:set var="collections" value="${user.getCollectionList()}"></c:set>
<div class="row">
    <h3>Type of modules</h3>
    <div class="col-md-4">
      <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">Graph</h3>
      </div>
      <div class="panel-body">
        <div class="col-md-6">
          <img src="${res}/images/graphs.png">
        </div>
            <div class="col-md-6">
          Display your temperature readings in a nice graph. One graph per node.
        </div>
      </div>
    </div>
    </div>
    <div class="col-md-4">
      <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">Average temperature</h3>
      </div>
      <div class="panel-body">
        <div class="col-md-6">
          <img src="${res}/images/gauge.png">
        </div>
            <div class="col-md-6">
          Show a collections average temperature. All nodes displayed as one value.
        </div>
      </div>
    </div>
    </div>
    <div class="col-md-4">
      <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">Detailed statistics</h3>
      </div>
      <div class="panel-body">
        <div class="col-md-6">
          <img src="${res}/images/stat.png">
        </div>
            <div class="col-md-6">
          Detailed statistics about each node, max, min and average temperatures
        </div>
      </div>
    </div>
    </div>
</div>

<div class="row">   
    <h3>Installed modules</h3> 
    <div class="col-md-12">
        <div class="panel panel-default">
        <c:forEach items="${collections}" var="collection">          
                <div class="panel-heading">Collection: ${collection.getName()}</div>
                <div class="panel-body">
                    <table class="table">
            <c:forEach items="${collection.getModuleList()}" var="collectionModule">  
                <tr>
                    <td>${collectionModule.getName()}</td>
                    <td><button class="btn btn-danger" onClick="removeModule('${user.getMail()}','${collection.getId()}','${collectionModule.getName()}')">Remove</button></td>
                </tr>
            </c:forEach>
                    </table>
               </div>
        </c:forEach>
    </div>
    </div>
</div>

<div class="row">
    <h3>Install new module</h3>
    <div class="col-md-12">
    <form class="form-inline" id="am">
        <input type="hidden" name="username" value="${user.getMail()}"/>
        <div class="form-group">
          <label for="collection">Select collection</label>
            <select name="collection" form="am" class="form-control">
                <c:forEach items="${collections}" var="collection">
                    <option value="${collection.getId()}">${collection.getName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
          <label for="module">Select module</label>
            <select name="module" form="am" class="form-control">
                <c:forEach items="${modules}" var="module">
                    <option value="${module.getName()}">${module.getName()}</option>
                </c:forEach>
            </select>
        </div>
        <button form="am" id="addMod" type="submit" class="btn btn-default">Add module</button>
      </form>
    </div>
</div>

</div>
