package com.imooc.data.structure.list.ArrayList;

import java.util.AbstractList;

public class ArrayList<T> {
  private static final int DEFAULT_CAPACITY = 10;
  private Object[] elementData ;
  //新建
  public static void main(String[] args) {
      Class clazz = null;
      try {
          clazz = Class.forName("java.util.ArrayList");
          clazz.newInstance();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          e.printStackTrace();
      } catch (InstantiationException e) {
          e.printStackTrace();
      }
  }
}
