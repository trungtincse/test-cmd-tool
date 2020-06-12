/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.zing.testfeature.common.Utils;
import com.vng.zing.testfeature.data.database.DataStorage;
import java.util.List;
import java.util.stream.Collectors;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tindang
 */
public class Role {

    @Id
    public String id;
    public String uid;
    public String role;
    public List<String> accessible_table;

    public static class RoleName {

        public static String ADMIN = "ADMIN";
        public static String TESTER = "TESTER";
        public static String GUEST = "GUEST";
    }

    public Role(String id, String uid, String role) {
        this.id = Utils.uuid();
        this.uid = uid;
        this.role = role;
    }

    public List<Feature> getFeaLst() {
        List<Feature> result = accessible_table.stream().map(slugName -> {
            return DataStorage.Instance.feaRepo.find(eq("slug_name", slugName)).firstOrDefault();
        }).collect(Collectors.toList());
        return result;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper a = new ObjectMapper();
        String id = "1";
        String uid = "2";
        String role = Role.RoleName.ADMIN;
        Role ins = new Role(id, uid, role);
        String str = a.writeValueAsString(ins);
        System.out.println(str);
    }
}
