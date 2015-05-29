package backup.java.com.dianping.sansi.sansilab.action;

/**
 * Created by sansi on 2014/5/9.
 */
public class PageAction extends BaseAction {
    private String page;

    private String forward;

    private final String REDIRECT="redirect";

    public String redirect(){
        if(page!=null){
            forward=page;
        }
        return REDIRECT;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }
}
