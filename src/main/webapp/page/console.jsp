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
        PL._init();
        PL.joinListen('/console/response');
        $(document).ready(function(){
            editor = CodeMirror(document.getElementById('console'),{
                mode:'javascript'
                ,theme:'eclipse'
                ,value:'function my(){return 100;}\n12345678\n12345'
                //,lineNumbers: true
            });
            registerEvent();
        });
    </script>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container">
    <div class="jumbotron">
        <h2>Console</h2>
    </div>
    <div class="row">
        <div class="col-md-12">
            <hr>
            <fieldset >
                <div id="console" style="border:solid grey 3px"></div>
            </fieldset>

        </div>
    </div>
</div>
<hr>
<%@ include file="common/footer.jsp" %>
</div> <!-- /container -->
</body>
</html>

