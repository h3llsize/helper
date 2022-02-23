package org.helper.weka;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WekaStorage {
    public static WekaCSGO csgoMultischeme;

    public static void Initialize()
    {
        try {
            ObjectInputStream inS = new ObjectInputStream(new FileInputStream("src/main/ai/csgo.ai"));
            csgoMultischeme = (WekaCSGO) inS.readObject();

            inS.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String getPrediction(String line)
    {
        try {
            int bay =  Integer.parseInt(csgoMultischeme.getPrediction(line).replace(".0",""));

            return bay + "";

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
