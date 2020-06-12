/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.zing.testfeature.common.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tindang
 */
public class Param {

    @Id
    public String id;
    public String description;
    public String type;
    public List<String> values;

    public Param(String description, String type) {
        this.description = description;
        this.type = type;
        this.values = new ArrayList<>();
        id = Utils.uuid();
    }

    public Param() {
    }

//    public static void main(String[] args) throws JsonProcessingException, IOException {
//        List<String> value = new ArrayList();
//        value.add("as");
//        value.add("as1");
//        value.add("as2");
//        Param param = new Param("aas", "bsd", value);
//        ObjectMapper a = new ObjectMapper();
//        System.out.println(a.writeValueAsString(param));
//        System.out.println(a.writeValueAsString(a.readValue("{\"id\":\"1481f836-ffdd-4748-b30a-704bc239b720\",\"description\":\"aas\",\"type\":\"bsd\",\"value\":[\"as\",\"as1\",\"as2\"]}", Param.class)));
//    }
}
