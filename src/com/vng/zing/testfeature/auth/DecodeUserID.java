/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.auth;

import com.vng.zing.jni.ClassLoaderUtil;
import com.vng.zing.zalooauth.ZCypher;

/**
 *
 * @author hahs
 */
public class DecodeUserID {

    public static int decodeUserID(long appId, long userId) {
        ClassLoaderUtil.loadCommonLibs();
        int appId_ = ZCypher.decodeAppId(appId);
        int userId_ = ZCypher.decodeUserIdByApp(userId, appId_);
        return userId_;
    }

    public static void main(String[] args) {

        ClassLoaderUtil.loadCommonLibs();

//        long appId = Long.valueOf(args[0]);
        String appId = "2773280555401809511";
//        long userId = Long.valueOf(args[1]);
        String userId = "2434946131165497821";
//	System.out.println(appId);
//	System.out.println(userId);
        int appId_ = ZCypher.decodeAppId(Long.valueOf(appId));
        int userId_ = ZCypher.decodeUserIdByApp(Long.valueOf(userId), appId_);

        System.out.println(userId_);
        System.exit(1);
    }
}
