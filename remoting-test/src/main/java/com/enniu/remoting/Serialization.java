package com.enniu.remoting;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/*********************************
 *                               *
 Created by daipengfei on 16/11/25.
 *                               *
 ********************************/

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializationByJava();
        serializationByHessian();
        serializationByHessian2();
        serializationByKryo();
    }

    private static void serializationByKryo() throws IOException {
        Kryo kryo = new Kryo();

        kryo.register(HashMap.class);
        kryo.register(ArrayList.class);
        kryo.register(SerialClass.class);
//        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try( Output output = new Output(out)){
            SerialClass serialClass = buildSerialClass();
            kryo.writeObject(output,serialClass);
            output.flush();
            System.out.println("kryo : " + out.size());
        }
        try(Input input = new Input(out.toByteArray())) {
            SerialClass deSerialClass = kryo.readObject(input, SerialClass.class);
            System.out.println(deSerialClass.getId());
        }
    }

    private static void serializationByHessian() throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(bao);
        hessianOutput.writeObject(buildSerialClass());
        System.out.println("hessian : " + bao.size());
        hessianOutput.flush();
        hessianOutput.close();
        HessianInput in = new HessianInput(new ByteArrayInputStream(bao.toByteArray()));
        SerialClass desClass = (SerialClass)in.readObject();
        System.out.println(desClass.getId());
        in.close();
    }

    private static void serializationByHessian2() throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(bao);
        hessian2Output.writeObject(buildSerialClass());
        hessian2Output.flush();
        hessian2Output.close();
        System.out.println("hessian2 : " + bao.size());
        Hessian2Input in = new Hessian2Input(new ByteArrayInputStream(bao.toByteArray()));
        SerialClass desClass = (SerialClass)in.readObject();
        System.out.println(desClass.getId());
        in.close();
    }

    private static void serializationByJava() throws IOException, ClassNotFoundException {
        SerialClass serialClass = buildSerialClass();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(serialClass);
        System.out.println("java : " + bao.size());
        out.flush();
        out.close();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        SerialClass desClass = (SerialClass)in.readObject();
        System.out.println(desClass.getId());
    }

    private static SerialClass buildSerialClass() {
        SerialClass serialClass = new SerialClass();
        serialClass.setId(123456789L);
        serialClass.setName("daipengfei");
        Map<String,String> map = new HashMap<>();
        map.put("a","hello");
        map.put("b","hello");
        map.put("c","hello");
        map.put("d","hello");
        map.put("e","hello");
        serialClass.setFeatures(map);
        return serialClass;
    }

}
