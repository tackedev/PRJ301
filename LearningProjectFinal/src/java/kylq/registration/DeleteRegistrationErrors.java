/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.registration;

import java.io.Serializable;

/**
 *
 * @author tackedev
 */
public class DeleteRegistrationErrors implements Serializable {
    private String deleteYourAccount;

    public DeleteRegistrationErrors() {
    }

    /**
     * @return the deleteYourAccount
     */
    public String getDeleteYourAccount() {
        return deleteYourAccount;
    }

    /**
     * @param deleteYourAccount the deleteYourAccount to set
     */
    public void setDeleteYourAccount(String deleteYourAccount) {
        this.deleteYourAccount = deleteYourAccount;
    }
}
