package test.java.apiAutomation.tests.AccountMS.contractPojo;

import com.github.javafaker.Faker;

public class CreateContractPayload

{

        private String account_number;
    private String  approval_code;
    private String     bill_language_code;
    private String     bill_redirect;
    private String     company_code;
    private String     contact_person_name;
    private String      contact_tel_number;
    private String       create_date;
    private String   credit_card_expiry;
    private String     credit_card_number;
    private String      currency_code;
    private String     customer_id;
    private String     customer_id_type;
    private String      customer_name;
    private String      customer_ref_id;
    private String     e_bill;
    private String      email;
    private String      fax_number;
    private String      hardcopy_bill;
    private String    home_tel_number;
    private String     industry_class;
    private String    language_code;
    private String   paper_bill_charge;
    private String    paper_bill_charge_waive_count;
    private String     payment_method;
    private String    postman_beat;
    regAddress  registerd_address;
    private String   sms_mobile_number;
    private String   tender_code;
    private String  title;
    private String    undel_ind;


    public CreateContractPayload(regAddress registeredaddress,String account_number) {
        Faker generateData=new Faker();
        this.account_number = account_number;
        this.approval_code =generateData.number().toString();
        this.bill_language_code = generateData.number().toString();
        this.bill_redirect = generateData.number().toString();
        this.company_code = generateData.number().toString();
        this.contact_person_name =generateData.code().isbn10();
        this.contact_tel_number = generateData.phoneNumber().phoneNumber();
        this.create_date = "2020-05-28";
        this.credit_card_expiry = "2024-05-28";
        this.credit_card_number = generateData.number().digits(15);
        this.currency_code = "USD";
        this.customer_id = generateData.idNumber().valid();
        this.customer_id_type = "I";
        this.customer_name = generateData.name().fullName();
        this.customer_ref_id = generateData.idNumber().valid();
        this.e_bill = generateData.idNumber().valid();
        this.email = generateData.name()+"@"+"hmail.com";
        this.fax_number =generateData.phoneNumber().phoneNumber();
        this.hardcopy_bill = generateData.code().isbn10();
        this.home_tel_number =generateData.phoneNumber().phoneNumber();
        this.industry_class = "standard";
        this.language_code = "en-us";
        this.paper_bill_charge ="3030";
        this.paper_bill_charge_waive_count ="20";
        this.payment_method = "credit Card";
        this.postman_beat = "erer";
        this.registerd_address = registeredaddress;
        this.sms_mobile_number = generateData.phoneNumber().phoneNumber();
        this.tender_code = generateData.code().isbn10();
        this.title = generateData.job().title();
        this.undel_ind = "U1";
    }
}


