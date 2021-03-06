package com.istimaldar.encryptprogram.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public interface SymmetricEncryptable extends Encryptable{

    abstract public SecretKey generateKeys() throws NoSuchAlgorithmException;
    abstract public void saveKey(SecretKey key, String path) throws FileNotFoundException,
            IOException;
    abstract public SecretKey loadKey(String path) throws FileNotFoundException, IOException,
            ClassNotFoundException;
    abstract public byte[] encrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException;
    abstract public byte[] decrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException;
    public byte[] encrypt(byte[] data);
    public byte[] decrypt(byte[] data);
}
