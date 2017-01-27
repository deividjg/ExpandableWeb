package com.example.david.expandable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Datos {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> dam1 = new ArrayList<String>();
        dam1.add("Iñigo");
        dam1.add("Kike");
        dam1.add("Angel");
        dam1.add("Toni");

        List<String> dam2 = new ArrayList<String>();
        dam2.add("Patrón");
        dam2.add("Valentín");
        dam2.add("David");
        dam2.add("Natalia");
        dam2.add("Oscar");
        dam2.add("Raúl");
        dam2.add("Dani");

        List<String> daw1 = new ArrayList<String>();
        daw1.add("Jose");
        daw1.add("Antonio");
        daw1.add("Juan");
        daw1.add("Marco");
        daw1.add("Jesus");

        List<String> daw2 = new ArrayList<String>();
        daw2.add("Jose");
        daw2.add("Antonio");
        daw2.add("Juan");
        daw2.add("Marco");
        daw2.add("Jesus");


        expandableListDetail.put("1º DAM", dam1);
        expandableListDetail.put("2º DAM", dam2);
        expandableListDetail.put("1º DAW", daw1);
        expandableListDetail.put("2º DAW", daw2);
        return expandableListDetail;
    }
}
