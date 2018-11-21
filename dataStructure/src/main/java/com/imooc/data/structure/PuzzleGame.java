package com.imooc.data.structure;

public class PuzzleGame {
    public static void main(String[] args) {
        char[][] a = createTwoDimensionalArray();
        String[] d = dictionary();
        findWord(d, a);
    }

    /**
     * 生成row行，col列的二维数组
     */
    public static char[][] createTwoDimensionalArray() {
        char[][] a = {{'t', 'h', 'i', 's'}, {'w', 'a', 't', 's'}, {'o', 'a', 'h', 'g'}, {'f', 'g', 'd', 't'}};
        return a;
    }

    public static String[] dictionary() {
        String[] d = {"this", "two", "fat", "that"};
        return d;
    }

    public static void findWord(String[] d, char[][] a) {
        for (int i = 0; i < d.length; i++) {//挨个循环查找词板里的单词
            String word = d[i];
            int len = word.length();//先确定长度
            char firstc = word.charAt(0);//第一个字符
            for (int j = 0; j < a.length; j++) {//遍历二维数组
                for (int k = 0; k < a[j].length; k++) {//遍历二维数组
                    if (a[j][k] == firstc) {//比较首字母，相等继续判断
                        compareLenth(len, a, j, k, word, len);
                    } else {
                        continue;//不等，继续遍历
                    }
                }
            }
        }
    }

    public static void compareLenth(int lenth, char[][] a, int j, int k, String word, int len) {
        int[] lenths = new int[8];//正横，负横，正竖，负竖，斜右上，斜左下，斜左上，斜左下   加当前字母，往不同方向可以组成的字母串的最大长度
        lenths[0] = a[0].length - k;//向右
        lenths[1] = k + 1;//向左
        lenths[2] = j + 1;//向上
        lenths[3] = a.length - j;//向下
        lenths[4] = j + 1 < a[0].length - k ? j + 1 : a[0].length - k;//斜右上
        lenths[5] = a.length - j < a[0].length - k ? a.length - j : a[0].length - k;//斜右下
        lenths[6] = j + 1 < k + 1 ? j + 1 : k + 1;//斜左上
        lenths[7] = k + 1 < a.length - j ? k + 1 : a.length - j;//斜左下
        for (int x = 0; x < lenths.length; x++) {
            if (lenths[x] >= lenth) {
                compareWord(x, a, j, k, word, len);
            } else {
                continue;
            }

        }
    }

    public static void compareWord(int x, char[][] a, int j, int k, String word, int len) {
        String newStr = "";
        switch (x) {
            case 0://以[j][k]为原点，正横向，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j][k + i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + j + "," + (k + len - 1) + ")");
                break;
            case 1://以[j][k]为原点，负横向，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j][k - i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + j + "," + (k - len + 1) + ")");
                break;
            case 2://以[j][k]为原点，正竖向（向上），找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j - i][k]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j - len + 1) + "," + k + ")");
                break;
            case 3://以[j][k]为原点，负竖向（向下），找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j + i][k]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j + len - 1) + "," + k + ")");
                break;
            case 4://以[j][k]为原点，斜右上，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j - i][k + i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j - len + 1) + "," + (k + len - 1) + ")");
                break;
            case 5://以[j][k]为原点，斜右下，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j + i][k + i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j + len - 1) + "," + (k + len - 1) + ")");
                break;
            case 6://以[j][k]为原点，斜左上，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j - i][k - i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j - len + 1) + "," + (k - len + 1) + ")");
                break;
            case 7://以[j][k]为原点，斜左下，找len个字符组成string,与word比较，一样就输出结果
                for (int i = 0; i < len; i++) {
                    newStr += String.valueOf(a[j + 1][k - i]);
                }
                if (word.equals(newStr))
                    System.out.println(word + "位置：(" + j + "," + k + ")至(" + (j + len - 1) + "," + (k - len + 1) + ")");
                break;
            default:
                break;
        }
    }
}
