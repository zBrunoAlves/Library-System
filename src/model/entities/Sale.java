package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private LocalDate saleDate;
    private Double totalPrice;

    private Customer customer;
    private List<SaleItem> items = new ArrayList<>();

    public Sale() {
    }

    public Sale(Integer id, LocalDate saleDate, Customer customer) {
        this.id = id;
        this.saleDate = saleDate;
        this.customer = customer;
    }

    public void setTotalPrice(Double totalPrice) {this.totalPrice = totalPrice;}
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public Double getTotalPrice() {
        double sum = 0.0;
        for (SaleItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<SaleItem> getItems() { return items; }


    public void addItem(SaleItem item) {
        items.add(item);
    }

    public void removeItem(SaleItem item) {
        items.remove(item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Sale other = (Sale) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sale [id=" + id + ", date=" + saleDate + ", customer=" + customer.getName() + "]\n");
        for (SaleItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: R$ ").append(String.format("%.2f", totalPrice));
        return sb.toString();
    }
}
