var fileTree;

var selectedItem=null;

function loadRoots(){
    http.post("/file/listRoots",null,function(data){
        var fileList=data.fileList;
        var roots=[];
        if(data.code!=0){
            roots=handleErr(data.code);
        }else{
            for(var k in fileList){
                var file=fileList[k];
                roots.push({
                    isLeaf:false,
                    loadNode:loadNode,
                    onClickText:onClickText,
                    attributes: {
                        name:file.name,
                        path:file.path,
                        freeSpace:file.freeSpace,
                        usableSpace:file.usableSpace,
                        totalSpace:file.totalSpace
                    },
                    text: file.path+"("+(file.totalSpace-file.freeSpace)+"/"+file.totalSpace+"g)"
                });
            }
        }

        dtree.createTree({
            id:'fileTree',
            onClickText:onClickText,
            loadNode:loadNode,
            data:roots,
            operations:[
                dtree.operation.refresh,
                {
                    createOperation:function(li,tree){
                        var btn=$("<span class='glyphicon glyphicon-edit'></span>");
                        btn.click(function(event){
                            var tgt=event.target;
                            var lin=$(tgt).closest("li")[0];
                            selectedItem=lin;
                            var attr=fromJson($(lin).attr("data-attr"));
                            var src=attr.path;
                            var name=attr.name;
                            var form=$('<form></form>');
                            var input=$('<input class="form-control" name="filename" id="rename" type="text" value="'+name+'">');
                            form.append(input);
                            dialog.confirm("Rename to...",form,function(e,dlg){
                                var filename=$('#rename')[0].value;
                                if(filename==fromJson($(selectedItem).attr('data-attr')).name){
                                    dialog.alert("Please provide a new name different with the old name.");
                                    return;
                                }
                                var attr= fromJson($(selectedItem).attr('data-attr'));
                                var path=attr.path;
                                http.post('/file/rename',{filename:filename,path:path},function(data){
                                    var code=data.code;
                                    if(code==CODE_SUCCESS){
                                        dialog.close(dlg);
                                        dialog.alert('The file has been successfully renamed to:'+filename+'!');
                                        dtree.loadAndExpand(dtree.getParent(selectedItem),tree);
                                    }else{
                                        dialog.alert(getErrorMessage(code));
                                    }
                                });
                            });
                        });
                        return btn;
                    },
                    filter:function(li,tree){
                        if($(li).attr("isLeaf")=="true"){
                            return true;
                        }
                        return false;
                    }
                },
                {
                    createOperation:function(li,tree){
                        var btn=$("<span class='glyphicon glyphicon-upload' data-toggle='modal' data-target='#uploadDlg'></span>");
                        btn.click(function(event){
                            var tgt=event.target;
                            var lin=$(tgt).closest("li");
                            selectedItem=lin;
                        });
                        return btn;
                    },
                    filter:function(li,tree){
                        if($(li).attr("isLeaf")=="false"){
                            return true;
                        }
                        return false;
                    }
                }
            ]
        });
    })
}

function loadNode(params,callback){
    var attr=params.attributes;
    var path=attr.path;
    http.post('/file/listDir',{path:path},function(data){
        var fileList=data.fileList;
        var td=[];
        if(data.code!=0){
            td=handleErr(data.code);
        }else{
            for(var k in fileList){
                var f=fileList[k];
                td.push({
                    isLeaf:!f.isDir,
                    loadNode:loadNode,
                    onClickText:onClickText,
                    attributes: {
                        name: f.name,
                        path: f.path,
                        isDir: f.isDir
                    },
                    text: f.name
                });
            }
        }

        callback(params.id,td);
    })
}

function onClickText(event){
    var li=$(event.target).closest('li');
    if(li.attr('isLeaf')!="true"){
        return;
    }
    var attr=fromJson(li.attr("data-attr"));
    var path=attr.path;

    //var url="/file/download?path="+encodeURIComponent(path);
    var url="/file/download";
    http.download(url,{path:path},'post');
}

function handleErr(code){
    var data=[];
    var err= {
        isLeaf: true,
        loadNode: null,
        onClickText: null,
        attributes: {
        }
    }

    err.text=getErrorMessage(code);
    data.push(err);
    return data;
}

function submitFile(formId){
    var upload=$("#upload")[0];
    if(upload.value==null || upload.value==""){
        dialog.alert("Please specify the file to upload.");
        return;
    }

    var attr=eval("("+$(selectedItem).attr("data-attr")+")");
    $('#path')[0].value=attr.path;
    var form=$("#"+formId)[0];
    var formData=new FormData(form);

    http.upload(formData,"/file/upload",function(data){
        var code=data.code;
        if(code==CODE_SUCCESS){
            $('#uploadDlg').modal('hide');
        }else{
            dialog.alert(getErrorMessage(code));
        }
    })
}

function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('progress').attr({value:e.loaded,max:e.total});
    }
}