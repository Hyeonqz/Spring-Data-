package org.example.shop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long id;

	private String name;

	@Embedded // 내장 타입을 내재 했다고 명시해줌
	private Address address;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
}
