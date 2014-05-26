package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.service.FileService;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by lenovo on 2014/5/20.
 */
public class FileAction extends BaseAction {
    //services
    FileService fileService;

    //output
    InputStream inputStream;
    String fileName;
    int code=DirectoryAction.CODE_SUCCESS;

    //input
    String path;
    File file;
    String contentType;

    public String download(){
        if(path==null){
            code=CODE_INVALID_PARAMETER;
            return SUCCESS;
        }
        try {
            String d= URLDecoder.decode(path,"utf-8");
            fileName=d.substring(d.lastIndexOf(File.separator)+1);
            fileName=new String(fileName.getBytes("gbk"),"ISO-8859-1");
            inputStream=fileService.getFileInputStream(d);
        } catch (FileNotFoundException e) {
            code=CODE_FILE_NOT_FOUND;
            return SUCCESS;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String upload(){
        try {
            System.out.println("upload file "+fileName+" :"+file.getAbsolutePath()+" to "+path);
            fileService.saveFile(file,path);
        } catch (FileNotFoundException e) {
            code=CODE_FILE_NOT_FOUND;
            return SUCCESS;
        } catch (FileAlreadyExistsException e) {
            code=CODE_FILE_EXISTS;
            return SUCCESS;
        }
        return SUCCESS;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InputStream getInputStream(){
        return inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
