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
            data:roots,
            operations:{
                refresh:true
            }
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
            err.text="Unknown error."
    }
    data.push(err);
    return data;
}

function submitFile(id){
    var input=$('#'+id);
    var formData = new FormData($('form')[0]);
    $.ajax({
        url: '/file/upload',  //server script to process data
        type: 'POST',
        xhr: function() {  // custom xhr
            myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){ // check if upload property exists
                myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
            }
            return myXhr;
        },
        //Ajax事件
        beforeSend: beforeSendHandler,
        success: completeHandler,
        error: errorHandler,
        // Form数据
        data: formData,
        //Options to tell JQuery not to process data or worry about content-type
        cache: false,
        contentType: false,
        processData: false
    });
}

function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('progress').attr({value:e.loaded,max:e.total});
    }
}