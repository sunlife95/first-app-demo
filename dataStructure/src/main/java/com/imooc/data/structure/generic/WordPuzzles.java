package com.imooc.data.structure.generic;

public class WordPuzzles {
    public static void main(String[] args) {
        solvePuzzle();
    }

    public static void solvePuzzle() {
        String[][] arr = {{"t", "h", "i", "s"}, {"w", "a", "t", "s"}, {"o", "a", "h", "g"}, {"f", "g", "d", "t"}};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
//                System.out.println(arr[i][j]);//起始点 长度？
                getWord(i,j,Direction.LD,arr.length,arr[0].length,arr);
            }
        }
    }

    public static String getWord(int row, int col, Direction direction, int allRow, int allCol , String[][] arr) {
        int length;//需要循环的长度，单词最少两个字符
        StringBuilder sb = new StringBuilder(arr[row][col]);
        switch (direction) {
            case TOP:
                length = row;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row - i][col]);
                    System.out.println(sb.toString());
                }
                break;
            case LEFT:
                length = col;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row][col - i]);
                    System.out.println(sb.toString());
                }
                break;
            case DOWN:
                length = allRow - row - 1;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row + i][col]);
                    System.out.println(sb.toString());
                }
                break;
            case RIGHT:
                length = allCol - col -1 ;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row][col + i]);
                    System.out.println(sb.toString());
                }
                break;
            case TL:
                length = row < col ? row : col;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row - i][col - i]);
                    System.out.println(sb.toString());
                }
                break;
            case LD:
                length = row < allCol - col -1 ? row : allCol - col -1;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row - i][col + i]);
                    System.out.println(sb.toString());
                }
                break;
            case DR:
                length = allRow - row -1 < allCol - col -1 ? allRow - row -1 : allCol - col -1;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row + i][col + i]);
                    System.out.println(sb.toString());
                }
                break;
            case RT:
                length = allRow - row -1 < col ? allRow - row -1 : col;
                if (length == 0)
                    break;
                for (int i = 1 ; i <= length ; i++ ){
                    sb.append(arr[row + i][col - i]);
                    System.out.println(sb.toString());
                }
                break;
        }

        return null;

    }
}
