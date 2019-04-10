package com.imooc.data.structure.question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/10 19:39
 */
public class Land {

    static int wayNum = 0;

    static int[][] arr = {
            {1, 2, 3, 4, 5},
            {1, 4, 8, 7, 6},
            {1, 2, 9, 10, 5},
            {1, 2, 3, 10, 5},
            {1, 2, 3, 4, 5}};
    static int m = 5;
    static int n = 5;

    public static void main(String[] args) {
        System.out.println(findNextPoint(0, 0, 2, 3));
    }

    public static void findWay(Point A, Point B) {
        System.out.println(findNextPoint(0, 0, 0, 4));
    }

    static int findNextPoint(int x1, int y1, int x2, int y2) {
        System.out.println("[" + x1 + "," + y1 + "],[" + x2 + "," + y2 + "]");
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int ways = 0;
        if ( y1 - 1 >= 0 && arr[x1][y1] < arr[x1][y1 - 1]) {
            ways += findNextPoint(x1 , y1 - 1, x2, y2);
        }
        if ( y1 + 1 <= m - 1 && arr[x1][y1] < arr[x1][y1 + 1]) {
            ways += findNextPoint(x1, y1 + 1, x2, y2);
        }
        if (x1 - 1 >= 0 && arr[x1][y1] < arr[x1 - 1][y1]) {
            ways += findNextPoint(x1 - 1, y1 , x2, y2);
        }
        if (x1 + 1 <= n - 1 && arr[x1][y1] < arr[x1 + 1][y1]) {
            ways += findNextPoint(x1 + 1, y1, x2, y2);
        }
        return ways;
    }


    class Point {
        int x;
        int y;
        int h;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //1 找到下一步

}
