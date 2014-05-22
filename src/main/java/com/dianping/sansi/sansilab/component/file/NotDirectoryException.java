package com.dianping.sansi.sansilab.component.file;

/**
 * Created by sansi on 2014/5/22.
 */
public class NotDirectoryException extends Exception{
    private String path;

    public NotDirectoryException(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
