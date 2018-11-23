package com.imooc.data.structure.generic;

import java.io.*;

public class IncludeFile {
    public static void main(String[] args) {
        getFileContent("C:\\Users\\zmn-E330\\Desktop\\文件测试.txt");
    }

    private static void getFileContent(String filepath) {
        File file = new File(filepath);
        InputStream fis = null;
        BufferedReader fr = null;
        try {
            if (!file.exists()) {
                System.out.println("file :" + filepath +" is not exists");
                return;
            }
            fis = new FileInputStream(file);
            fr = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String bufferStr = null;
            while ((bufferStr = fr.readLine()) != null) {
                if (bufferStr.startsWith("#include")) {
                    String fileName = bufferStr.substring(9,bufferStr.indexOf(";") == -1 ? bufferStr.length() : bufferStr.indexOf(";")).trim();
                    getFileContent(file.getParent() + "\\" + fileName);
                } else {
                    System.out.println(bufferStr);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fr != null) {
                    fr.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
