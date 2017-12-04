package nz.co.price.alert.company;

public class CreateCompanyMessage {
    private String name;

    public CreateCompanyMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
