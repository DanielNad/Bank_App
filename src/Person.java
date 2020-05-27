import java.io.Serializable;

public abstract class Person implements Serializable {

    private String last_name;
    private String first_name;
    private String address;
    private static final long serialVersionUID = 2224002162179063029L;

    public Person(String last_name, String first_name, String address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.address = address;
    }

    public Person() {
    }

    public String get_last_name() {
        return last_name;
    }

    public String get_first_name() {
        return first_name;
    }

    public String get_address() {
        return address;
    }

    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }

    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }

    public void set_address(String address) {
        this.address = address;
    }
}
