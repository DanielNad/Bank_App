package Model;

public abstract class Person {


    private String last_name;
    private String first_name;
    private String address;

    public Person(String last_name, String first_name, String address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.address = address;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getAddress() {
        return address;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
