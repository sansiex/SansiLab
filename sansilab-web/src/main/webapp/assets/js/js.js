/**
 * Created by lenovo on 2014/6/2.
 */

var edit;

function runCode(){
    var code=edit.getValue();
    eval(code);
}

function createEditor(){
    edit=CodeMirror(document.getElementById('editor'),{
        mode:'javascript',
        theme:'eclipse',
        value:'function my(){return 100;}\n',
        lineNumbers: true
    });
}

function createIncludeTree(){
    var data=[{
        isLeaf:false,
        loadNode:null,
        onClickText:function(event){},
        attributes: {
            name:'default'
        },
        text: "Default",
        children:extractDefaultScript()
    },{
        isLeaf:false,
        loadNode:null,
        onClickText:function(event){window.open(src);},
        attributes: {
            name:'user'
        },
        text: "User",
        operations:[{
            createOperation:function(li,tree){
                var btn=$("<span class='glyphicon glyphicon-plus'></span>");
                btn.click(function(event){
                    var tgt=event.target;
                    var lin=$(tgt).closest("li");

                    var form=$('<form></form>');
                    var input=$('<input class="form-control" name="refUrl" id="refUrl" type="text" value="http://">');
                    form.append(input);
                    dialog.confirm("Add external js file reference",form,function(e,dlg){
                        var src=$('#refUrl')[0].value;
                        includeJS(src);
                        dtree.appendChild(lin,{
                            isLeaf:true,
                            attributes: {
                                src:src,
                                type:'user'
                            },
                            text: src
                        });
                        dialog.close(dlg);
                    });
                });
                return btn;
            },
            filter:function(li,tree){
                return true;
            }
        }]
    }];

    dtree.createTree({
        id:'include',
        onClickText:function(event){},
        loadNode:null,
        data:data,
        operations:[{
            createOperation:function(li,tree){
                var btn=$("<span class='glyphicon glyphicon-remove'></span>");
                btn.click(function(event){
                    var tgt=event.target;
                    var lin=$(tgt).closest("li");

                    var attr=fromJson($(lin).attr("data-attr"));
                    removeJS(attr.src);

                    lin.remove();
                });
                return btn;
            },
            filter:function(li,tree){
                var attr=fromJson($(li).attr("data-attr"));
                if(attr.type=="user"){
                    return true;
                }
                return false;
            }
        }]
    });
}

function extractDefaultScript(){
    var sarr=[];
    $('head script').each(function(index,item){
        var src=$(item).attr('src');
        if(src==null){
            return;
        }
        var node={
            isLeaf:true,
            loadNode:null,
            onClickText:function(event){
                window.open(src);
            },
            attributes: {
                name:src
            },
            text:src

        };
        sarr.push(node);
    });

    return sarr;
}