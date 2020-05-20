package SingleModel;

/**
 * 类描述
 *
 * @author sunlife
 * @version 1.0
 * @date 2019/9/3 14:42
 */
public class Minister {
    public static void main(String[] args) {
        for (int i = 0 ; i < 3 ; i ++) {
            Emperor emperor = Emperor.getInstance();
            emperor.say();
        }
    }
}
