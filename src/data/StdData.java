package data;

public class StdData {

    String name;
    String age;
    String ssn;
    String phone;
    String address;
    String gender;
    String year;
    String mail;
    String user;
    String pass;

    public StdData(String name, String age, String ssn, String phone, String address, String gender, String year, String mail, String user, String pass) {
        this.name = name;
        this.age = age;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.year = year;
        this.mail = mail;
        this.user = user;
        this.pass = pass;
    }

    public StdData() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getYear() {
        return year;
    }

    public String getMail() {
        return mail;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

}
