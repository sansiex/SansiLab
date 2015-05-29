/*
可选项
g (全文查找出现的所有 pattern)
i (忽略大小写)
m (多行查找)
*/
var regexUtils={};

regexUtils.findOne=function(str,reg) {
    return str.match(reg);
}

regexUtils.match=function(str,reg){
    var re=new RegExp(reg);
    return re.exec(str);
}