package backup.java.com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.components.file.NotDirectoryException;
import com.dianping.sansi.sansilab.service.FileService;
import org.apache.struts2.json.annotations.JSON;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2014/5/20.
 */
public class DirectoryAction extends BaseAction {

    FileService fileService;

    //input params
    String path;

    //ouput params
    List<HashMap<String,Object>> fileList;
    protected int code=BaseAction.CODE_SUCCESS;

    public String listDir(){
        if(path==null){
            code=CODE_INVALID_PARAMETER;
            return SUCCESS;
        }
        try {
            fileList= fileService.listDirectory(path);
        } catch (FileNotFoundException e) {
            code=CODE_FILE_NOT_FOUND;
            return SUCCESS;
        } catch (NotDirectoryException e) {
            code=CODE_NOT_DIRECTORY;
            return SUCCESS;
        } catch (AccessDeniedException e) {
            code=CODE_FILE_ACCESS_DENIED;
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
    public int getCode(){
        return code;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
