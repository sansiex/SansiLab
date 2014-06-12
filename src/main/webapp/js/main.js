/**
 * Created by sansi on 2014/5/28.
 */

var CODE_SUCCESS=0;
var CODE_NOT_DIRECTORY=1001;
var CODE_FILE_NOT_FOUND=1002;
var CODE_FILE_ACCESS_DENIED=1003;
var CODE_FILE_EXISTS=1004;

function getUserId(){
    return 145;
}

function log(content){
    console.log(content);
}

function toJson(obj){
    return JSON.stringify(obj);
}

function fromJson(json){
    return eval("("+json+")");
}

function foreach(obj, func){
    for(var k in obj){
        var v=obj[k];
        func(v,k);
    }
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

//Dynamically add js ref
function includeJS(src){
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = false;
    ga.src = src;
    var s = document.getElementsByTagName('head')[0];
    s.appendChild(ga);
}

//Dynamically add js ref
function removeJS(src){
    $('script[src="'+src+'"]').remove();
}

var errorMessage={};
errorMessage[CODE_NOT_DIRECTORY]="The clicked item isn't a directory.";
errorMessage[CODE_FILE_NOT_FOUND]="Can't find the directory or file.";
errorMessage[CODE_FILE_ACCESS_DENIED]="Access to the directory or file is denied.";
errorMessage[CODE_FILE_EXISTS]="The file with the same name already exists.";

function getErrorMessage(code){
    var msg=errorMessage[code];
    if(msg!=null) {
        return msg;
    }
    return "Unknown error:"+code;
}