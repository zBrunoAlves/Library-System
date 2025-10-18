package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private LocalDate releaseDate;
	private Double price;
	private Integer quantity;
	private Integer id;

	public Book() {

	}

	public Book(String title, String author, LocalDate releaseDate, Double price, Integer quantity, Integer id) {
		super();
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.price = price;
		this.quantity = quantity;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, price, releaseDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(price, other.price)
				&& Objects.equals(releaseDate, other.releaseDate) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", releaseDate=" + releaseDate + ", price=" + price
				+ ", quantity=" + quantity + ", id=" + id + "]";
	}

}
