package com.istimaldar.encryptprogram.model;


public class Model {

    private static Model instance = new Model();
    private byte[] data, proceededData;

    private Model() {

    }

    public static Model getInstance() {
        return instance;
    }

    public void loadFile() {

    }

    public void loadText(String text) {
        data = text.getBytes();
    }

    public void encrypt(Encryptable encrypter) {
        proceededData = encrypter.encrypt(data);
    }

    public void decrypt(Encryptable decrypter) {
        proceededData = decrypter.decrypt(data);
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getProceededData() {
        return proceededData;
    }

}
