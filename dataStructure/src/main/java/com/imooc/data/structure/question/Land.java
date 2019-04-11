package com.imooc.data.structure.question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/10 19:39
 * 一张n*m的地图上，每个点的海拔高度不同，从当前点只能访问上下左右四个点中还没有到达过的点，
 * 且下一步选择的点的海拔高度必须高于当前点，求从地图中的点A到点B总的路径条数/10^9的余数，
 * 地图左上角坐标为（0 0），右下角坐标为（n-1,m-1）
 */
public class Land {


    static int a = 1000000000;

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {1, 4, 8, 7, 6},
                {1, 2, 9, 10, 5},
                {1, 2, 10, 10, 5},
                {1, 2, 3, 4, 5}
        };
        System.out.println(findNextPoint(0, 0, 2, 3, arr) % a);
    }


    static int findNextPoint(int x1, int y1, int x2, int y2 , int[][] arr) {
        System.out.println("[" + x1 + "," + y1 + "]");
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int ways = 0;
        if (y1 - 1 >= 0 && arr[x1][y1] < arr[x1][y1 - 1]) {
            ways += findNextPoint(x1, y1 - 1, x2, y2, arr);
        }
        if (y1 + 1 <= arr[0].length - 1 && arr[x1][y1] < arr[x1][y1 + 1]) {
            ways += findNextPoint(x1, y1 + 1, x2, y2, arr);
        }
        if (x1 - 1 >= 0 && arr[x1][y1] < arr[x1 - 1][y1]) {
            ways += findNextPoint(x1 - 1, y1, x2, y2, arr);
        }
        if (x1 + 1 <= arr.length - 1 && arr[x1][y1] < arr[x1 + 1][y1]) {
            ways += findNextPoint(x1 + 1, y1, x2, y2, arr);
        }
        return ways;
    }


    //1 找到下一步

}
