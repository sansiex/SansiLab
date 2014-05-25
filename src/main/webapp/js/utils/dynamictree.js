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
        loadNode:params.loadNode
    };

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
    var nid="tree_node_"+dtree.nodeId++;
    var li=$("<li id='"+nid+"' treeId='"+tree.id+"'></li>");
    var span;
    var onclick

    li.attr("expanded","false");
    if(data.isLeaf==false){
        li.attr('isLeaf','false');
        span=$("<span class='glyphicon glyphicon-folder-close'></span>");
    }else{
        li.attr('isLeaf','true');
        span=$("<span class='glyphicon glyphicon-file'></span>");
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
                console.log(event);
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

    li.append(span);
    if(data.attributes!=null){
        li.attr("data-attr",JSON.stringify(data.attributes));
    }
    var a=$("<a class='tree-node'>"+data.text+"</a>");
    if(tree.onClickText!=null){
        a.click(tree.onClickText);
    }
    if(data.attributes!=null){
        li.attr('attributes',JSON.stringify(data.attributes));
    }
    li.append(a);

    if(data.children!=null){
        var children=$("<ul></ul>");
        for(var k in data.children){
            var child=data.children[k];
            var cli=dtree.createChild(child, tree);
            children.append(cli);
        }
        ul.append(children);
    }

    return li;
}

dtree.loadAndExpand=function(pli,tree){
    var attrstr=pli.attr("data-attr");
    var attr=eval('('+attrstr+')');
    var params={
        attributes:attr,
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
}