package com.dianping.sansi.sansilab.component.console;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sansi on 2014/5/21.
 */
public interface Console {
    void exec(String cmd) throws IOException, InterruptedException;
}
