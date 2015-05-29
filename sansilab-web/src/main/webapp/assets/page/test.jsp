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
        <script src="/js/lib/bootstrap.file-input.js"></script>
        <script type="text/javascript">
            //loadRoots();
        </script>
    </head>
    <body>
        <button data-target="#uploadForm" class="btn btn-primary" data-toggle="modal">Upload</button>
        <div id="uploadForm" role="dialog" class="modal fade" aria-labelledby="uploadTitle" data-backdrop="static" data-keyboard="true" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 class="modal-title" id="uploadTitle">Upload File</h3>
                    </div>
                    <div class="modal-body">
                        <form enctype="multipart/form-data">
                            <input type="file" name="upload" id="upload" class="file-input" data-filename-placement="inside">
                            <button type="button" class="btn btn-primary" onclick="submitFile(upload)">上传文件</button>
                        </form>
                        <progress></progress>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            //$('input[type=file]').bootstrapFileInput();
        </script>
    </body>
</html>
