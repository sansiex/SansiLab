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
        <script src="/js/lib/bootstrap.file-input.js"></script>
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
            <div class="row">
                <div class="col-md-5">
                    <ul id="fileTree"></ul>
                </div>
                <div class="col-md-5">
                    <form enctype="multipart/form-data">
                        <input type="file" name="upload" id="upload" class="file-input" data-filename-placement="inside">
                        <button type="button" class="btn btn-primary" onclick="submitFile(upload)">上传文件</button>
                    </form>
                    <progress></progress>

                </div>
            </div>

            <hr>

            <%@ include file="common/footer.jsp" %>
        </div> <!-- /container -->
        <script type="text/javascript">
            $('input[type=file]').bootstrapFileInput();
        </script>
    </body>
</html>
