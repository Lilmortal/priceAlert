package nz.co.price.alert.product;

public class CreateProductMessage {
    private String product;

    public CreateProductMessage(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
