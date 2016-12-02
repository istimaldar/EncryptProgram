package com.istimaldar.encryptprogram.model;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public interface Encryptable {

    public  byte[] encrypt(byte[] data);
    public byte[] decrypt(byte[] data);

}
