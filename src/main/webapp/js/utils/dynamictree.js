//depends on jquery, bootstrap

var dtree={
    nodeId:0
};

dtree.createTree=function(id,data,onClickText, onLoadNode){
    var root=$('#'+id)[0];
    dtree.createChildren(root,data,onClickText, onLoadNode);
}

dtree.createChildren=function(ul, date, onClickText, onLoadNode){
    if(ul.getName()!='ul'){
        console.log('The root node must be a ul.');
        return;
    }

    for(var key in data){
        var nd=data[key];
        var li=dtree.createChild(nd, onClickText, onLoadNode);
        ul.appendChild(li);
    }
}

dtree.createChild=function(data, onClickText, onLoadNode){
    var nid="tree_node_"+dtree.nodeId++;
    var li=$("<li id='"+nid+"'></li>");
    var span;
    var onclick
    if(onLoadNode==null){
        onclick="dtree.expandNode("+nid+")"
    }else{
        onclick=onLoadNode;
    }
    if(data.isDir==true){
        span=$("<span class='glyphicon glyphicon-folder-close'></span>");
    }else{
        span=$("<span class='glyphicon glyphicon-file'></span>");
    }
    li.attr("onclick",onclick);
    li.appendChild(span);
    if(data.attributes!=null){
        li.attr("data-attr",JSON.stringify(data.attributes));
    }
    var a=$("<a class='tree-node'>"+data.text+"</a>");
    if(onClickText!=null){
        a.attr("onclick",onClickText);
    }
    if(nd.attributes!=null){
        root.attributes=nd.attributes;
    }

    if(data.children!=null){
        var children=$("<ul></ul>");
        for(var k in data.children){
            var child=data.children[k];
            var cli=dtree.createChild(child);
            children.appendChild(cli);
        }
        ul.appendChild(children);
    }

    return li;
}

dtree.loadData=function(li, data){
    $(li).children(ul).remove();
    var onClickText=li.children('a')[0].attr("onclick");
    var onLoadNode=li.children('span').attr("onclick");
    var ul=$("<ul></ul>");
    dtree.createChildren(ul,data, onClickText, onLoadNode);
}

dtree.expand=function(nid){
    var li=$('#'+nid)[0];
}