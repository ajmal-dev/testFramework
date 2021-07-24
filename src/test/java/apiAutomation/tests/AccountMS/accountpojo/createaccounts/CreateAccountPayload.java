package test.java.apiAutomation.tests.AccountMS.accountpojo.createaccounts;

import com.github.javafaker.Faker;

public class CreateAccountPayload
{
    private String account_category;
    private String account_grp_number;
    private String agreement_number;
    private String avg_monthly_IDD;
    private String bill_cycle;
    private String business_code;
    private String contact_person_name;
    private String contact_tel_number;
    private String create_date;
    private String customer_id;
    private String customer_id_type;
    private String customer_name;
    private String employee_count;
    private String fax_number;
    private String home_tel_number;
    private String invoice_scenario;
    private String occupation;
    private String other_service;
    private String payment_terms;
    AddressReg registered_address;
    private String salesman_code;
    private String soft_diskette_bill;
    private String source;
    private String title;
    private String waive_invoice_charge;



    public CreateAccountPayload(AddressReg addressobject) {
        Faker generateData=new Faker();
        this.account_category = "CR";
        this.account_grp_number = generateData.idNumber().valid();
        this.agreement_number =generateData.number().toString();
        this.avg_monthly_IDD =generateData.number().toString() ;
        this.bill_cycle = generateData.number().toString();
        this.business_code = "BBS";
        this.contact_person_name = generateData.name().fullName();
        this.contact_tel_number =generateData.phoneNumber().phoneNumber();
        this.create_date = "2020-05-28";
        this.customer_id = generateData.idNumber().valid();
        this.customer_id_type = "I";
        this.customer_name = generateData.name().fullName();
        this.employee_count = "4";
        this.fax_number = generateData.phoneNumber().phoneNumber();
        this.home_tel_number =generateData.phoneNumber().phoneNumber();
        this.invoice_scenario = "1";
        this.occupation = generateData.job().title();
        this.other_service ="Y";
        this.payment_terms ="30DAYS";
        this.registered_address = addressobject;
        this.salesman_code =generateData.code().isbnGroup();
        this.soft_diskette_bill =generateData.code().isbnRegistrant();
        this.source = "DA";
        this.title = generateData.job().title();
        this.waive_invoice_charge = "Y";
    }

    // Getters

    public String getAccount_category() {
        return account_category;
    }

    public String getAccount_grp_number() {
        return account_grp_number;
    }

    public String getAgreement_number() {
        return agreement_number;
    }

    public String getAvg_monthly_IDD() {
        return avg_monthly_IDD;
    }

    public String getBill_cycle() {
        return bill_cycle;
    }

    public String getBusiness_code() {
        return business_code;
    }

    public String getContact_person_name() {
        return contact_person_name;
    }

    public String getContact_tel_number() {
        return contact_tel_number;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_id_type() {
        return customer_id_type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getEmployee_count() {
        return employee_count;
    }

    public String getFax_number() {
        return fax_number;
    }

    public String getHome_tel_number() {
        return home_tel_number;
    }

    public String getInvoice_scenario() {
        return invoice_scenario;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getOther_service() {
        return other_service;
    }

    public String getPayment_terms() {
        return payment_terms;
    }

    public AddressReg getRegistered_address() {
        return registered_address;
    }

    public String getSalesman_code() {
        return salesman_code;
    }

    public String getSoft_diskette_bill() {
        return soft_diskette_bill;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getWaive_invoice_charge() {
        return waive_invoice_charge;
    }

    // Setters

    public void setAccount_category(String account_category) {
        this.account_category = account_category;
    }

    public void setAccount_grp_number(String account_grp_number) {
        this.account_grp_number = account_grp_number;
    }

    public void setAgreement_number(String agreement_number) {
        this.agreement_number = agreement_number;
    }

    public void setAvg_monthly_IDD(String avg_monthly_IDD) {
        this.avg_monthly_IDD = avg_monthly_IDD;
    }

    public void setBill_cycle(String bill_cycle) {
        this.bill_cycle = bill_cycle;
    }

    public void setBusiness_code(String business_code) {
        this.business_code = business_code;
    }

    public void setContact_person_name(String contact_person_name) {
        this.contact_person_name = contact_person_name;
    }

    public void setContact_tel_number(String contact_tel_number) {
        this.contact_tel_number = contact_tel_number;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_id_type(String customer_id_type) {
        this.customer_id_type = customer_id_type;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setEmployee_count(String employee_count) {
        this.employee_count = employee_count;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public void setHome_tel_number(String home_tel_number) {
        this.home_tel_number = home_tel_number;
    }

    public void setInvoice_scenario(String invoice_scenario) {
        this.invoice_scenario = invoice_scenario;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setOther_service(String other_service) {
        this.other_service = other_service;
    }

    public void setPayment_terms(String payment_terms) {
        this.payment_terms = payment_terms;
    }

    public void setRegistered_address(AddressReg regadd) {
        this.registered_address = regadd;
    }

    public void setSalesman_code(String salesman_code) {
        this.salesman_code = salesman_code;
    }

    public void setSoft_diskette_bill(String soft_diskette_bill) {
        this.soft_diskette_bill = soft_diskette_bill;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWaive_invoice_charge(String waive_invoice_charge) {
        this.waive_invoice_charge = waive_invoice_charge;
    }
}
