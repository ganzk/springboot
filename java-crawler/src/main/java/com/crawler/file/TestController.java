package com.crawler.file;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TestController {



    public static void main(String[] args) throws Exception {

        long l = TimeUnit.MILLISECONDS.toSeconds(135468);
        System.out.println(l);

// some code


        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\track\\Desktop\\ma.zip");
//        unzipInputStream(fileInputStream, 139L);

        importZip(fileInputStream);

//        for (int i = 0; i < 1000; i++) {
//            String s = "C:\\Users\\track\\Desktop\\ma_batch_7eed7a102cdd4d14ab8c7e6679bf975a\\batch_0.txt";
//            String s1 = "C:\\Users\\track\\Desktop\\ma_batch_7eed7a102cdd4d14ab8c7e6679bf975a\\batch_0_" + i + ".txt";
//            Files.copy(new File(s).toPath(), new File(s1).toPath());
//        }


//        FileOutputStream os = new FileOutputStream("C:\\Users\\track\\Desktop\\ma.zip");
//        ZipOutputStream zos = new ZipOutputStream(os);
//        File file = new File("C:\\Users\\track\\Desktop\\ma_batch_7eed7a102cdd4d14ab8c7e6679bf975a");
//        File[] files = file.listFiles();
//        for (int i = 0; i < files.length; i++) {
//            Path path = files[i].toPath();
//            ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
//            zipEntry.setSize(Files.size(path));
//            zos.putNextEntry(zipEntry);
//            Files.copy(path, zos);
//            zos.closeEntry();
//
//        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
        System.out.println(seconds);
    }

    private static void copyFileUsingJava7Files(File source, File dest)
            throws IOException {

    }




    private static void unzipInputStream(InputStream zipInputStream, Long planId) {
//        ZipFile zipFile = new ZipFile();


        int count = 0;
        try (ZipInputStream zip = new ZipInputStream(zipInputStream, Charset.forName("UTF-8"))) {
            ZipEntry zipEntry = null;
            BufferedInputStream bs = new BufferedInputStream(zip);
            byte[] bytes = null;
            while ((zipEntry = zip.getNextEntry()) != null) {
                String fileName_zip = zipEntry.getName();
                File file = new File(fileName_zip);
                if (fileName_zip.endsWith("/")) {
                    file.mkdir();
                    continue;
                } else {
//                    if ((int) zipEntry.getSize() <= 0) {
//                        // 读取完毕
//                        continue;
//                    }
//                    long size = zipEntry.getSize();
                    // 有的文件没有设置size,可能是-1
//                    if (size == -1) {
//                    if (true) {
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        while (true) {
//                            int byteZip = zipInputStream.read();
//                            if (byteZip == -1) break;
//                            baos.write(byteZip);
//                        }
//                        bytes = baos.toByteArray();

//                        System.out.println(new String(bytes, "UTF-8"));

//
//                        int byteZip = zipInputStream.read();
//                        BufferedReader br1 = new BufferedReader(new InputStreamReader(byteArrayInputStream));
//                        bytes = new byte[byteZip];
//                        bs.read(bytes, 0 , byteZip);

//                        ByteArrayInputStream bInput = new ByteArrayInputStream(bytes);
//                        int c;
//                        while(( c= bInput.read())!= -1) {
//                            System.out.println(Character.toUpperCase((char)c));
//                        }
//                        bInput.reset();

//                        baos.close();





//                        int size1 = 0;
//                        byte[] buffer = new byte[1024];
//                        while (true) {
//                            size1 = zipInputStream.read(buffer, 0, buffer.length);
//                            if (size1 <= 0) {
//                                break;
//                            }
//                            baos.write(buffer, 0, size1);
//                        }
//                        baos.flush();
//                        baos.close();
//
//                        byte[] be = baos.toByteArray();
//                        String rtn = new String(be, "GBK");
//                        System.out.println(rtn);

//                        int byteZip = zip.read();
//                        bytes = new byte[byteZip];
//                        bs.read(bytes, 0, byteZip);
//
//                    } else {
//                        bytes = new byte[(int) zipEntry.getSize()];
//                        bs.read(bytes, 0, zip.read());
//                    }

//                    bytes = new byte[1024];
//                    if (bs.read(bytes, 0, (int) zipEntry.getSize()) != -1) {
//
//                    }

                    // 编码格式
//                    String charsetName = "GBK";//或GB2312，即ANSI
//                    if (bytes[0] == -1 && bytes[1] == -2 ) //0xFFFE
//                        charsetName = "UTF-16";
//                    else if (bytes[0] == -2 && bytes[1] == -1 ) //0xFEFF
//                        charsetName = "Unicode";//包含两种编码格式：UCS2-Big-Endian和UCS2-Little-Endian
//                    else if(bytes[0]==-27 && bytes[1]==-101 && bytes[2] ==-98)
//                        charsetName = "UTF-8"; //UTF-8(不含BOM)
//                    else if(bytes[0]==-17 && bytes[1]==-69 && bytes[2] ==-65)
//                        charsetName = "UTF-8"; //UTF-8-BOM
//                    System.out.println(charsetName);

                    // 转码
//                    String test = "测试";
//                    String test_gbk_utf8 = new String(test.getBytes(StandardCharsets.UTF_8), "gbk");
//                    System.out.println(test_gbk_utf8);//娴嬭瘯
//                    String test_utf8_gbk = new String(test_gbk_utf8.getBytes("gbk"), StandardCharsets.UTF_8);
//                    System.out.println(test_utf8_gbk);//测试


//                    InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
////                    BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream));
//                    String line = null;
//                    while ((line = br.readLine()) != null) {
//
//                        System.out.println(line);
////                        line = new String(line.getBytes("gbk"), StandardCharsets.UTF_8);
//
////                        byte[] utf8Bytes = line.getBytes("UTF-8");
////                        line = new String(utf8Bytes, "UTF-8");
////                        System.out.println(line);
//
//                        if (userList.size() >= 1000) {
//                            userAllList.add(userList);
//                            userList = new ArrayList<>();
//                        }
//                        if (StringUtils.isNotEmpty(line)) {
//                            UserInfoEntity userInfoEntity = this.handUserInfo(line, planId);
//                            if (userList.size() % 9 == 0) {
//                                // 10% 实验组
//                                userInfoEntity.setType(MathNumber.THREE);
//                            }
//                            userList.add(userInfoEntity);
//                            count++;
//                        }
//                    }
//                    br.close();




                    bytes = new byte[(int) zipEntry.getSize()];
                    bs.read(bytes, 0, (int) zipEntry.getSize());
                    InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void importZip(InputStream inputStream) throws IOException {

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (zipEntry.isDirectory()) {
                // do nothing
            }else {
                String name = zipEntry.getName();
                long size = zipEntry.getSize();
                // unknown size
                // ZipEntry的size可能为-1，表示未知
                // 通过上面的几种方式下载，就会产生这种情况
                if (size == -1) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while (true) {
                        int bytes = zipInputStream.read();
                        if (bytes == -1) break;
                        baos.write(bytes);
                    }
                    baos.close();
//                    String s = new String(baos.toByteArray());
//                    System.out.println(String.format("Name:%s,Content:%s",name,new String(baos.toByteArray())));

                    InputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
                    BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        System.out.println("==========");
                    }


                } else { // ZipEntry的size正常
                    byte[] bytes = new byte[(int) zipEntry.getSize()];
                    zipInputStream.read(bytes, 0, (int) zipEntry.getSize());
                    System.out.println(String.format("Name:%s,Content:%s",name,new String(bytes)));
                }
            }

        }
        zipInputStream.closeEntry();
        zipInputStream.close();
    }


    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }



}
