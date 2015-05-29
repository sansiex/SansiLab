<%--
  Created by IntelliJ IDEA.
  User: sansi
  Date: 2014/5/8
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <base href="<%=request.getContextPath()%>">
    <title>Sansi Laboratory</title>

    <%@ include file="common/ref.jsp" %>
    <link rel="stylesheet" href="/css/codemirror.css">
    <link rel="stylesheet" href="/css/codemirror-theme/eclipse.css">

    <script src="/js/lib/codemirror.js"></script>
    <script src="/js/lib/mode/javascript.js"></script>
    <script src="/js/utils/dynamictree.js"></script>
    <script src="/js/js.js"></script>
    <script type="text/javascript">
        //PL._init();
        //PL.joinListen('/myevent/msg');
        $(document).ready(function(){
            createEditor();
            createIncludeTree();
        });
    </script>

    <script type="text/css">

    </script>
</head>
<body>
<%@ include file="common/header.jsp" %>

    <div class="container">
        <div class="jumbotron">
            <h2>Javascript Editor</h2>
        </div>
        <div class="row">
            <div class="col-md-8">
                <button class="btn btn-primary" onclick="runCode();">Run</button>
                <hr>
                <div id="editor"></div>
                <!--<textarea id="editor" style="width:100%;height:500px"></textarea>-->
            </div>
            <div class="col-md-4">
                <ul id="include" />
            </div>
        </div>

    </div>

    <hr>

    <%@ include file="common/footer.jsp" %>
</div> <!-- /container -->
</body>
</html>
