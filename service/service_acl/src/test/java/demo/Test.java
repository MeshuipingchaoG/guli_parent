package demo;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * @BelongsProject: guli_parent
 * @BelongsPackage: demo
 * @Author: chenzhiwei
 * @CreateTime: 2022-08-12  21:43
 * @Description: TODO
 * @Version: 1.0
 */
public class Test {
    @org.junit.Test
    public void test(){
        // 定义一个字符串数组
        String[] str = new String[]{"A","B","B","C","E","F"};
        // 返回随机生成的数
        String[] res = new String[3];
        // 定义指针
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            // 判断结果数组是否满了，满了即可结束
            if (index == 3) {
                break;
            }
            // 随机生成一个数[0,6),指定下标
            Random random = new Random();
            int rIndex = random.nextInt(6);
            // 从数组中获取下标的值
            String s = str[rIndex];
            // 判断结果数组中是否存在此下标的字符串
            if (i != 0) {
                for (String r : res) {
                    if (s.equals(r)) {
                        break;
                    } else {
                        res[index] = s;
                        index++;
                        break;
                    }
                }
            }

        }
        for (String s : res) {
            System.out.println(s);
        }
    }
}
