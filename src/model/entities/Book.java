package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private LocalDate releaseYear;
	private Double price;
	private Integer id;

	public Book() {

	}

	public Book(String title, String author, LocalDate releaseYear, Double price, Integer id) {
		super();
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.price = price;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDate getreleaseYear() {
		return releaseYear;
	}

	public void setreleaseYear(LocalDate releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, price, releaseYear, title);
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
				&& Objects.equals(releaseYear, other.releaseYear) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", releaseYear=" + releaseYear + ", price=" + price
				+ ", id=" + id + "]";
	}

}
