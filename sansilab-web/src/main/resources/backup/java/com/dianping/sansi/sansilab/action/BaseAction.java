package backup.java.com.dianping.sansi.sansilab.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by sansi on 2014/5/22.
 */
public class BaseAction extends ActionSupport {
    public static final int CODE_SUCCESS=0;
    public static final int CODE_INVALID_PARAMETER=1;

    public static final int CODE_NOT_DIRECTORY=1001;
    public static final int CODE_FILE_NOT_FOUND=1002;
    public static final int CODE_FILE_ACCESS_DENIED=1003;
    public static final int CODE_FILE_EXISTS=1004;

    public static final int CODE_COMMAND_NOT_FOUND=2001;
    public static final int CODE_INVALID_COMMAND=2002;

//    protected LogService logService;
//
//    public void setLogService(LogService logService) {
//        this.logService = logService;
//    }

}
