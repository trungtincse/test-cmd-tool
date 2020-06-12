/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.vng.zing.testfeature.data.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.zing.configer.ZConfig;
import com.vng.zing.testfeature.data.model.Command;
import com.vng.zing.testfeature.data.model.Feature;
import com.vng.zing.testfeature.data.model.Param;
import com.vng.zing.testfeature.data.model.Role;
import com.vng.zing.testfeature.data.model.SubCmd;
import com.vng.zing.testfeature.data.model.User;
import com.vng.zing.testfeature.data.model.WhiteList;
import java.util.ArrayList;
import java.util.List;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.mapper.NitriteMapper;
import org.dizitart.no2.objects.ObjectRepository;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class DataStorage {

    public Nitrite db;
    public ObjectRepository<Feature> feaRepo;
    public ObjectRepository<Role> roleRepo;
    public ObjectRepository<WhiteList> wlRepo;
    public ObjectRepository<User> userRepo;
    public ObjectRepository<Command> cmdRepo;
    public ObjectRepository<SubCmd> subCmdRepo;
    public ObjectRepository<Param> paramRepo;
    public static DataStorage Instance = new DataStorage();

    private DataStorage() {
        String dbPath = ZConfig.Instance.getString(DataStorage.class, "main", "db_path", "./data.db");
        String username = ZConfig.Instance.getString(DataStorage.class, "main", "username", "admin");
        String password = ZConfig.Instance.getString(DataStorage.class, "main", "password", "admin");
        db = Nitrite.builder()
                .compressed()
                .filePath(dbPath)
                .openOrCreate(username, password);
        feaRepo = db.getRepository(Feature.class);
        roleRepo = db.getRepository(Role.class);
        wlRepo = db.getRepository(WhiteList.class);
        userRepo = db.getRepository(User.class);
        cmdRepo = db.getRepository(Command.class);
        subCmdRepo = db.getRepository(SubCmd.class);
        paramRepo = db.getRepository(Param.class);

    }

    public void deleteParamLst(List<String> lst) {
        for (String id : lst) {
            paramRepo.remove(eq("id", id));
        }
    }

    public void deleteSubcmdLst(List<String> lst) {
        for (String id : lst) {
            subCmdRepo.find(eq("id", id)).forEach((subcmd) -> {
                deleteParamLst(subcmd.paramIds);
            });
            subCmdRepo.remove(eq("id", id));
        }
    }

    public void deleteCmdLst(List<String> numberlst) {
        for (String number : numberlst) {
            cmdRepo.find(eq("number", number)).forEach((cmd) -> {
                deleteSubcmdLst(cmd.subcmdIds);
            });
            cmdRepo.remove(eq("number", number));
        }
    }

    public void deleteCmd(String number) {
        Command cmd = cmdRepo.find(eq("number", number)).firstOrDefault();
        if (cmd != null) {
            deleteSubcmdLst(cmd.subcmdIds);
            cmdRepo.remove(eq("number", number));
        }
    }

    public void deleteFea(String slug_name) {
        Feature fea = feaRepo.find(eq("slug_name", slug_name)).firstOrDefault();
        if (fea != null) {
            deleteCmdLst(fea.commands);
            feaRepo.remove(fea);
        }
    }

    public void deleteCmdofFea(String slug_name, String cmd) {
        Feature fea = feaRepo.find(eq("slug_name", slug_name)).firstOrDefault();
        if (fea != null) {
            fea.commands.remove(cmd);
            feaRepo.update(fea);
            deleteCmd(cmd);
        }
    }

    public String getCMDJson(String cmd) throws JsonProcessingException, JSONException {
        Command cmdIns = cmdRepo.find(eq("number", cmd)).firstOrDefault();
        if (cmdIns == null) {
            return "";
        }
        JSONObject result = new JSONObject();
        JSONArray subcmds = new JSONArray();
        for (String subcmd : cmdIns.subcmdIds) {
            JSONObject subcmdJson = new JSONObject();
            SubCmd subcmdIns = subCmdRepo.find(eq("id", subcmd)).firstOrDefault();
            if (subcmdIns == null) {
                continue;
            }
            subcmdJson.put("description", subcmdIns.description);
            subcmdJson.put("subcmd", subcmdIns.subcmd);
            JSONArray params = new JSONArray();
            for (String param : subcmdIns.paramIds) {
                Param paramIns = paramRepo.find(eq("id", param)).firstOrDefault();
                if (paramIns == null) {
                    continue;
                }
                ObjectMapper objectMapper = new ObjectMapper();
                JSONObject paramJson = new JSONObject(objectMapper.writeValueAsString(paramIns));
                paramJson.put("value", createInputHtml(paramIns.values));
                params.put(paramJson);
            }
            subcmdJson.put("params", params);
            subcmds.put(subcmdJson);
        }
        result.put("subcmds", subcmds);
        return result.toString();
    }

    private String createInputHtml(List<String> values) {
        String template = "<input type=\"text\" name=\"value\" id=\"value\" class=\"form-control value\" %s value=\"%s\">";
        if (values.isEmpty()) {
            return "";
        }
        if (values.size() > 1) {
            return String.format(template, "data-role=\"tagsinput\"", values.toString().replace("[", "").replace("]", ""));
        } else {
            return String.format(template, "", values.toString().replace("[", "").replace("]", ""));
        }
    }
}
