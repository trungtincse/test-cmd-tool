/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

/**
 *
 * @author tindpt
 */
public class ServerInstance {
    private BackendLevel owner;
    public String name;

    public ServerInstance(BackendLevel owner, String name) {
        this.owner = owner;
        this.name = name;
    }
    @Override
    public String toString(){
        return owner+name;
    }
}
