package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

public class MultiplyStrings extends AbstractProblem {

    public String multiply(String num1, String num2) {
        int len = 9;
        long[] val1 = new long[(num1.length()-1)/len+1];
        long[] val2 = new long[(num2.length()-1)/len+1];
        long[] r = new long[(220-1)/len+1];
        String zero = "000000000";
        long limit = 1000000000;

        for (int i = 0; i < r.length; i++) {
            r[i]=0l;
        }

        for (int i = num1.length()-1; i > -1; i-=9) {
            int start = Math.max(0, i-8);
            long val = Long.parseLong(num1.substring(start, i+1));
            val1[val1.length - 1 - i/9] = val;
        }
        for (int i = num2.length()-1; i > -1; i-=9) {
            int start = Math.max(0, i-8);
            long val = Long.parseLong(num2.substring(start, i+1));
            val2[val2.length - 1 - i/9] = val;
        }
        for (int i = 0; i < val1.length; i++) {
            for (int j = 0; j < val2.length; j++) {
                long mul = val1[i]*val2[j];
                int ind = i+j;

                long cr = r[ind] + mul;
                while (cr >= limit) {
                    long actual = cr % limit;
                    r[ind] = actual;
                    ind++;
                    cr = cr/limit + r[ind];
                }
                r[ind] = cr;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = r.length-1; i > -1 ; i--) {
            long val = r[i];
            if (val == 0l) {
                if (sb.length() == 0) {
                    if (i == 0) {
                        sb.append("0");
                    } else {
                        continue;
                    }
                } else {
                    sb.append(zero);
                }
            } else {
                String str = String.valueOf(val);
                if (sb.length() != 0) {
                    sb.append(zero.substring(0, 9-str.length()));
                }
                sb.append(str);
            }
        }

        return sb.toString();
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        parserList.add(new StringParser());

        addInput("2", "3");
        outputList.add("6");
        addInput("123", "456");
        outputList.add("56088");
        addInput("1234567890", "10");
        outputList.add("12345678900");
        addInput("0", "0");
        outputList.add("0");
        addInput("1234567890", "0");
        outputList.add("0");
        addInput("498828660196", "840477629533");
        outputList.add("0");
        addInput("123456789","987654321");
        outputList.add("121932631112635269");
    }

    @Override
    Object execute(Object[] input) {
        return multiply((String)input[0], (String) input[1]);
    }

    public static void main(String[] args) {
        new MultiplyStrings().runTest();
    }
}
