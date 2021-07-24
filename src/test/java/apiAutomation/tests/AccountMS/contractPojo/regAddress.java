package test.java.apiAutomation.tests.AccountMS.contractPojo;

import com.github.javafaker.Faker;

public class regAddress

{

    private String address_line_1;
    private String address_line_2;
    private String address_line_3;
    private String address_line_4;
    private String building_id;
    private String building_name;
    private String district;

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getAddress_line_3() {
        return address_line_3;
    }

    public void setAddress_line_3(String address_line_3) {
        this.address_line_3 = address_line_3;
    }

    public String getAddress_line_4() {
        return address_line_4;
    }

    public void setAddress_line_4(String address_line_4) {
        this.address_line_4 = address_line_4;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUnit_number() {
        return unit_number;
    }

    public void setUnit_number(String unit_number) {
        this.unit_number = unit_number;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    private String floor;
    private String street;
    private String unit_number;
    private String unit_type;

    public regAddress() {
        Faker generateData=new Faker();
        this.address_line_1 = generateData.address().country();
        this.address_line_2 = generateData.address().city();
        this.address_line_3 = generateData.address().streetAddress();
        this.address_line_4 = generateData.address().cityPrefix();
        this.building_id = generateData.address().buildingNumber();
        this.building_name =generateData.address().secondaryAddress();
        this.district = generateData.address().state();
        this.floor = "3";
        this.street = generateData.address().cityPrefix();
        this.unit_number = "234522";
        this.unit_type = "FLAT";
    }
}
