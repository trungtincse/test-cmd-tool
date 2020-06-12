/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

import java.util.UUID;

/**
 *
 * @author tindpt
 */
public class Utils {

    public static boolean canMap(Object[] a, Object[] b) {
        return a.length == b.length ? true : false;
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static void main(String[] args) {
        System.out.println(uuid());
    }
}
