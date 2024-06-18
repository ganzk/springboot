package com.crawler.until;

import cn.hutool.core.net.NetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpMacUtil {

    /**
     * 从windows机器上获取mac地址
     *
     * @param ip ipv4地址
     * @return mac
     */
    public static String getMacInWindows(final String ip) {
        String result;
        String[] cmd = {"cmd", "/c", "ping " + ip};
        String[] another = {"cmd", "/c", "ipconfig -all"};
        // 获取执行命令后的result
        String cmdResult = callCmd(cmd, another);
        // 从上一步的结果中获取mac地址
        result = filterMacAddress(ip, cmdResult, "-");
        return result;
    }


    /**
     * 命令执行
     *
     * @param cmd     命令
     * @param another another
     * @return 结果
     */
    public static String callCmd(String[] cmd, String[] another) {
        StringBuilder result = new StringBuilder();
        String line;
        try {
            Runtime rt = Runtime.getRuntime();
            // 执行第一个命令
            Process proc = rt.exec(cmd);
            proc.waitFor();
            // 执行第二个命令
            proc = rt.exec(another);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("---> 执行获取mac地址命令错误：" + e.getMessage());
        }
        return result.toString();
    }


    /**
     * 获取mac地址
     *
     * @param ip           ip地址
     * @param sourceString s
     * @param macSeparator s
     * @return mac
     */
    @SuppressWarnings("all")
    public static String filterMacAddress(final String ip, final String sourceString, final String macSeparator) {
        String result = "";
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            result = matcher.group(1);
            // 因计算机多网卡问题，截取紧靠IP后的第一个mac地址
            //int num = sourceString.indexOf(ip) - sourceString.indexOf(": " + result + " ");
            //if (num > 0 && num < 300) {
            //break;
            //}
            if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
                break; //如果有多个IP,只匹配本IP对应的Mac.
            }
        }
        return result;
    }


    public static void main(String[] args) throws UnknownHostException {
        //获取本地mac地址
        String macAddress = NetUtil.getMacAddress(InetAddress.getLocalHost());
        System.out.println(macAddress);
        //获取本地ip和主机名
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("IP地址：" + addr.getHostAddress() + "，主机名：" + addr.getHostName());
    }





}
