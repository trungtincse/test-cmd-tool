/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tindpt
 */
public class Feature {

    @Id
    public String slug_name;
    public String name;
    public String description;
    public List<String> commands;

    public Feature(String name, String description) {
        this.name = name;
        this.slug_name = toSlug();
        this.description = description;
        this.commands = new ArrayList();
    }

    public Feature() {
    }

    public final String toSlug() {
        if (name == null) {
            return null;
        }
        final Pattern NONLATIN = Pattern.compile("[^\\w-]");
        final Pattern WHITESPACE = Pattern.compile("[\\s]");
        String nowhitespace = WHITESPACE.matcher(name).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public String toString() {
        return slug_name;
    }

//    public static void main(String[] args) throws JsonProcessingException, IOException {
//        Feature fea = new Feature("as", "as");
//        Command cmd = new Command("1", "2");
//        SubCmd subcmd = new SubCmd("1", "2");
//        ArrayList<String> value = new ArrayList<>();
//        value.add("aasas");
//        subcmd.params.add(new Param("1", "byte", value));
//        cmd.subcmds.add(subcmd);
//        cmd.subcmds.add(new SubCmd("3", "Deptrai"));
//        fea.commands.add(cmd);
//        fea.commands.add(new Command("2", "2"));
//        ObjectMapper a = new ObjectMapper();
//        System.out.println(a.writeValueAsString(fea));
////        String str = "{\"name\":\"as\",\"slug_name\":\"as\",\"description\":\"as\",\"commands\":[{\"number\":\"1\",\"description\":\"2\",\"subcmds\":[{\"subcmd\":\"1\",\"description\":\"2\"},{\"subcmd\":\"3\",\"description\":\"Deptrai\"}]},{\"number\":\"2\",\"description\":\"2\",\"subcmds\":[]}]}";
//String str="{\"name\":\"as\",\"slug_name\":\"as\",\"description\":\"as\",\"commands\":[{\"number\":\"1\",\"description\":\"2\",\"subcmds\":[{\"subcmd\":\"1\",\"description\":\"2\",\"params\":[{\"description\":\"1\",\"type\":\"byte\",\"value\":[\"aasas\"]}]},{\"subcmd\":\"3\",\"description\":\"Deptrai\",\"params\":[]}]},{\"number\":\"2\",\"description\":\"2\",\"subcmds\":[]}]}";
//        Feature fea1 = a.readValue(str, Feature.class);
//        System.out.println(a.writeValueAsString(fea1));
////        DataStorage.Instance.feaRepo.insert(fea);
////        DataStorage.Instance.db.commit();
////        Cursor<Feature> cursor = DataStorage.Instance.feaRepo.find();
////        for (Feature feature : cursor) {
////            feature.toString();
////        }
//    }
}
