package ch30_regular_expressions_and_other_packages;

import java.rmi.*;
public interface AddServerIntf extends Remote {
    double add(double d1, double d2) throws RemoteException;
}
