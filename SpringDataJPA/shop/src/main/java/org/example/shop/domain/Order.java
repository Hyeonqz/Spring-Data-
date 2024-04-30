package org.example.shop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate;

	private OrderStatus status;

	// 연관관계 편의 메소드
	// 컨트롤 하는 쪽이 가지고 있는게 좋다. 즉 FK 를 가지고 있는 쪽이 좋다.
	public void setMember (Member member) {
		this.member = member;
		member.getOrders().add(this);
	}

	public void addOrderItem (OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void setDelivery (Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

	// 생성 메소드 \\
	public static Order createOrder (Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		return order;
	}
	// 앞으로 생성하는 로직을 수정하려면 위 코드에서만 하면되닌까, 도메인 모델 패턴이 좋다.

	// 주문 취소
	public void cancle () {
		if (delivery.getStatus() == DeliveryStatus.COMP) {
			throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
		}

		this.setStatus(OrderStatus.CANCEL);
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}

	// 조회 로직 //
	public int getTotalPrice () {
		int totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;
	}

}
