/**
 * Created by sansi on 2014/5/8.
 */

var http={};

http.ajax=function(url,method,params,callback){
    $.ajax({
        type: method,
        dataType: "json",
        url : url,
        data: params,
        success: function(data){
            if(callback!=null){
                callback(data);
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert("error@ajax_post:"+errorThrown);
        }
    });
}

http.get=function(url,params,callback){
    if(param!=null){
        url=url+"?";
        var arr=new Array();
        for(var k in params){
            arr.push(url+k+"="+params[k]);
        }
        url=url+arr.join('&');
    }
    http.ajax(url,"get",null,callback);
}

http.post=function(url,params,callback){
    http.ajax(url,"post",params,callback);
}

http.download = function(url, data, method){
    // 获得url和data
    if( url && data ){
        // data 是 string 或者 array/object
        var inputs = '';
        if(typeof data == 'string'){
            jQuery.each(data.split('&'), function(){
                var pair = this.split('=');
                inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ encodeURIComponent(pair[1]) +'" />';
            });
        }else {
            for(var k in data){
                var v=data[k];
                inputs+='<input type="hidden" name="'+ k +'" value="'+ encodeURIComponent(v) +'" />';
            }
        }
        // 把参数组装成 form的  input

        // request发送请求
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
            .appendTo('body').submit().remove();
    };
};