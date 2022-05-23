import java.io.IOException;

public class Person implements ClassInterface {
    private String fullName;
    private Integer age;
    private String address;
    private String phoneNumber;
    private String email;

    Person(String fullName, Integer age, String address, String phoneNumber, String email) {
        setAddress(address);
        setAge(age);
        setEmail(email);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
    }

    public Person(String... params) {
        System.out.println(params);
    }

    public void printPersonInfo(){
        System.out.println(this.toString());
    }

    public static void printHello(){
        System.out.println("Hello");
    }

    @Override
    public void execute(String... params) throws IOException {
        Person person = new Person(params);
        //person.printPersonInfo();
    }

    @Override
    public String toString() {
        return "Person\nfull name: " + fullName + ", age: " + age + ", address: " + address
                + ", phone: " + phoneNumber + ", email: " + email;
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
