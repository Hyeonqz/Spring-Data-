package org.example.shop.domain.item;

import java.util.ArrayList;
import java.util.List;

import org.example.shop.domain.Category;
import org.example.shop.exception.NotEnoughStockException;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorColumn(name="dtype") // 구분하는 것.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
@Entity
public abstract class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private Long id;

	private String name;
	private int price;
	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	// == 비즈니스 로직 == \\

	// 재고 증가
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
	}


	// 원래 변경을 해야할 일이 있으면, Setter 를 쓰는게 아닌 비지니스 메소드를 작성해서 해야한다.

}
