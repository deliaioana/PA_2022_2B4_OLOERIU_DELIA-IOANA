package testClasses;

import java.io.IOException;

public class Person implements ClassInterface{
    private String fullName;
    private Integer age;
    private String address;
    private String phoneNumber;
    private String email;

    public void printPersonInfo(){
        System.out.println(this);
    }

    @Override
    public void execute(String... params) throws IOException {
        printPersonInfo();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
