//depends on jquery, bootstrap

var dtree={
    nodeId:0
};

dtree.createTree=function(id,data,onclick,loadDate){
    var root=$('#'+id)[0];
    if(root.getName()!='ul'){
        console.log('The root node must be a ul.');
        return;
    }

    for(var key in data){
        var nd=data[key];
        var li=$("<li></li>");
        if(nd.isDir==true){
            var span=$("<span class='glyphicon glyphicon-folder-close'></span>");
        }
        if(nd.attributes!=null){
            root.attributes=nd.attributes;
        }

        if(nd.text!=null){

        }
    }

}