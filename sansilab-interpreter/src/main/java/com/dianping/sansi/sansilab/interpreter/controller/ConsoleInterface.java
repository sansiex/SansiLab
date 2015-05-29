package com.dianping.sansi.sansilab.interpreter.controller;

import java.io.Console;

/**
 * Created by sansi on 2014/6/5.
 */
public class ConsoleInterface {
    public void start(){
        Console csl=System.console();
        if(csl==null){
            throw new IllegalStateException("Console is not available.");
        }
//        while(true){
//            System.out.println("Please input statement:");
//            Calculator parser=new Calculator(System.in);
//            try {
//                parser.start();
//            } catch (ParseException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }
}
