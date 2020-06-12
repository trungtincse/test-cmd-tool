/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.auth;
/**
 *
 * @author hahs
 */
public class AccessToken {

    String accessToken;
    long expireTime;

    public AccessToken() {
    }

    public AccessToken(String accessToken, long expireTime) {
	this.accessToken = accessToken;
	this.expireTime = expireTime;
    }

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

    public long getExpireTime() {
	return expireTime;
    }

    public void setExpireTime(long expireTime) {
	this.expireTime = expireTime;
    }

    @Override
    public String toString() {
	return "AccessToken{" + "accessToken=" + accessToken + ", expireTime=" + expireTime + '}';
    }

}
