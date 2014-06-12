package com.dianping.sansi.sansilab.component.file;

import com.dianping.sansi.sansilab.component.file.FileSystemUtils;
import com.dianping.sansi.sansilab.component.file.NotDirectoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2014/6/12.
 */
public class FileUtils {
    public InputStream getFileInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(new File(path));
    }

    public boolean exists(String path){
        File file=new File(path);
        return file.exists();
    }
}
