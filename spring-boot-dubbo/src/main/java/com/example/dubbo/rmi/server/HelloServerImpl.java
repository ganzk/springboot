package com.example.dubbo.rmi.server;

import com.example.dubbo.rmi.HelloInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServerImpl extends UnicastRemoteObject implements HelloInterface{

    private static final long serialVersionUID = -271947229644133464L;

    protected HelloServerImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        System.out.println("clent:" + msg);
        return "hello";
    }
}
