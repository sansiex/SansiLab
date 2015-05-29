package com.dianping.sansi.sansilab.components.console.command;

import com.dianping.sansi.sansilab.components.console.command.GrepCommand;

import java.util.HashMap;

/**
 * Created by lenovo on 2014/6/14.
 */
public class CommandManager {
    private static CommandManager instance;
    private CommandManager(){
        register("cat",new CatCommand());
        register("grep",new GrepCommand());
    }

    public static CommandManager getInstance(){
        if(instance==null){
            instance=new CommandManager();
        }
        return instance;
    }

    private HashMap<String, ConsoleCommand> cmdMap=new HashMap<>();

    public void register(String name, ConsoleCommand cmd){
        cmdMap.put(name,cmd);
    }

    public void remove(String name){
        cmdMap.remove(name);
    }

    public ConsoleCommand getCommand(String name){
        return cmdMap.get(name);
    }
}
