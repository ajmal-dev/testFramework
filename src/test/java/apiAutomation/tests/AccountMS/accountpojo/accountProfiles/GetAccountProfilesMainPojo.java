package test.java.apiAutomation.tests.AccountMS.accountpojo.accountProfiles;

import java.util.ArrayList;
import java.util.List;

// Getters and setters for  account controller
public class GetAccountProfilesMainPojo

{

    private int status;
    private String hash_key;
    private Boolean vip;
    private ArrayList<AccountSubPojo> accounts;
    private int match_count;

    GetAccountProfilesMainPojo(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHash_key() {
        return hash_key;
    }

    public void setHash_key(String hash_key) {
        this.hash_key = hash_key;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public List<AccountSubPojo> getAccounts() {
        return accounts;
    }

    public void setAccountsub(ArrayList<AccountSubPojo> accounts) {
        this.accounts = accounts;
    }

    public int getMatch_count() {
        return match_count;
    }

    public void setMatch_count(int match_count) {
        this.match_count = match_count;
    }






}
