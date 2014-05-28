/**
 * Created by sansi on 2014/5/28.
 */

function log(content){
    console.log(content);
}

function toJson(obj){
    return JSON.stringify(obj);
}

function fromJson(json){
    return eval("("+json+")");
}

//获取指定form中的所有的数据
function formToMap(formId) {
    var form = document.getElementById(formId);
    var data={};
    var tagElements = form.getElementsByTagName('input');
    for (var j = 0; j < tagElements.length; j++){
        var ele=tagElements[j];
        data[ele.name]=ele.value;
    }
    return data;
}

//获取指定form中的所有的数据
function formToString(formId) {
    var form = document.getElementById(formId);
    var data=[];
    var tagElements = form.getElementsByTagName('input');
    for (var j = 0; j < tagElements.length; j++){
        var ele=tagElements[j];
        data.push(ele.name+"="+ele.value);
    }
    var str=data.join('&');
    return str;
}
