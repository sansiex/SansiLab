var dialog={
    dlgId:0
};

dialog.getDlgId=function(){
    return 'sansi-dialog-'+dialog.dlgId++;
}

dialog.show=function(dlg){
    $('body').append(dlg);
    dlg.modal('show');
}

dialog.close=function(dlg){
    dlg.modal('hide');
}

dialog.alert=function(content){
    var dlg=dialog.createDialog(null,content,[dialog.button.ok]);
    dialog.show(dlg);
    //alert(content);
}

dialog.confirm=function(title,content,callback){
    var dlg=dialog.createDialog(title,content,[dialog.button.makeButton({
        text:'Confirm',
        onclick:function(e){
            callback(e,dlg);
        }
    })]);
    dialog.show(dlg);
}

dialog.createDialog=function(title,content,buttons,config){
    var closable;
    if(config!=null){
        closable=config.closable;
    }

    var id=dialog.getDlgId();

    var dlg=$('<div id="'+id+'" role="dialog" class="modal fade" aria-labelledby="dltTitle" data-backdrop="static" data-keyboard="true" tabindex="-1"></div>');
    var modal=$('<div class="modal-dialog"></div>');
    dlg.append(modal);
    var ctt=$('<div class="modal-content"></div>');
    modal.append(ctt);
    var header=dialog.createHeader(title==null?"Message":title,closable);
    ctt.append(header);
    var body=dialog.createBody(content);
    ctt.append(body);
    var footer=dialog.createFooter(buttons);
    ctt.append(footer);

    dlg.on('hidden.bs.modal', function (e) {
        var clicked=$(e.target);
        var d=clicked.closest('[role="dialog"]');
        d.remove();
    });
    return dlg;
}

dialog.createHeader=function(title,closable){
    var header=$('<div class="modal-header"></div>');
    if(closable!=false){
        header.append($('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'));
    }
    header.append($('<h3 class="modal-title" id="dlgTitle">'+title+'</h3>'));
    return header;
}

dialog.createBody=function(content){
    var body=$('<div class="modal-body"></div>');
    body.append(content);
    return body;
}

dialog.createFooter=function(buttons){
    var footer=$('<div class="modal-footer"></div>');
    for(var k in buttons){
        var v=buttons[k];
        footer.append(v);
    }
    return footer;
}

dialog.button={
    makeButton:function(config){
        var btn=$('<button type="button" class="btn"></button>');
        btn.append(config.text);
        if(config.style!=null){
            btn.addClass(config.style);
        }else{
            btn.addClass("btn-default");
        }
        if(config.onclick!=null){
            btn.click(config.onclick);
        }
        return btn;
    },
    ok:$('<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>'),
    cancel:$('<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>')
}