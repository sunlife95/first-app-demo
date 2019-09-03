package SingleModel;

/**
 * 类描述
 *
 * @author sunlife
 * @version 1.0
 * @date 2019/9/3 14:28
 */
public class Emperor {
    public static final Emperor emperor = new Emperor();

    private Emperor() {

    }

    public static Emperor getInstance() {
        return emperor;
    }

    public void say() {
        System.out.println("我，秦始皇，打钱");
    }
}
