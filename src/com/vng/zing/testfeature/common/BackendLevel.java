/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

public class BackendLevel {
    public String level;
    public int nos;//number of servers
    public ServerInstance[] list;
    private BackendElement owner;
    public BackendLevel(String level, int nos,BackendElement owner) {
        this.owner=owner;
        this.level = level;
        this.nos = nos;
        list=new ServerInstance[nos];
        _init();
    }
    private void _init(){
        for (int i = 0; i < nos; i++) {
            list[i]=new ServerInstance(this, "f"+String.valueOf(i+1));
        }
    }
    public String toString(){
        return owner+level+"_";
    }
}