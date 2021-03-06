package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author joceco
 */
public class G {
    public static boolean DEBUG = false;
    public static RandomGen r = new RandomGen(getIntegerParam("seed"));
    public static int evaluations = 0;
    public static int maxEvaluations = 10000;
    public static HashMap<String, Object> params;
    public static int runid = 0;

    public static HashMap<String, Object> getParams() {
        return params;
    }

    public static void reset(int seed){
        resetR(seed);
        evaluations=0;

    }
    public static void resetR(int seed){
        r = new RandomGen(seed);
    }

    public static int getMaxEvaluations() {
        if (params.containsKey("max_epochs"))
            return getIntegerParam("max_epochs");
        return maxEvaluations;
    }

    public static String getANNType() {
        return (String) params.get("ann_type");
    }

    public static String getStringParam(String key) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return (String) params.get(key);
        }
        return "";
    }

    public static Integer getIntegerParam(String key) {
        paramsNotNull();
        try{
            if (params.containsKey(key)) {
                return ((Double) params.get(key)).intValue();
        }}catch (ClassCastException e){
            if (params.containsKey(key)) {
                return ((Integer) params.get(key)).intValue();
        }}
        return 0;
    }

    public static Integer getIntegerParam(String key,String key2) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return ((Double) getMapParam(key).get(key2)).intValue();
        }
        return 0;
    }

    public static Object getObjectParam(String key) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return (params.get(key));
        }
        return null;
    }

    public static Map<String, Object> getMapParam(String key) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return ((Map<String, Object>) params.get(key));
        }
        return null;
    }

    public static Double getDoubleParam(String key) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return (Double) params.get(key);
        }
        return 0.0;
    }

    public static Double getDoubleParam(String key,String key2) {
        paramsNotNull();
        if (params.containsKey(key)) {
            return (Double) getMapParam(key).get(key2);
        }
        return 0.0;
    }

    private static void paramsNotNull() {
        if (params == null) {
//            try {
//                URL url = G.class.getResource("../../../defaults.json");
//                File route = new File(url.getFile());
//                route = route.getParentFile()
//                        .getParentFile()
//                        .getParentFile()
//                        .getParentFile();
//                System.out.println(route.getAbsolutePath());
//                Path path = Paths.get(route.getAbsolutePath(), "defaults.json");
//                System.out.println(path);
//                System.out.println(path.toFile());
//                getDefaultsFromFile(path.toFile());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

//            TODO: Me canse de intentarlo, la ruta relativa desde python es diferente a la de Java, por mientras usare una ruta absoluta
            File file = new File("C:\\Users\\joalc\\Dropbox\\Doctorado\\Programas\\cellular-processing-neuroevolution\\defaults.json");
            try {
                getDefaultsFromFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void getDefaultsFromFile(File defaultsFile) throws FileNotFoundException {
        params = new HashMap<>();
        FileReader fr = new FileReader(defaultsFile);
        JsonReader reader = new JsonReader(fr);
        HashMap<String, Object> defaultsM = new Gson().fromJson(reader,
                new TypeToken<HashMap<String, Object>>() {
                }.getType());

        params.putAll(defaultsM);
    }

    public static boolean compare(String key, String value) {
        if (params.containsKey(key)) {
            if (params.get(key).toString().compareToIgnoreCase(value) == 0) {
                return true;
            }
        }
        return false;
    }


    public static boolean containsKey(String key) {
        return params.containsKey(key);
    }


    public static void setParam(String key, Number value) {
        params.put(key, value);
    }

    public static void incrementEpoch() {
        evaluations++;
    }
}
