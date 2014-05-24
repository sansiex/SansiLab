var fileTree;

function loadRoots(){
    http.post("/file/listRoots",null,function(data){
        var fileList=data.fileList;
        var roots=[];
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
                text: file.name
            });
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
        var data=[];
        for(var k in fileList){
            var f=fileList[k];
            data.push({
                isLeaf:!f.isDir,
                loadNode:loadNode,
                onClickText:onClickText,
                attributes: {
                    name: f.name
                },
                text: f.name
            });
        }
        callback(params.id,data);
    })
}

function onClickText(event){}