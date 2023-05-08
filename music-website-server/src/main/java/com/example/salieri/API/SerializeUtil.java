package com.example.salieri.API;

import java.io.*;

public class SerializeUtil {
    public static byte[] Serialize(Object object) {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Object UnSerialize(byte[] bytes){
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
