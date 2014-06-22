package com.dianping.sansi.sansilab.component.file;

import java.io.*;

/**
 * Created by lenovo on 2014/6/22.
 */
public class TextFileReader extends BufferedReader{
    private FileReader fr;

    public TextFileReader(Reader reader) throws FileNotFoundException {
        super(reader);
    }

    public TextFileReader(File f) throws FileNotFoundException {
        super(new FileReader(f));
    }

    public TextFileReader(String path) throws FileNotFoundException {
        super(new FileReader(new File(path)));
    }

    public String readLine() throws IOException {
        return super.readLine();
    }

    public void close(){
        try {
            super.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
