package com.dianping.sansi.sansilab.controllers;

import com.dianping.sansi.sansilab.constants.ErrorCode;
import com.dianping.sansi.sansilab.components.file.NotDirectoryException;
import com.dianping.sansi.sansilab.entities.Result;
import com.dianping.sansi.sansilab.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/listDir",method = RequestMethod.POST)
    @ResponseBody
    public Result queryModuleTable(String path) {
        Result result = new Result();
        List<HashMap<String, Object>> fileList;
        if(path==null){
            result.put("errCode", ErrorCode.CODE_INVALID_PARAMETER);
            return result;
        }
        try {
            fileList= fileService.listDirectory(path);
        } catch (FileNotFoundException e) {
            result.put("errCode", ErrorCode.CODE_FILE_NOT_FOUND);
            return result;
        } catch (NotDirectoryException e) {
            result.put("errCode", ErrorCode.CODE_NOT_DIRECTORY);
            return result;
        } catch (AccessDeniedException e) {
            result.put("errCode", ErrorCode.CODE_FILE_ACCESS_DENIED);
            return result;
        }
        return result;
    }
}
