/**
 * Created by lenovo on 2014/5/20.
 */

function onData(event) {
    display(event.get("msg"));
}

var count=0;

function display(content){
    var txt=$('#txt')[0];
    count++;
    var o=txt.innerHTML;
    txt.innerHTML=o+content+count;;
}

function testPushlet(){
    http.post("http://localhost:8080/test/broadcast",{},function(data){
        console.log(data);
    });
}