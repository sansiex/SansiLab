package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;

import java.io.*;

/**
 * Created by lenovo on 2014/6/14.
 */
public interface ConsoleCommand {
    /**
     *
     * @param cmd
     * First element of the array is the name of the command and the following elements are actual arguments
     * @param reader
     * @param writer
     */
    void doCommand(String[] cmd, Reader reader, Writer writer, Console csl) throws IOException;

    default public void writeLine(Writer w, String line) throws IOException {
        w.write(line+System.lineSeparator());
    }
}
