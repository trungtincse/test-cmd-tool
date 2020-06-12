/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.zing.list32bm.thrift.wrapper.test.Util;
import com.vng.zing.testfeature.common.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dizitart.no2.objects.Id;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindang
 */
public class SubCmd {

    @Id
    public String id;
    public String subcmd;
    public String description;
    public List<String> paramIds;

    public SubCmd() {
    }

    public SubCmd(String subcmd, String description) {
        this.subcmd = subcmd;
        this.description = description;
        paramIds = new ArrayList();
        id = Utils.uuid();
    }

    public void setParams(JSONArray jSONArray) throws JSONException {
        for (int n = 0; n < jSONArray.length(); n++) {
            JSONObject param = jSONArray.getJSONObject(n);
            String description = param.getString("description");
            String type = param.getString("type");
            Object value = param.get("value");
//            Param paramIns = new Param(description,type,value);
//            params.add(paramIns);
        }

    }
    public static void main(String[] args) throws JsonProcessingException, IOException {
//        SubCmd subcmd=new SubCmd("1","assaa");
//        subcmd.params.add(new Param("as","assa",new Integer[]{1,2,3,4}));
        ObjectMapper a=new ObjectMapper();
//        System.out.println(a.writeValueAsString(subcmd));
        String str = "{\"id\":\"31e66fbd-e72a-441c-bb1c-80fdacd92847\",\"subcmd\":\"1\",\"description\":\"assaa\",\"params\":[{\"description\":\"as\",\"type\":\"assa\",\"value\":[1,2,3,4]}]}";
        SubCmd subcmd1=a.readValue(str, SubCmd.class);
        System.out.println(a.writeValueAsString(subcmd1));
        
        
    }
}
