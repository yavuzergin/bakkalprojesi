 package org.deneme.bakkal.model;
import jakarta.persistence.*;
@Entity
@Table(name = "customerorders")
public class CustomerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    public CustomerOrders(){

    }
    public CustomerOrders(Customer customer, Product product, int quantity) {
        super();
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int entity){
        this.quantity = quantity;
    }
}

