var fileTree;
var CODE_NOT_DIRECTORY=1001;
var CODE_FILE_NOT_FOUND=1002;
var CODE_ACCESS_DENIED=1003;

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
            data:roots
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

function onClickText(event){}

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
            err.text="Unknown error."
    }
    data.push(err);
    return data;
}