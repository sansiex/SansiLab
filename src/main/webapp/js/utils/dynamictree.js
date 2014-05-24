//depends on jquery, bootstrap

var dtree={
    trees:[],
    nodeId:0,
    treeId:0
};

dtree.createTree=function(params){
    var root=$('#'+params.id)[0];

    var tree={
        id:dtree.treeId++,
        onClickText:params.onClickText,
        loadNode:params.loadNode,
        data:params.data
    };

    dtree.push(tree);

    dtree.createChildren(root,data,tree);
}

dtree.createChildren=function(ul, data, tree){
    if(ul.getName()!='ul'){
        console.log('The root node must be a ul.');
        return;
    }

    for(var key in data){
        var nd=data[key];
        var li=dtree.createChild(nd, tree.onClickText, tree.onLoadNode);
        ul.appendChild(li);
    }
}

dtree.createChild=function(data, tree){
    var nid="tree_node_"+dtree.nodeId++;
    var li=$("<li id='"+nid+"' treeId='"+tree.id+"'></li>");
    var span;
    var onclick

    if(data.isLeaf==false){
        li.attr('isLeaf','false');
        span=$("<span class='glyphicon glyphicon-folder-close'></span>");
    }else{
        li.attr('isLeaf','true');
        span=$("<span class='glyphicon glyphicon-file'></span>");
    }

    if(tree.loadNode==null){
        if(data.isLeaf==false){
            onclick=function(event){
                var target=event.target;
                var pli=$(target).closest('li');
                dtree.expandNode(pli);
            };
        }else{
            onclick=function(event){};
        }
    }else{
        if(li.attr('ready')!='true'){
            onclick=function(event){
                //阻止事件冒泡
                event.stopPropagation();
                console.log(event);
                var target=event.target;
                var pli=$(target).closest('li');
                dtree.loadAndExpand(pli);
            };
        }else{
            onclick=function(event){
                var target=event.target;
                var pli=$(target).closest('li');
                dtree.expandNode(pli);
            }
        }
    }

    $(span).click(onclick);

    li.appendChild(span);
    if(data.attributes!=null){
        li.attr("data-attr",JSON.stringify(data.attributes));
    }
    var a=$("<a class='tree-node'>"+data.text+"</a>");
    if(onClickText!=null){
        a.click(onClickText);
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

dtree.loadAndExpand=function(pli){
    var params={
        attributes:eval(pli.attr("data-attr")),
        text:pli.children('a').html(),
        node:pli,
        id:pli.attr('id')
    };

    tree.loadNode(params,function(lid,data){
        var li=$('#'+lid);
        li.attr('ready','true');
        dtree.renderNodeDate(li,data);
        dtree.expand(li);
    });
};

dtree.renderNodeDate=function(li, data){
    $(li).children(ul).remove();
    var treeId=parseInt(li.attr('treeId'));
    var tree=dtree.trees[treeId];
    var ul=$("<ul></ul>");
    dtree.createChildren(ul,data, tree);
    li.appendChild(ul);
};

dtree.expand=function(n){
    var li;
    if(typeof n=='string'){
        li=$('#'+n)[0];
    }else{
        li=n;
    }
    $(li).children('ul').style('display','block');
}

dtree.collapse=function(n){
    var li;
    if(typeof n=='string'){
        li=$('#'+n)[0];
    }else{
        li=n;
    }
    $(li).children('ul').style('display','none');
}