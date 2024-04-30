package org.example.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("Book")
@Getter @Setter
@Entity
public class Book extends Item{
	private String author;
	private String isbn;

}
