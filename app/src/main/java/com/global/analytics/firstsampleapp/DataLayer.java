package com.global.analytics.firstsampleapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by senthilraj on 17-10-2015.
 */

public class DataLayer {

    public String email_id = null;
    public String passwd = null;

    public String amount = null;

    public String salutation = null;
    public String first_name = null;
    public String last_name = null;
    public String marital_status = null;
    public String dob_day = null;
    public String dob_month = null;
    public String dob_year = null;
    public String mobile_no = null;

    public String post_code = null;
    public String house_no = null;
    public String street = null;
    public String post_town = null;
    public String county = null;
    public String time_at_address = null;
    public String type_of_occupancy = null;

    public String employer_name = null;
    public String employer_phone = null;
    public String income_source = null;
    public String employment_lenght = null;
    public String income_amount = null;
    public String income_per = null;
    public String income_frequency = null;

    public String expense_rent = null;
    public String expense_loans = null;
    public String expense_transport = null;
    public String expense_food = null;
    public String expense_others = null;
    public String CreditPayments = null;

    public String bank_sortcode = null;
    public String bank_account_no = null;
    public String card_type = null;

    public String card_no = null;
    public String card_cvv = null;
    public String card_exp_month = null;
    public String card_exp_year = null;

    public boolean pullSuccess = false;
    public String ReqLoanAmt = null;
    public int salutationindex,marital_statusindex,dob_dayindex,dob_monthindex,dob_yearindex;

    public DataLayer(JSONObject jsonObject){
        try {
            JSONObject ExtPostTransaction = jsonObject.getJSONObject("ExtPostTransaction");
            JSONObject TransactionData = ExtPostTransaction.getJSONObject("TransactionData");
            JSONObject CustomerInfo = TransactionData.getJSONObject("CustomerInfo");
            JSONObject BankInfo = TransactionData.getJSONObject("BankInfo");
            JSONObject EmployerInfo = TransactionData.getJSONObject("EmployerInfo");
            JSONObject LoanInfo = TransactionData.getJSONObject("LoanInfo");
            email_id = CustomerInfo.getString("CustEmail");
            pullSuccess = true;
            salutation = CustomerInfo.getString("Title");
            salutationindex = 1;
            first_name = CustomerInfo.getString("CustFName");
            last_name = CustomerInfo.getString("CustLName");
            marital_status = CustomerInfo.getString("MaritalStatus");
            marital_statusindex = 1;
            dob_day = CustomerInfo.getString("CustDOB");
            dob_month = CustomerInfo.getString("CustDOB");
            dob_year = CustomerInfo.getString("CustDOB");
            dob_dayindex = 10;
            dob_monthindex = 6;
            dob_yearindex = 20;
            mobile_no = CustomerInfo.getString("CustMobilePhone");

            post_code = CustomerInfo.getString("Postcode");
            house_no = CustomerInfo.getString("HomeNumber");
            street = CustomerInfo.getString("Street");
            county = CustomerInfo.getString("County");
            type_of_occupancy = CustomerInfo.getString("HomeStatus");

            employer_name = EmployerInfo.getString("EmpName");
            income_source = EmployerInfo.getString("TypeOfIncome");
            income_amount = EmployerInfo.getString("MonthlyIncome");
            income_per = EmployerInfo.getString("TypeOfIndustry");
            income_frequency = EmployerInfo.getString("PayFrequency");

            expense_loans = LoanInfo.getString("Mortgage");
            expense_transport = LoanInfo.getString("TransportCosts");
            expense_food = LoanInfo.getString("FoodCosts");
            expense_others = LoanInfo.getString("OtherPayments");
            CreditPayments = LoanInfo.getString("CreditPayments");
            ReqLoanAmt = LoanInfo.getString("ReqLoanAmt");

            bank_sortcode = BankInfo.getString("BankSortCode");
            bank_account_no = BankInfo.getString("AcctNumber");
            card_type = CustomerInfo.getString("DebitCardType");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        catch (Exception e){e.printStackTrace();}
    }
    public FormFieldValidation oFormFieldValidation = new FormFieldValidation();

    public String getEmail_id() {
        return email_id;
    }


    public boolean setEmail_id(String email_id) {
        if (!oFormFieldValidation.isValidEmail(email_id))
        {
            return false;
        }
        this.email_id = email_id;
        return true;
    }

    public String getPasswd() {
        return passwd;
    }

    public boolean setPasswd(String passwd) {
        this.passwd = passwd;
        return true;
    }

    public String getAmount() {
        return amount;
    }

    public boolean setAmount(String amount) {
        if (!oFormFieldValidation.isMoney(amount))
        {
            return false;
        }
        this.amount = amount;
        return true;
    }

    public String getSalutation() {
        return salutation;
    }

    public boolean setSalutation(String salutation) {
        this.salutation = salutation;
        return true;
    }

    public String getFirst_name() {
        return first_name;
    }

    public boolean setFirst_name(String first_name) {
        if (!oFormFieldValidation.isAlphaNumeric(first_name))
        {
            return false;
        }
        this.first_name = first_name;
        return true;
    }

    public String getLast_name() {
        return last_name;
    }

    public boolean setLast_name(String last_name) {
        if (!oFormFieldValidation.isAlphaNumeric(last_name))
        {
            return false;
        }
        this.last_name = last_name;
        return true;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public boolean setMarital_status(String marital_status) {
        this.marital_status = marital_status;
        return true;
    }

    public String getDob_day() {
        return dob_day;
    }

    public boolean setDob_day(String dob_day) {
        this.dob_day = dob_day;
        return true;
    }

    public String getDob_month() {
        return dob_month;
    }

    public boolean setDob_month(String dob_month) {
        this.dob_month = dob_month;
        return true;
    }

    public String getDob_year() {
        return dob_year;
    }

    public boolean setDob_year(String dob_year) {
        this.dob_year = dob_year;
        return true;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public boolean setMobile_no(String mobile_no) {
        if (!oFormFieldValidation.isMobileNumber(mobile_no))
        {
            return false;
        }
        this.mobile_no = mobile_no;
        return true;
    }

    public String getPost_code() {
        return post_code;
    }

    public boolean setPost_code(String post_code) {
        if (!oFormFieldValidation.isAlphaNumeric(post_code))
        {
            return false;
        }
        this.post_code = post_code;
        return true;
    }

    public String getHouse_no() {
        return house_no;
    }

    public boolean setHouse_no(String house_no) {
        if (!oFormFieldValidation.isAlphaNumeric(house_no))
        {
            return false;
        }
        this.house_no = house_no;
        return true;
    }

    public String getStreet() {
        return street;
    }

    public boolean setStreet(String street) {
        if (!oFormFieldValidation.isAlphaNumeric(street))
        {
            return false;
        }
        this.street = street;
        return true;
    }

    public String getPost_town() {
        return post_town;
    }

    public boolean setPost_town(String post_town) {
        if (!oFormFieldValidation.isAlphaNumeric(post_town))
        {
            return false;
        }
        this.post_town = post_town;
        return true;
    }

    public String getCounty() {
        return county;
    }

    public boolean setCounty(String county) {
        this.county = county;
        return true;
    }

    public String getTime_at_address() {
        return time_at_address;
    }

    public boolean setTime_at_address(String time_at_address) {
        this.time_at_address = time_at_address;
        return true;
    }

    public String getType_of_occupancy() {
        return type_of_occupancy;
    }

    public boolean setType_of_occupancy(String type_of_occupancy) {
        this.type_of_occupancy = type_of_occupancy;
        return true;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public boolean setEmployer_name(String employer_name) {
        if (!oFormFieldValidation.isAlphaNumeric(employer_name))
        {
            return false;
        }
        this.employer_name = employer_name;
        return true;
    }

    public String getEmployer_phone() {
        return employer_phone;
    }

    public boolean setEmployer_phone(String employer_phone) {
        if (!oFormFieldValidation.isMobileNumber(employer_phone))
        {
            return false;
        }
        this.employer_phone = employer_phone;
        return true;
    }

    public String getIncome_source() {
        return income_source;
    }

    public boolean setIncome_source(String income_source) {
        this.income_source = income_source;
        return true;
    }

    public String getEmployment_lenght() {
        return employment_lenght;
    }

    public boolean setEmployment_lenght(String employment_lenght) {
        this.employment_lenght = employment_lenght;
        return true;
    }

    public String getIncome_amount() {
        return income_amount;
    }

    public boolean setIncome_amount(String income_amount){
        if (!oFormFieldValidation.isMoney(income_amount))
        {
            return false;
        }
        this.income_amount = income_amount;
        return true;
    }

    public String getIncome_per() {
        return income_per;
    }

    public boolean setIncome_per(String income_per) {
        this.income_per = income_per;
        return true;
    }

    public String getIncome_frequency() {
        return income_frequency;
    }

    public boolean setIncome_frequency(String income_frequency) {
        this.income_frequency = income_frequency;
        return true;
    }

    public String getExpense_rent() {
        return expense_rent;
    }

    public boolean setExpense_rent(String expense_rent) {
        this.expense_rent = expense_rent;
        return true;
    }

    public String getExpense_loans() {
        return expense_loans;
    }

    public boolean setExpense_loans(String expense_loans) {
        this.expense_loans = expense_loans;
        return true;
    }

    public String getExpense_transport() {
        return expense_transport;
    }

    public boolean setExpense_transport(String expense_transport) {
        this.expense_transport = expense_transport;
        return true;
    }

    public String getExpense_food() {
        return expense_food;
    }

    public boolean setExpense_food(String expense_food) {
        this.expense_food = expense_food;
        return true;
    }

    public String getExpense_others() {
        return expense_others;
    }

    public boolean setExpense_others(String expense_others) {
        this.expense_others = expense_others;
        return true;
    }

    public String getBank_sortcode() {
        return bank_sortcode;
    }

    public boolean setBank_sortcode(String bank_sortcode) {
        if (!oFormFieldValidation.isNumber(bank_sortcode))
        {
            return false;
        }
        this.bank_sortcode = bank_sortcode;
        return true;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    public boolean setBank_account_no(String bank_account_no) {
        if (!oFormFieldValidation.isNumber(bank_account_no))
        {
            return false;
        }
        this.bank_account_no = bank_account_no;
        return true;
    }

    public String getCard_type() {
        return card_type;
    }

    public boolean setCard_type(String card_type) {
        this.card_type = card_type;
        return true;
    }

    public String getCard_no() {
        return card_no;
    }

    public boolean setCard_no(String card_no) {
        if (!oFormFieldValidation.isNumber(card_no))
        {
            return false;
        }
        this.card_no = card_no;
        return true;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public boolean setCard_cvv(String card_cvv) {
        if (!oFormFieldValidation.isNumber(card_cvv))
        {
            return false;
        }
        this.card_cvv = card_cvv;
        return true;
    }

    public String getCard_exp_month() {
        return card_exp_month;
    }

    public boolean setCard_exp_month(String card_exp_month) {
        this.card_exp_month = card_exp_month;
        return true;
    }

    public String getCard_exp_year() {
        return card_exp_year;
    }

    public boolean setCard_exp_year(String card_exp_year) {
        this.card_exp_year = card_exp_year;
        return true;
    }

}
