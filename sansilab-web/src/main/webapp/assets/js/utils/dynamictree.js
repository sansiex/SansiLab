//depends on jquery, bootstrap

var dtree={
    trees:[],
    nodeId:0,
    treeId:0
};

dtree.createTree=function(params){
    var root=$('#'+params.id)[0];

    $(root).attr("tree_root","true");

    var tree={
        id:dtree.treeId++,
        onClickText:params.onClickText,
        loadNode:params.loadNode
    };

    if(params.operations!=null){
        tree.operations=params.operations;
    }

    var data=params.data;

    dtree.trees.push(tree);

    dtree.createChildren(root,data,tree);
}

dtree.createChildren=function(ul, data, tree){
    if(ul.tagName!='UL'){
        console.log('The root node must be a ul.');
        return;
    }

    var uln=$(ul);

    for(var key in data){
        var nd=data[key];
        var li=dtree.createChild(nd, tree);
        uln.append(li);
    }
}

dtree.createChild=function(data, tree){
    var expanded=false;
    if(data.expanded==true || (data.expanded!=false && tree.expanded==true)){
        expanded=true;
    }

    var nid="tree_node_"+dtree.nodeId++;
    var li=$("<li id='"+nid+"' treeId='"+tree.id+"'></li>");

    var span=dtree.createItemIcon(li,tree,data);
    li.append(span);

    if(data.attributes!=null){
        li.attr("data-attr",JSON.stringify(data.attributes));
    }

    var a=dtree.createItemText(li,tree,data);
    li.append("&nbsp;&nbsp;&nbsp;");
    li.append(a);

    //add operations to item
    if(tree.operations!=null){
        for(var i=0;i<tree.operations.length;i++){
            var op=tree.operations[i];
            if(op.filter(li,tree)){
                var opBtn=op.createOperation(li,tree);
                li.append("&nbsp;&nbsp;&nbsp;");
                li.append(opBtn);
            }
        }
    }

    if(data.operations!=null){
        for(var i=0;i<data.operations.length;i++){
            var op=data.operations[i];
            if(op.filter(li,tree)){
                var opBtn=op.createOperation(li,tree);
                li.append("&nbsp;&nbsp;&nbsp;");
                li.append(opBtn);
            }
        }
    }

    //create child nodes for static tree
    if(data.children!=null){
        var children=$("<ul style='display: none'></ul>");
        for(var k in data.children){
            var child=data.children[k];
            var cli=dtree.createChild(child, tree);
            children.append(cli);
        }
        li.append(children);
    }

    return li;
}

dtree.appendChild=function(li,data){
    if(!dtree.isTreeNode(li)){
        return;
    }
    $(li).attr('isLeaf','false');
    if($(li).children('ul').length==0){
        var ul="<ul style='display: none'></ul>";
        $(li).append(ul);
    }
    var ul=$(li).children('ul')[0];
    var c=dtree.createChild(data,dtree.getTree(li));
    $(ul).append(c);
}

dtree.loadAndExpand=function(pli,tree){
    var li=$(pli);
    var attrstr=li.attr("data-attr");
    var attr=eval('('+attrstr+')');
    var params={
        attributes:attr,
        text:li.children('a').html(),
        node:pli,
        id:li.attr('id')
    };

    tree.loadNode(params,function(lid,data){
        var li=$('#'+lid);
        li.attr('ready','true');
        dtree.renderNodeDate(li,data);
        dtree.expand(li);
    });
};

dtree.renderNodeDate=function(li, data){
    var uln=$(li).children('ul');
    uln.remove();
    var treeId=parseInt(li.attr('treeId'));
    var tree=dtree.trees[treeId];
    var ul=$("<ul></ul>")[0];
    dtree.createChildren(ul,data, tree);
    li.append(ul);
};

dtree.switchNode=function(n){
    var li=$(n);
    if(li.attr("expanded")=="false"){
        dtree.expand(n);
    }else{
        dtree.collapse(n);
    }
}

dtree.expand=function(n){
    var li;
    if(typeof n=='string'){
        li=$('#'+n)[0];
    }else{
        li=n;
    }
    var ul=$(li).children('ul');
    if(ul.length>0){
        $(ul[0]).css('display','block');
    }
    li.attr("expanded","true");
    li.children('.tree-item-icon').removeClass("glyphicon-folder-close").addClass("glyphicon-folder-open");
}

dtree.collapse=function(n){
    var li;
    if(typeof n=='string'){
        li=$('#'+n)[0];
    }else{
        li=n;
    }
    $(li).children('ul').css('display','none');
    li.attr("expanded","false");
    li.children('.tree-item-icon').removeClass("glyphicon-folder-open").addClass("glyphicon-folder-close");
}

dtree.createItemIcon=function(li,tree,data){
    var span;
    var onclick

    li.attr("expanded","false");
    if(data.isLeaf==false){
        li.attr('isLeaf','false');
        span=$("<span class='tree-item-icon glyphicon glyphicon-folder-close'></span>");
    }else{
        li.attr('isLeaf','true');
        span=$("<span class='tree-item-icon glyphicon glyphicon-file'></span>");
    }

    if(data.isLeaf==true){
        onclick=function(event){};
    }else{
        if(tree.loadNode==null){
            onclick=function(event){
                var target=event.target;
                var pli=$(target).closest('li');
                dtree.switchNode(pli);
            };
        }else{
            onclick=function(event){
                //阻止事件冒泡
                event.stopPropagation();
                var target=event.target;
                var pli=$(target).closest('li');
                if(li.attr('ready')!='true'){
                    dtree.loadAndExpand(pli,tree);
                    li.attr('ready','true');
                }else{
                    dtree.switchNode(pli);
                }
            };
        }
    }

    $(span).click(onclick);
    return span;
}

dtree.createItemText=function(li,tree,data){
    var a=$("<a class='tree-item-text'>"+data.text+"</a>");
    if(data.onClickText!=null){
        a.click(data.onClickText);
    }else if(tree.onClickText!=null){
        a.click(tree.onClickText);
    }
    return a;
}

dtree.createRefreshButton=function(li,tree){
    var refresh=$("<span class='glyphicon glyphicon-refresh'></span>");
    refresh.click(function(event){
        var tgt=event.target;
        var lin=$(tgt).closest("li");
        dtree.loadAndExpand(lin,tree);
    });
    return refresh;
}

dtree.getTree=function(li){
    if(!dtree.isTreeNode(li)){
        console.log(li+" is not a tree node.");
        return null;
    }
    var tid=parseInt($(li).attr("treeId"));
    return dtree.trees[tid];
}

dtree.getParent=function(li){
    if(!dtree.isTreeNode(li)){
        console.log(li+" is not a tree node.");
        return null;
    }
    if(dtree.isRoot(li)){
        console.log(li+" is a root node.");
        return null;
    }

    var p=$(li).parent();
    return p.closest('li')[0];
}

dtree.isTreeNode=function(li){
    if(li==null || $(li).attr('id').indexOf('tree_node')!=0){
        return false;
    }
    return true;
}

dtree.isRoot=function(li){
    var p=$(li).parent();
    if(p.attr('tree_root')=="true"){
        console.log(li+" is a root node.");
        return true;
    }
    return false;
}

dtree.operation={
    refresh:{
        createOperation:function(li,tree){
            var refresh=$("<span class='glyphicon glyphicon-refresh'></span>");
            refresh.click(function(event){
                var tgt=event.target;
                var lin=$(tgt).closest("li");
                dtree.loadAndExpand(lin,tree);
            });
            return refresh;
        },
        filter:function(li,tree){
            if($(li).attr("isLeaf")=="false"){
                return true;
            }
            return false;
        }
    }
}