/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;
import org.dizitart.no2.objects.Id;
/**
 *
 * @author tindang
 */
public class User {
    @Id
    public int uid;
    public String name;
    public String avatar;
}
