/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.vng.zing.testfeature.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tindpt
 */
public class FeatureRequestModel {
    
    public static FeatureRequestModel Instance=new FeatureRequestModel();
    private FeatureRequestModel() {
    }
    public void process(HttpServletRequest req, HttpServletResponse resp){
        try {
            BufferedReader reader = req.getReader();
//            Gson gson = new Gson();
//            RequestObj requestObj = gson.fromJson(reader, RequestObj.class);
        } catch (IOException ex) {
            Logger.getLogger(FeatureRequestModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
