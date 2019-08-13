/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author deca
 */
public class Search {
   @NotNull
        private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }


    

}
