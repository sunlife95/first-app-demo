package com.imooc.leetcode.q3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunlife
 * @date:2020/5/19 4:45 下午
 * description:
 */
public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        Map<Character, Integer> recordMap = new HashMap<>(128, 1);
        int[] hash = new int[128];

        int flag = 0,left = 0,result = 0;

        char[] charArr = s.toCharArray();

        for (int right = 0; right < charArr.length; right++) {
            hash[charArr[right]]++;
            while (hash[charArr[right]] != 1) {
                hash[charArr[left]]--;
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] sArr = s.toCharArray();
        int[] hash = new int[128];
        int left = 0, result = Integer.MIN_VALUE;
        for (int right = 0; right < sArr.length; right++) {
            hash[sArr[right]]++;
            while (hash[sArr[left]] != 1) {
                hash[sArr[left]]--;
                left++;
            }
            result = Math.max(result, right - left + 1);
        }


        return  result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

        Short s = new Short("2");
        System.out.println(s.equals((short)2));
    }
}
