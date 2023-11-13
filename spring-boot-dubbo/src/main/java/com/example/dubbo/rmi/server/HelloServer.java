package com.example.dubbo.rmi.server;

import com.example.dubbo.rmi.HelloInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServer {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(9090);
            HelloInterface helloServer = new HelloServerImpl();
            // 需要自己去绑定注册
            Naming.bind("rmi://127.0.0.1:9090/hello",helloServer);
            System.out.println("服务端启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
