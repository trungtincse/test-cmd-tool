/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.vng.zing.testfeature.common;

import hapax.TemplateDictionary;
import hapax.TemplateException;

/**
 *
 * @author tindpt
 */
    public class TemplateHelper {
    public static String renderSnippet(String path,String... args) throws TemplateException{
        TemplateDictionary dict=new TemplateDictionary();
        for (int i = 0; i < args.length; i++) {
        dict.setVariable("ARG"+i,args[i]);
        }
        return TemplateManager.applyTemplate(path, dict);
    }
    
}
