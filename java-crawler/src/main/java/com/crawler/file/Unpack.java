package com.crawler.file;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Unpack {

    public static void main(String[] args) throws IOException {

        LocalDateTime nowTime = LocalDateTime.now();
        // 当前时间
        String nowTimeString = asMinuteString(nowTime);
        // 上一分钟/小时
        String lastTimeString = asMinuteString(nowTime.minusMinutes(1));
        // 上上分钟/小时
        String beforeLastTimeString = asMinuteString(nowTime.minusMinutes(2));
        System.out.println(nowTimeString);
        System.out.println(lastTimeString);
        System.out.println(beforeLastTimeString);

        Date date = parseDate("2024-12-21 18:55:30", "yyyy-MM-dd HH:mm:ss");
        System.out.println(GreaterThanOneHour(date, new Date()));






//        test();

//        File file = new File("C:\\Users\\track\\Desktop\\Desktop.zip");
//        unzip(file);

        // https://rqqx-test-file.haier.net/ma/test/Desktop06eb919d7a80464db580efa79c437229.zip
        // https://rqqx-test-file.haier.net/ma/test/Desktop06eb919d7a80464db580efa79c437229.zip
        // https://rqqx-test-file.haier.net/ma/test/Desktop587026d2d3354b718737d2de4a8840d2.zip
        // https://rqqx-test-file.haier.net/ma/test/Desktop6fce44117e05426f9daf6702de6beaf4.zip
        // https://rqqx-test-file.haier.net/ma/test/Desk368d3e137ffea4b34a183f5faa622cc4b.zip
//        String path = "https://rqqx-test-file.haier.net/ma/test/Desk368d3e137ffea4b34a183f5faa622cc4b.zip";
//        InputStream download = download(path);
//        List<File> files = unzipInputStream(download);
//        List<File> files = unzipInputStream1(download);

//        String a = "qwewq";
//        String[] split = a.split(" ");
//        System.out.println(split[0]);

    }

    /**
     * 本地文件解压
     *
     * @param file 具体文件
     * @return 解压后的文件列表
     */
    public static void unzip(File file) {

        ZipFile zip = null;
        try {
            zip = new ZipFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            StringBuilder sb = new StringBuilder();
            try (InputStream in = zip.getInputStream(entry);
                 InputStreamReader inputStreamReader = new InputStreamReader(in);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(zip)) {
            try {
                zip.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static List<File>  unzipInputStream(InputStream inputStream) {

        List<File> fileList = new ArrayList<>();
        try (ZipInputStream zip = new ZipInputStream(inputStream, Charset.forName("UTF-8"))) {
            ZipEntry zipEntry = null;
            BufferedInputStream bs = new BufferedInputStream(zip);
            byte[] bytes = null;
            while ((zipEntry = zip.getNextEntry()) != null) {
                System.out.println("==============");
                String fileName_zip = zipEntry.getName();
                System.out.println(fileName_zip);
                String decode = URLEncoder.encode(fileName_zip, "UTF-8");
                File file = new File(decode);
                System.out.println(decode);
//                FileReader fileReader = new FileReader(decode);

                if (fileName_zip.endsWith("/")) {
                    file.mkdir();
                    continue;
                } else {

                    String name = Charset.defaultCharset().name();
                    System.out.println(name);

                    bytes = new byte[(int) zipEntry.getSize()];
                    bs.read(bytes, 0, (int) zipEntry.getSize());
                    InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(byteArrayInputStream));



//                    FileInputStream fis = new FileInputStream(file);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//                    BufferedReader br = new BufferedReader(fileReader);

//                    BufferedReader reader = new BufferedReader(new InputStreamReader(zip.getInputStream()));

//                    InputStream entryInputStream = new ZipInputStream(inputStream);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(zip));

                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println("------------------------");
                        System.out.println(line);
                    }
                    br.close();
//                    fis.close();


//                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
//                    byte[] byte_s = new byte[1024];
//                    int num;
//                    while ((num = zip.read(byte_s, 0, byte_s.length)) > 0) {
//                        outputStream.write(byte_s, 0, num);
//                        System.out.println(new String(byte_s).trim());
//                        System.out.println("===============");
//                    }
//                    outputStream.close();


                }
                fileList.add(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public static List<File>  unzipInputStream1(InputStream inputStream) {

        List<File> fileList = new ArrayList<>();
        try (ZipInputStream zip = new ZipInputStream(inputStream, Charset.forName("UTF-8"))) {
            ZipEntry zipEntry = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(zip));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("------------------------");
                System.out.println(line);
            }
            br.close();

//            while ((zipEntry = zip.getNextEntry()) != null) {
//                System.out.println("==============");
//                String fileName_zip = zipEntry.getName();
//                System.out.println(fileName_zip);
//                String decode = URLEncoder.encode(fileName_zip, "UTF-8");
//                File file = new File(decode);
//                System.out.println(decode);
////                FileReader fileReader = new FileReader(decode);
//
//                InputStream entryInputStream = zip;
//                if (fileName_zip.endsWith("/")) {
//                    file.mkdir();
//                    continue;
//                } else {
//
//                    String name = Charset.defaultCharset().name();
//                    System.out.println(name);
//
////                    FileInputStream fis = new FileInputStream(file);
////                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
////                    BufferedReader br = new BufferedReader(fileReader);
//
////                    BufferedReader reader = new BufferedReader(new InputStreamReader(zip.getInputStream()));
//
//
//
////                    fis.close();
//
//
////                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
////                    byte[] byte_s = new byte[1024];
////                    int num;
////                    while ((num = zip.read(byte_s, 0, byte_s.length)) > 0) {
////                        outputStream.write(byte_s, 0, num);
////                        System.out.println(new String(byte_s).trim());
////                        System.out.println("===============");
////                    }
////                    outputStream.close();
//
//
//                }
//                fileList.add(file);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }


    public static InputStream download(String path) {
        try {
            URL url = new URL(path);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(20 * 1000);
            InputStream inputStream = con.getInputStream();

            return inputStream;
//
//
//            // path是指欲下载的文件的路径。
//            File file = new File(path);
//            // 取得文件名。
//            String filename = file.getName();
//            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//
//            // 以流的形式下载文件。
//            InputStream fis = new BufferedInputStream(new FileInputStream(path));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//
//            return fis;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void test () throws IOException {
        File file = new File("C:\\Users\\track\\Desktop\\test2_2.txt");
        FileInputStream fis = new FileInputStream("C:\\Users\\track\\Desktop\\test2_2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println("------------------------");
            System.out.println(line);
        }
        br.close();
        fis.close();
    }




    public static String asMinuteString(LocalDateTime time) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(fmt);
    }


    public static boolean GreaterThanOneHour(Date startDate, Date endDate) {


        // 将Date对象转换为Instant对象
        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant();

        // 计算时间差
        Duration duration = Duration.between(startInstant, endInstant);

        // 将时间差转换为秒
        long seconds = duration.getSeconds();
        if (3600 < seconds) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }


    public static Date parseDate(String dateStr, String dateFormat) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
            Date date = sd.parse(dateStr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

}
