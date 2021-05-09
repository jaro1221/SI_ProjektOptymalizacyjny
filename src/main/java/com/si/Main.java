package com.si;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

public class Main {

    public static ArrayList<ArrayList<String>> HM;
    public static ArrayList<ArrayList<Double>> FP;
    private static final int HS = 5;

    //private static final int carId = 5;
    private static double maxCarMass = 2340.0;   // kg
    private static double maxCarCube = 370.5 * 187 * 193.2;   // cm^3  (13 385 572.2 cm^3 == 13.3855722 m^3)

    public static JSONArray products;

    public static void main(String[] args) {
        HM = new ArrayList<>();
        FP = new ArrayList<>();
        loadFiles();

        // MAIN LOOP
        for (int i = 0; i < HS; i++) {

            ArrayList<String> vectHM = new ArrayList<>();
            double currCube = 0.0;
            double currMass = 0.0;

            //get random product
            while (true) {
                int rnd = new Random().nextInt(products.size());
                JSONObject prod = (JSONObject) products.get(rnd);
                String prodId = (String) prod.get("id");

                // avoid duplicates
                if (!vectHM.contains(prodId)) {

                    boolean isValid = true;

                    // get cube & mass
                    double prodCube = (double) prod.get("dl") * (double) prod.get("szer") * (double) prod.get("wys");
                    double prodMass = (double) prod.get("masa");

                    // check cube & mass
                    if ((currCube + prodCube) > maxCarCube) isValid = false;
                    if ((currMass + prodMass) > maxCarMass) isValid = false;

                    if (isValid)
                    {
                        currCube = currCube + prodCube;
                        currMass = currMass + prodMass;
                        vectHM.add(prodId);
                    } else break;
                }
            }

            HM.add(vectHM);
            ArrayList<Double> vectFP = new ArrayList<>();
            vectFP.add(currCube);
            vectFP.add(currMass);
            FP.add(vectFP);
        }
        printMatrix();
    }

    private static void printMatrix() {
        System.out.println("HM = ");
        for (int i = 0; i < HS; i++) {
            System.out.println(HM.get(i));
        }

        System.out.println("\nFP = ");
        for (int i = 0; i < HS; i++) {
            System.out.println(FP.get(i));
        }
    }

    public static void loadFiles() {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader("files/produkty.json"));
            //System.out.println(obj);
            JSONObject jsonObj = (JSONObject) obj;
            products = (JSONArray) jsonObj.get("productsList");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
