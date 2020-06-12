/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;
import com.vng.zing.testfeature.common.Utils;
import com.vng.zing.testfeature.data.database.DataStorage;
import org.dizitart.no2.objects.Id;
/**
 *
 * @author tindang
 */
public class WhiteList {
    @Id
    public String uid;

    public WhiteList() {
    }

    public WhiteList(String uid) {
        this.uid = uid;
    }
       public static void main(String[] args) {
           System.out.println(DataStorage.Instance.wlRepo.find().firstOrDefault().uid);
    }
}
