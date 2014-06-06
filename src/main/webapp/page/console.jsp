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

    <%@ include file="common/ref.jsp" %>
    <script src="/js/console.js"></script>
    <script type="text/javascript">
        //PL._init();
        //PL.joinListen('/myevent/msg');
        $(document).ready(function(){
        });
    </script>

    <script type="text/css">

    </script>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <hr>
            <textarea id="console" style="width:100%;height:500px"></textarea>
        </div>
    </div>
</div>
<hr>
<%@ include file="common/footer.jsp" %>
</div> <!-- /container -->
</body>
</html>

