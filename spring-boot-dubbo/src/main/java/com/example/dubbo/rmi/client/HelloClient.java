package com.example.dubbo.rmi.client;

import com.example.dubbo.rmi.HelloInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class HelloClient {

    public static void main(String[] args) {

        try {
            HelloInterface lookup = (HelloInterface)Naming.lookup("rmi://127.0.0.1:9090/hello");
            String server = lookup.sayHello("server");
            System.out.println(server);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
