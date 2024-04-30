package org.example.shop.service;

import java.util.List;

import org.example.shop.domain.Delivery;
import org.example.shop.domain.Member;
import org.example.shop.domain.Order;
import org.example.shop.domain.OrderItem;
import org.example.shop.domain.item.Item;
import org.example.shop.repository.ItemRepository;
import org.example.shop.repository.MemberRepository;
import org.example.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	// 주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// 엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// 주문 생성
		Order order = Order.createOrder(member,delivery,orderItem);

		return order.getId();
	}

	// 취소
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancle();
	}

	// 검색
	public List<Order> searchOrder(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}
}
