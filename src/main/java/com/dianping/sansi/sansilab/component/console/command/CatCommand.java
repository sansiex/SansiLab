package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by lenovo on 2014/6/12.
 */
public class CatCommand implements ConsoleCommand{

    @Override
    public void doCommand(String[] args, PipedInputStream is, PipedOutputStream os, Console csl) {
    }

    @Override
    public void doCommand(String[] args, Reader is, Writer os, Console csl) {

    }
}
