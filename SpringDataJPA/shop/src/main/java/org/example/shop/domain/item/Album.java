package org.example.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("Album")
@Getter
@Setter
@Entity
public class Album extends Item{
	private String artist;
	private String etc;
}
