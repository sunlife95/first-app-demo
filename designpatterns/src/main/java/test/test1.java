package test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author sunlife
 * @version 1.0
 * @date 2019/9/2 15:22
 */
public class test1 {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = list.stream().filter(a -> a == 1).collect(Collectors.toList());
        System.out.println(list.size());
        System.out.println(list1.size());
    }
}
