package com.example.dubbo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {

    String sayHello(String msg) throws RemoteException;

}
