package com.dianping.sansi.sansilab.component.console;

import com.dianping.sansi.sansilab.component.event.EventCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by sansi on 2014/5/21.
 */
public class WinConsole implements Console{
    public static final String CONSOLE_OUTPUT="console_output";

    @Override
    public void exec(String cmd) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec(cmd,new String[]{"c:/"});
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        int ln=0;
        while((line = reader.readLine())!= null){
//            HashMap<String,Object> params=new HashMap<>();
//            params.put("output",line);
//            params.put("cmd",cmd);
//            params.put("lineNumber",ln++);
//            EventCenter.getInstance().dispatchSyn(CONSOLE_OUTPUT,params,this);

            System.out.println(line);
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
    }

    public static void main(String[] args) {
        WinConsole wc=new WinConsole();
        try {
            wc.exec("dir");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
