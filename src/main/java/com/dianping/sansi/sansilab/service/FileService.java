package com.dianping.sansi.sansilab.service;

import com.dianping.sansi.sansilab.component.file.FileSystemUtils;
import com.dianping.sansi.sansilab.component.file.NotDirectoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sansi on 2014/5/22.
 */
public class FileService {
    public List<HashMap<String,Object>> listDirectory(String path) throws FileNotFoundException, NotDirectoryException, AccessDeniedException {
        path=transformPath(path);
        File[] files= FileSystemUtils.listDir(path);
        ArrayList<HashMap<String,Object>> list=new ArrayList<>(files.length);
        for(File f:files){
            HashMap<String,Object> ent=new HashMap<String,Object>();
            ent.put("name",f.getName());
            ent.put("path",f.getAbsolutePath());
            ent.put("isDir",f.isDirectory());
            list.add(ent);
        }
        return list;
    }

    public List<HashMap<String,Object>> listRoots(){
        File[] roots=FileSystemUtils.listRoots();
        ArrayList<HashMap<String,Object>> list=new ArrayList<>(roots.length);
        for(File r:roots){
            HashMap<String,Object> ent=new HashMap<String,Object>();
            ent.put("name",r.getName());
            ent.put("path",r.getPath());
            ent.put("freeSpace",r.getFreeSpace()/1024/1024/1024);
            ent.put("usableSpace",r.getUsableSpace()/1024/1024/1024);
            ent.put("totalSpace",r.getTotalSpace()/1024/1024/1024);
            list.add(ent);
        }
        return list;
    }

    public InputStream getFileInputStream(String path) throws FileNotFoundException {
        path=transformPath(path);
        return new FileInputStream(new File(path));
    }

    private String transformPath(String path){
        System.out.println("request:"+path);
        return path;
    }
}
