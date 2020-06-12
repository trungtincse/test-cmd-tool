/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

import com.vng.zing.configer.ZConfig;

/**
 *
 * @author tindpt
 */
public class Config {

    public static long appId = ZConfig.Instance.getLong(Config.class,"main","app_id", -1);
    public static String appUrl = ZConfig.Instance.getString(Config.class,"main","app_url", "");
}
