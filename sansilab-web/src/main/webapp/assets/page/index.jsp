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

        <script src="/assets/js/index.js"></script>
        <script type="text/javascript">
        </script>
    </head>
    <body>
    <%@ include file="common/header.jsp" %>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1>Welcome to Sansi Laboratory!</h1>
                <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2>File Manager</h2>
                    <p>Manage files on the server. </p>
                    <p><a class="btn btn-default" href="/page/page?page=file" role="button">Go &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>JS Editor</h2>
                    <p>Edit and execute Javascript code. </p>
                    <p><a class="btn btn-default" href="/page/page?page=js" role="button">Go &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Console</h2>
                    <p>Provide a console to interact with server. </p>
                    <p><a class="btn btn-default" href="/page/page?page=console" role="button">go &raquo;</a></p>
                </div>
            </div>

            <hr>

            <%@ include file="common/footer.jsp" %>
        </div> <!-- /container -->
    </body>
</html>
