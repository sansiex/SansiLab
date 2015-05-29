/**
 * Created by lenovo on 2014/6/7.
 */

var pwd="d:\\";
var editor;
var curCursor;

var OPT_END_OUTPUT="end_output";

function init(){
    $(document).ready(function(){
        editor = CodeMirror(document.getElementById('console'),{
            mode:'javascript'
            ,theme:'eclipse'
            ,value:'cat e:\\data\\test.txt | grep -i a | grep -i b\n'
            ,lineNumbers: true
        });
        push.joinListen('/console/output',handleConsoleOutput);
        registerEvent();
    });
}

function execute(){
    var script=editor.getValue();
    editor.setOption("readOnly",true);
    var btn=$('#exec');
    btn.attr('disabled','true');
    execScript(script,pwd);
}

function setPwd(p){
    pwd=p;
    $('#pwd').text(p);
}

function println(line){
    var ta=$('#output');
    ta.val(ta.val()+line+'\n');
}

function clearOutput(){
    $('#output').val("");
}

function execScript(script, pwd){
    http.post("/console/exec",{script:script,pwd:pwd,userId:getUserId()},function(data){
        var code=data.code;
        if(code!=CODE_SUCCESS){
            dialog.alert(getErrorMessage(code));
        }
    });
}

function handleConsoleOutput(event) {
    var msg=event.get('msg');
    if(msg!=null){
        println(msg);
    }

    var opt=event.get("opt");
    if(opt==OPT_END_OUTPUT){
        editor.setOption("readOnly",false);
        var btn=$('#exec');
        btn.removeAttr('disabled');
    }

    // 离开
    // PL.leave();
}

function getTypedStatement(){
    return editor.getLine(editor.lastLine());
}

//function keyDownReturn(event){
//    event.preventDefault();
//    var stm=getTypedStatement();
//    append("\n");
//    execStatement(stm,pwd);
//}

//function keyDownBackspace(event){
//    if(curCursor.ch==0){
//        event.preventDefault();
//    }
//}

function append(content){
    editor.setValue(editor.getValue()+content);
}

function appendLine(content){
    if(content.substring(content.length-1,content.length)!="\n"){
        content+="\n";
    }
    append(content);
}

function setCursorToEnd(){
    var line=editor.lastLine();
    var ch=editor.getLine(editor.lastLine()).length-1;
    editor.setCursor({
        line:line,
        ch:Math.max(ch,0)
    });
}

function registerEvent(){
    editor.on("keydown",function(instance, event){
        var keyCode=event.keyCode;
        switch(keyCode){
            case 8://backspace
                //keyDownBackspace(event);
                break;
            case 13://return
                //keyDownReturn(event);
                break;
            case 17://control
                break;
            case 46://delete
                break;
            default:
                console.log("keydown:"+keyCode+(event.ctrlKey?"+ctrl":""));
                break;
        }
    });

    editor.on("focus",function(instance, event){
        setCursorToEnd();
    });

//    editor.on("cursorActivity",function(instance){
//        var cursor=instance.getCursor();
//        var ll=instance.lastLine();
//        if(cursor.line<ll){
//            console.log('prevent');
//            instance.setCursor(curCursor);
//        }else{
//            curCursor=cursor;
//            console.log(cursor.line+","+cursor.ch);
//        }
//    });
}