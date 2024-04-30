package org.example.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("Movie")
@Getter
@Setter
@Entity
public class Movie extends Item {
	private String direction;
	private String actor;
}
