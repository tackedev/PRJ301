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
public class RegistrationInsertError implements Serializable {
    private String usernameLengErr;
    private String passwordLengthErr;
    private String confirmNoMatch;
    private String fullNameLengthErr;
    private String usernameIsExisted;

    public RegistrationInsertError() {
    }

    /**
     * @return the uernameLengErr
     */
    public String getUsernameLengErr() {
        return usernameLengErr;
    }

    /**
     * @param usernameLengErr the usernameLengErr to set
     */
    public void setUsernameLengErr(String usernameLengErr) {
        this.usernameLengErr = usernameLengErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmNoMatch
     */
    public String getConfirmNoMatch() {
        return confirmNoMatch;
    }

    /**
     * @param confirmNoMatch the confirmNoMatch to set
     */
    public void setConfirmNoMatch(String confirmNoMatch) {
        this.confirmNoMatch = confirmNoMatch;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
}
