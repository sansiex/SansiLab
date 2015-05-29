package com.dianping.sansi.sansilab.components.console.command;

import com.dianping.sansi.sansilab.components.console.Console;
import com.dianping.sansi.sansilab.components.console.ConsoleTask;

import java.io.*;

/**
 * Created by lenovo on 2014/6/14.
 */
public abstract class ConsoleCommand {

    /**
     *
     * @param cmd
     * First element of the array is the name of the command and the following elements are actual arguments
     * @param reader
     * @param writer
     */
    abstract public void doCommand(String[] cmd, Reader reader, Writer writer, ConsoleTask task) throws IOException;

    public void writeLine(Writer w, String line) throws IOException {
        w.write(line+System.lineSeparator());
    }
}
