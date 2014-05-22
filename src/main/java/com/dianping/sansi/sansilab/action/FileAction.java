package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.component.file.NotDirectoryException;
import com.dianping.sansi.sansilab.service.FileService;
import org.apache.struts2.json.annotations.JSON;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2014/5/20.
 */
public class FileAction extends BaseAction {
    public static final int CODE_NOT_DIRECTORY=1001;
    public static final int CODE_FILE_NOT_FOUND=1002;

    FileService fileService;

    //input params
    String dir;

    //ouput params
    List<HashMap<String,Object>> fileList;
    int code=CODE_SUCCESS;

    public String listDir(){
        try {
            fileList= fileService.listDirectory(dir);
        } catch (FileNotFoundException e) {
            code=CODE_FILE_NOT_FOUND;
            return SUCCESS;
        } catch (NotDirectoryException e) {
            code=CODE_NOT_DIRECTORY;
            return SUCCESS;
        }
        return SUCCESS;
    }

    public String listRoots(){
        fileList= fileService.listRoots();
        return SUCCESS;
    }

    @JSON(name="fileList")
    public List<HashMap<String,Object>> getFileList() {
        return fileList;
    }

    @JSON(name="code")
    public int getCode() {
        return code;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
