package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by lenovo on 2014/6/14.
 */
public interface ConsoleCommand {
    /**
     *
     * @param args
     * First element of the array is the name of the command and the following elements are actual arguments
     * @param is
     * @param os
     */
    void doCommand(String[] args, PipedInputStream is, PipedOutputStream os, Console csl);

    /**
     *
     * @param args
     * First element of the array is the name of the command and the following elements are actual arguments
     * @param is
     * @param os
     */
    void doCommand(String[] args, Reader is, Writer os, Console csl);
}
