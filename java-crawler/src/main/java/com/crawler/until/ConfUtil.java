package com.crawler.until;

import com.crawler.book.domain.Crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfUtil {

    public static String readConfigFile(String cfgFile) {
        String readStr = null;
        try {
            // 这里就以读取文件流为例,这种就会读取resources下的conf.yml文件(不能在前面加/)
            InputStream in = YamlConf.class.getClassLoader().getResourceAsStream("application.yml");
            Properties prop = new Properties();
            prop.load(new InputStreamReader(in, "UTF-8"));
            readStr = prop.getProperty(cfgFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return readStr;
    }

    public static String readConfigByClass(Class c) {

        ConfigPrefix annotation = (ConfigPrefix) c.getAnnotation(ConfigPrefix.class);
        if(annotation == null){
            throw new RuntimeException("Not Found ConfigPrefix Annotation");
        }
        String value = annotation.value();
        System.out.println(value);  // 前缀


        String readStr = null;
        try {
            // 这里就以读取文件流为例,这种就会读取resources下的conf.yml文件(不能在前面加/)
            InputStream in = YamlConf.class.getClassLoader().getResourceAsStream("application.yml");
            Properties prop = new Properties();
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");

            String line;
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((line = br.readLine()) != null) {

                String[] split = line.split(":");
                // 找到这个位置
                if(split[0].equals(value)){

                }

                sb.append(line);
            }
            String str = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return readStr;
    }

    public static void main(String[] args) {
        ConfUtil.readConfigByClass(Crawler.class);
    }

}
