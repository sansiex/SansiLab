var fileTree;

function loadRoots(){
    fileTree = $('#fileTree');

    var root={};

    http.post("/file/listRoots",null,function(data){
        var fileList=data.fileList;
        for(var k in fileList){
            var file=fileList[k];

        }
    })
}