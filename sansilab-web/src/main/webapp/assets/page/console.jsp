<%--
  Created by IntelliJ IDEA.
  User: sansi
  Date: 2014/6/6
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <base href="<%=request.getContextPath()%>">
    <title>Sansi Laboratory</title>

    <link rel="stylesheet" href="/css/codemirror.css">
    <link rel="stylesheet" href="/css/codemirror-theme/eclipse.css">

    <%@ include file="common/ref.jsp" %>
    <script src="/js/lib/codemirror.js"></script>
    <script src="/js/lib/mode/javascript.js"></script>

    <script src="/js/console.js"></script>
    <script type="text/javascript">
        init();
    </script>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container">
    <div class="jumbotron">
        <h2>Console</h2>
    </div>
    <hr>
    <span style="">Working Directory:</span>
    <p id="pwd" style="">d:\</p>
    <hr>
    <div class="row">
        <div class="col-md-12">
            <button class="btn btn-primary" onclick="execute();" id="exec">Execute</button>
            <button class="btn btn-secondary" onclick="clearOutput();" id="clear">Clear</button>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6">
            <fieldset >
                <div id="console" style="border:solid grey 3px;height:500px"></div>
            </fieldset>
        </div>
        <div class="col-md-6">
            <textarea id="output" readonly style="width: 100%;height:500;border:solid grey 3px"></textarea>
        </div>
    </div>
</div>
<hr>
<%@ include file="common/footer.jsp" %>
</div> <!-- /container -->
</body>
</html>

