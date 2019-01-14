package org.ggq.com.code;

/**
 * 限制 1. + , D , C 都存在限制
 * 
 * 你现在是棒球比赛记录员。
 * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 * @author ggq Date: 2018-11-30 Time: 19:39
 * @version $Id$
 */
public class BaseballGame {

    /**
     * 输入: ["5","-2","4","C","D","9","+","+"]
     * 输出: 27
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {

        int result = 0;
        
        
        if (ops == null || ops.length == 0) {
            return 0;
        }
        
        int activeNumber = Integer.MAX_VALUE;
        int nextNumber = Integer.MAX_VALUE;
        
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("C")) {
                ops[i] = "*";

                for (int j = i - 1; j >= 0; j--) {
                    if (!ops[j].equals("*")) {
                        ops[j] = "*";
                        break;
                    }
                }
            } else if (ops[i].equals("D")) {
                for (int j = i - 1; j >= 0; j--) {
                    if (!ops[j].equals("*")) {
                        ops[i] = String.valueOf(2 * Integer.parseInt(ops[j]));
                        break;
                    }
                }
            } else if (ops[i].equals("+")) {
                int number_1 = Integer.MAX_VALUE;
                int number_2 = Integer.MAX_VALUE;
                for (int j = i - 1; j >= 0; j--) {
                    if (!ops[j].equals("*")) {
                        if (number_1 == Integer.MAX_VALUE) {
                            number_1 = Integer.parseInt(ops[j]);
                        } else {
                            number_2 = Integer.parseInt(ops[j]);
                            break;
                        }
                    }
                }
                ops[i] = String.valueOf(number_1 + number_2);
            }
        }


        for (int i = 0; i < ops.length; i++) {
            if (!ops[i].equals("*")) {
                result += Integer.parseInt(ops[i]);
            } 
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
        
        
        String[] str = {"-60","D","-36","30","13","C","C","-33","53","79"};
        BaseballGame baseballGame = new BaseballGame();
        int temp = baseballGame.calPoints(str);

        System.out.println(temp);
    }
    
}
