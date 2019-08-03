package org.sansilab.leetcode.problem;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.sansilab.leetcode.parser.ParamParser;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbstractProblem {

    protected List<String[]> inputList;

    protected List outputList;

    protected List<ParamParser> parserList;

    protected ParamParser outputParser;

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
        List<Integer> errors = Lists.newArrayList();

        for (int i = 0; i < inputList.size(); i++) {
            System.out.println("start test case #"+i+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            String inputStr = Joiner.on(", ").join(inputList.get(i));
            System.out.println("input: "+ inputStr);
            Object[] args = params.get(i);
            long s = System.currentTimeMillis();
            Object ret = execute(args);
            long e = System.currentTimeMillis();
            String output = JsonUtils.toJson(ret);
            System.out.println("output: "+ output);
            String expect = JsonUtils.toJson(outputList.get(i));
            if (!Objects.equals(output, expect)) {
                errors.add(i);
            }
            System.out.println("expected: "+ expect);
            System.out.println("took: "+(e-s));
        }

        if (errors.isEmpty()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Error cases: " + Joiner.on(", ").join(errors));
        }
    }

    abstract void init();

    abstract Object execute(Object[] input);

    protected void addInput(String... params){
        inputList.add(params);
    }

    public void setOutputParser(ParamParser outputParser) {
        this.outputParser = outputParser;
    }

    protected void addOutput(String out) {
        Object val = outputParser.parse(out);
        outputList.add(val);
    }
}
