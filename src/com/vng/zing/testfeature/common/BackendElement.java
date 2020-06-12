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

public class BackendElement {
    public String Bname;
    public String Lname;
    public String type;
    public BackendMode mode;
    public BackendLevel[] list;
    
    public BackendElement(String Bname,String Lname, String type, BackendMode mode) {
        this.Bname = Bname;
        this.Lname = Lname;
        this.type = type;
        this.mode = mode;
        
    }
    public String toString(){
        return Lname+"_";
    }
    public void init(String[] spl){
        if (mode==BackendMode.SIMPLE) {
            list=new BackendLevel[1];
            spl=new String[]{"1"};
        }
        list= spl.length>0?new BackendLevel[spl.length]:null;
        for (int i = 0; i < list.length; i++) {
            list[i]=new BackendLevel(String.valueOf(i), Integer.valueOf(spl[i]), this);
        }
    }
}

