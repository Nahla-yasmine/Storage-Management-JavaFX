package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
public class LoginModel {
    private String user_id;
    private String password;


    //--------------------------------------------------------------
    public String getUserid() {
        return this.user_id;
    }

    public void setUserid(final String username) {
        this.user_id = username;
    }
 //--------------------------------------------------------------
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
