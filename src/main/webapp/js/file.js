var fileTree;
var CODE_NOT_DIRECTORY=1001;
var CODE_FILE_NOT_FOUND=1002;
var CODE_ACCESS_DENIED=1003;

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
    var attr=eval("("+li.attr("data-attr")+")");
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

    switch (code){
        case CODE_NOT_DIRECTORY:
            err.text="The clicked item isn't a directory.";
            break;
        case CODE_FILE_NOT_FOUND:
            err.text="Can't find the directory or file.";
            break;
        case CODE_ACCESS_DENIED:
            err.text="Access to the directory is denied.";
            break;
        default:
            err.text="Unknown error:"+code;
    }
    data.push(err);
    return data;
}

function submitFile(formId){
    var attr=fromJson($(selectedItem).attr("data-attr"));
    $('#path')[0].value=attr.path;
    var data=formToMap(formId);
    http.post('/file/upload',data,function(data){
        $('#uploadForm').modal('hide');
        var index=parseInt($(selectedItem).attr("treeId"));
        var tree=dtree.trees[index];
        dtree.loadAndExpand(selectedItem,tree);
    });
}

function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('progress').attr({value:e.loaded,max:e.total});
    }
}