/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.vng.zing.testfeature.data.model;

import java.util.ArrayList;
import java.util.List;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tindpt
 */
public class Command {

    @Id
    public String number;
    public String description;
    public List<String> subcmdIds;

    public Command(String number, String description) {
        this.number = number;
        this.description = description;
        this.subcmdIds = new ArrayList<>();
    }

    public Command() {
    }

}
