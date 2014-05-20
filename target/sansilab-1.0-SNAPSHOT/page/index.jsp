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

        <script src="/js/lib/jquery-1.11.0.min.js"></script>
        <script src="/js/lib/ajax-pushlet-client.js"></script>
        <script src="/js/utils/http.js"></script>
        <script src="/js/index.js"></script>
        <script type="text/javascript">
            PL._init();
            PL.joinListen('/myevent/msg');
        </script>
    </head>
    <body>

        <h1>Sansi Laboratory</h1>
        <hr>
        <button onclick="testPushlet()">pushlet</button>
        <textarea id="txt"></textarea>
    </body>
</html>
