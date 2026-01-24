package org.example.Creational.builder;

public class Order {

    private final String product;
    private final int quantity;
    private final String address;

    private Order(String product, int quantity,String address){
        this.product=product;
        this.quantity=quantity;
        this.address=address;
    }

    public static ProductStep builder(){
        return new Builder();
    }

    public interface ProductStep{
        QuantityStep product(String product);
    }

    public interface QuantityStep{
        AddressStep quantity(int quantity);
    }

    public interface  AddressStep{
        BuildStep address(String address);
    }

    public interface  BuildStep{
        Order build();
    }

    private static class Builder implements ProductStep,QuantityStep,AddressStep,
            BuildStep{

        private String product;
        private int quantity;
        private String address;

        @Override
        public BuildStep address(String address) {
            this.address = address;
            return this;

        }

        @Override
        public QuantityStep product(String product) {
            this.product=product;
            return this;
        }

        @Override
        public AddressStep quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        @Override
        public Order build() {
            return new Order(product,quantity,address);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", address='" + address + '\'' +
                '}';
    }
}
