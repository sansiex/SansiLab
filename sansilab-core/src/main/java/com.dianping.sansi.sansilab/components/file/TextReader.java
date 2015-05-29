package com.dianping.sansi.sansilab.components.file;

import java.io.*;

/**
 * Created by lenovo on 2014/6/22.
 */
public class TextReader extends BufferedReader{
    private FileReader fr;

    public TextReader(Reader reader) throws FileNotFoundException {
        super(reader);
    }

    public TextReader(File f) throws FileNotFoundException {
        super(new FileReader(f));
    }

    public TextReader(String path) throws FileNotFoundException {
        super(new FileReader(new File(path)));
    }

    public String readLine() throws IOException {
        return super.readLine();
    }

    public void close(){
        try {
            super.close();
            if(fr!=null){
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
