package org.sansilab.leetcode.problem;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.sansilab.leetcode.base.Operation;
import org.sansilab.leetcode.utils.JsonUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author sansi
 * @date 2022-09-14
 */
public abstract class AbstractDataStructure {

    protected static List<List<Method>> methodList = new ArrayList<>();

    protected static List<List<Object[]>> inputList = new ArrayList<>();

    protected static List<List<Object>> outputList = new ArrayList<>();

    protected static List<Object[]> constructorInputList = new ArrayList<>();

    protected static Map<String, Method> methodMap = new HashMap<>();

    protected static Constructor constructor;

    protected static void runTest(Class<? extends AbstractDataStructure> cls, Runnable init) {
        try {
            Method[] methods = cls.getDeclaredMethods();
            constructor = cls.getConstructors()[0];

            for (Method method : methods) {
                Operation ann = method.getAnnotation(Operation.class);
                if (ann == null) {
                    continue;
                }
                if (method.getName().equals(constructor.getName())) {
                    continue;
                }

                methodMap.put(ann.value(), method);
            }

            init.run();

            List<Integer> errors = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {

                List<Object[]> inputSeq = inputList.get(i);
                List<Method> methodSeq = methodList.get(i);
                List<Object> outputSeq = outputList.get(i);
                boolean pass = true;
                long s = System.currentTimeMillis();
                System.out.println("start test case #" + i + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                Object[] constructorInput = constructorInputList.get(i);
                System.out.println(
                        "Call constructor[" + constructor.getName() + "] (" + JsonUtils.toJson(constructorInput) + ")");
                AbstractDataStructure testCase = (AbstractDataStructure) constructor.newInstance(constructorInput);

                for (int j = 0; j < inputSeq.size(); j++) {
                    Object[] input = inputSeq.get(j);
                    Method method = methodSeq.get(j);
                    Object output = outputSeq.get(j);

                    System.out.println(String.format("Call method[%s](%s)", method.getName(), JsonUtils.toJson(input)));
                    Object ret = method.invoke(testCase, input);
                    if (output == null) {
                        continue;
                    }
                    if (Objects.equals(ret, output)) {
                        System.out.println(String.format("[pass]return %s expect %s", ret, output));
                    } else {
                        pass = false;
                        System.out.println(String.format("!!![FAIL]return %s expect %s !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", ret, output));
                    }
                }
                if (!pass) {
                    errors.add(i);
                }

                long e = System.currentTimeMillis();
                System.out.println("took: " + (e - s));
            }

            if (errors.isEmpty()) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Error cases: " + Joiner.on(", ").join(errors));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected static void addTestCase(String methodStr, String inputStr, String outputStr) {
        JsonArray methodJson = JsonUtils.parseArray(methodStr);
        JsonArray inputJson = JsonUtils.parseArray(inputStr);
        JsonArray outputJson = JsonUtils.parseArray(outputStr);

        if (!inputJson.get(0).isJsonNull()) {
            JsonArray arr = inputJson.get(0).getAsJsonArray();
            Object[] constructorInput = new Object[arr.size()];
            Class[] types = constructor.getParameterTypes();
            for (int i = 0; i < arr.size(); i++) {
                constructorInput[i] = parseJsonElement(arr.get(i), types[i]);
            }
            constructorInputList.add(constructorInput);
        }

        List<Method> methods = new ArrayList<>();
        List<Object[]> inputs = new ArrayList<>();
        List<Object> outputs = new ArrayList<>();
        for (int i = 1; i < methodJson.size(); i++) {
            String methodName = methodJson.get(i).getAsString();
            Method method = methodMap.get(methodName);
            methods.add(method);
            JsonArray curInputJson = inputJson.get(i).getAsJsonArray();
            Object[] input = parseInput(method, curInputJson);
            inputs.add(input);
            Object output = null;
            JsonElement outputEle = outputJson.get(i);
            if (!outputEle.isJsonNull()) {
                output = parseJsonElement(outputEle, method.getReturnType());
            }
            outputs.add(output);
        }
        methodList.add(methods);
        inputList.add(inputs);
        outputList.add(outputs);
    }

    private static Object[] parseInput(Method method, JsonArray inputArr) {
        Class[] paramTypes = method.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            JsonElement ele = inputArr.get(i);
            Object val = parseJsonElement(ele, paramTypes[i]);
            params[i] = val;
        }
        return params;
    }

    private static Object parseJsonElement(JsonElement ele, Class type) {
        return JsonUtils.fromJson(ele.getAsString(), type);
    }
}
