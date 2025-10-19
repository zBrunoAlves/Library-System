package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDate saleDate;
    private Integer quantity;
    private Double totalPrice;

    private Customer customer;
    private Book book;

    public Sale() {
    }

    public Sale(Integer id, LocalDate saleDate, Integer quantity, Double totalPrice, Customer customer, Book book) {
        this.id = id;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.book = book;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

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
        return "Sale [id=" + id +
               ", saleDate=" + saleDate +
               ", quantity=" + quantity +
               ", totalPrice=" + totalPrice +
               ", customer=" + (customer != null ? customer.getName() : "N/A") +
               ", book=" + (book != null ? book.getTitle() : "N/A") + "]";
    }
}
