package org.sansilab.leetcode.problem;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.sansilab.leetcode.parser.ParamParser;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.List;
import java.util.StringJoiner;

public abstract class AbstractProblem {

    protected List<String[]> inputList;

    protected List outputList;

    protected List<ParamParser> parserList;

    public AbstractProblem(){
        inputList = Lists.newArrayList();
        outputList = Lists.newArrayList();
        parserList = Lists.newArrayList();
    }

    protected void printResult(Object result){

        System.out.println(JsonUtils.toJson(result));
    }

    protected List<Object[]> generateInput(){
        List<Object[]> list = Lists.newArrayList();
        for (String[] input:inputList) {
            Object[] arr = new Object[input.length];
            for (int i = 0; i < input.length; i++) {
                arr[i] = parserList.get(i).parse(input[i]);
            }
            list.add(arr);
        }
        return list;
    }

    protected void runTest(){
        init();
        List<Object[]> params = generateInput();

        for (int i = 0; i < inputList.size(); i++) {
            System.out.println("start test case #"+i);
            String inputStr = Joiner.on(", ").join(inputList.get(i));
            System.out.println("input: "+ inputStr);
            Object[] args = params.get(i);
            long s = System.currentTimeMillis();
            Object ret = execute(args);
            long e = System.currentTimeMillis();
            String output = JsonUtils.toJson(ret);
            System.out.println("output: "+ output);
            String expect = JsonUtils.toJson(outputList.get(i));
            System.out.println("expected: "+ expect);
            System.out.println("took: "+(e-s));
        }
    }

    abstract void init();

    abstract Object execute(Object[] input);

    protected void addInput(String... params){
        inputList.add(params);
    }
}
