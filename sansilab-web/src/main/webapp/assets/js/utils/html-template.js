function renderTemplate(template, data){
    var regVar=/$\{(\w+)\}/;
    var ret=template.replaceAll(regVar,function(){return data[$1];});

    var regIf=/if\((\w+)\)\[(\w*):(\w*)\]/;
    ret=ret.replaceAll(regVar,function(){
        var cond=eval($1);
        if(cond){
            return $2;
        }else{
            return $3;
        }
    });
    return ret;
}

function renderList(template, arr){
    var ret="";
    for(var k in arr){
        var n=arr[k];
        ret+=renderTemplate(template, n);
    }
    return ret;
}