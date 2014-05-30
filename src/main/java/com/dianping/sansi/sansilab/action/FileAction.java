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
    String filename;
    int code=DirectoryAction.CODE_SUCCESS;

    //input
    String path;
    File upload;
    String uploadContentType;
    String uploadFileName;

    public String download(){
        if(path==null){
            code=CODE_INVALID_PARAMETER;
            return SUCCESS;
        }
        try {
            String d= URLDecoder.decode(path,"utf-8");
            filename=d.substring(d.lastIndexOf(File.separator)+1);
            filename=new String(filename.getBytes("gbk"),"ISO-8859-1");
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
            System.out.println("upload "+uploadContentType+" file "+uploadFileName+" :"+upload.getAbsolutePath()+" to "+path);
            fileService.saveFile(upload,path,uploadFileName);
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

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public InputStream getInputStream(){
        return inputStream;
    }

    public int getCode(){
        return code;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
