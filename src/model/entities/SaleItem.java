package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class SaleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer quantity;
    private Double price;

    private Book book;
    private Sale sale;

    public SaleItem() {
    }

    public SaleItem(Integer quantity, Double price, Book book, Sale sale) {
        this.quantity = quantity;
        this.price = price;
        this.book = book;
        this.sale = sale;
    }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }

    public Double getSubTotal() {
        return price * quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, sale);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SaleItem other = (SaleItem) obj;
        return Objects.equals(book, other.book) && Objects.equals(sale, other.sale);
    }

    @Override
    public String toString() {
        return book.getTitle() + " | " + quantity + "x R$" + String.format("%.2f", price) + 
               " = R$" + String.format("%.2f", getSubTotal());
    }
}
