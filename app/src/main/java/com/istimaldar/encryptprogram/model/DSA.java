package com.istimaldar.encryptprogram.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.R.attr.key;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public class DSA implements SymmetricEncryptable {
    @Override
    public SecretKey generateKeys() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance("DES").generateKey();
    }

    @Override
    public void saveKey(SecretKey key, String path) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(key);
    }

    @Override
    public SecretKey loadKey(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return  (SecretKey) ois.readObject();
    }

    @Override
    public byte[] encrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        return ecipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data, SecretKey key)  throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.DECRYPT_MODE, key);
        return ecipher.doFinal(data);
    }

    public byte[] encrypt(byte[] data) {
        try {
            SecretKey key = generateKeys();
            saveKey(key, "DSAkey");
            return encrypt(data, key);
        }
        catch (Throwable e) {
            return new byte[0];
        }
    }
    public byte[] decrypt(byte[] data) {
        try {
            SecretKey key = loadKey("DSAkey");
            return decrypt(data, key);
        }
        catch (Throwable e) {
            return new byte[0];
        }
    }
}
