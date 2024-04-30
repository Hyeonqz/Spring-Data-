package org.example.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable //JPA 내장 타입이라는걸 명시하는 어노테이션
public class Address {
	private String city;
	private String street;
	private String zipcode;

	public Address (String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

}
