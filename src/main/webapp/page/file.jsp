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
        <script src="/js/utils/dynamictree.js"></script>
        <script src="/js/file.js"></script>
        <script type="text/javascript">
            PL._init();
            PL.joinListen('/myevent/msg');

            loadRoots();
        </script>

        <script type="text/css">

        </script>
    </head>
    <body>
    <%@ include file="common/header.jsp" %>

        <div class="container">
            <!-- Example row of columns -->
            <ul id="fileTree"></ul>

            <hr>

            <%@ include file="common/footer.jsp" %>
        </div> <!-- /container -->
    </body>
</html>
