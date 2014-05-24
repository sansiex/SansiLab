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

        <script src="/js/lib/jquery.bootstrap.js"></script>
        <script src="/js/file.js"></script>
        <script type="text/javascript">
            PL._init();
            PL.joinListen('/myevent/msg');
        </script>

        <script type="text/css">

        </script>
    </head>
    <body>
    <%@ include file="common/header.jsp" %>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1>Welcome to Sansi Laboratory!</h1>
                <hr>
                <button type="button" class="btn btn-primary" onclick="testPushlet()">pushlet</button>
                <textarea id="txt" style="width:800px;height: 400px"></textarea>
                <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <ul id="fileTree"></ul>

            <hr>

            <%@ include file="common/footer.jsp" %>
        </div> <!-- /container -->
    </body>
</html>
