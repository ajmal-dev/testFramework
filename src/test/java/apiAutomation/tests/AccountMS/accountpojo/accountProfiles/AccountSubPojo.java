package test.java.apiAutomation.tests.AccountMS.accountpojo.accountProfiles;

import java.util.ArrayList;
import java.util.List;

public class AccountSubPojo

{
    private String id;
    private String category;
    private String status;
    private ArrayList<ContractsPojo> contracts;

    AccountSubPojo(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContractsPojo> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<ContractsPojo> contracts) {
        this.contracts = contracts;
    }
}
