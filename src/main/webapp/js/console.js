/**
 * Created by lenovo on 2014/6/7.
 */

var pwd="d:\\";
var editor;
var curCursor;

function execStatement(script, pwd){
    console.log("exec: "+pwd+" > "+script);
    http.post("/console/exec",{script:script,pwd:pwd},function(data){
        var code=data.code;
        if(code!=CODE_SUCCESS){
            dialog.alert(getErrorMessage(code));
        }
        //console.log("response"+data.msg);
    });
}

function getTypedStatement(){
    return editor.getLine(editor.lastLine()-1);
}

function keyDownReturn(event){
    var stm=getTypedStatement();
    execStatement(stm,pwd);
}

function keyDownBackspace(event){
    if(curCursor.ch==0){
        event.preventDefault();
    }
}

function setCursorToEnd(){
    var line=editor.lastLine();
    var ch=editor.getLine(editor.lastLine()).length;
    editor.setCursor({
        line:line,
        ch:ch
    });
    console.log(line+","+ch);
}

function registerEvent(){
    editor.on("keydown",function(instance, event){
        var keyCode=event.keyCode;
        switch(keyCode){
            case 8://backspace
                keyDownBackspace(event);
                break;
            case 13://return
                keyDownReturn(event);
                break;
            case 17://control
                break;
            case 46://delete
                break;
            default:
                console.log("keydown:"+keyCode+(event.ctrlKey?"+ctrl":""));
                console.log(event);
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