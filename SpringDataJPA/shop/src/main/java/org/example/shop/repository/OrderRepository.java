package org.example.shop.repository;

import java.util.List;

import org.example.shop.domain.Order;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
	private final EntityManager em;

	public void save (Order order) {
		em.persist(order);
	}

	public Order findOne (Long id) {
		return em.find(Order.class, id);
	}

/*	public List<Order> findAll(OrderSearch orderSearch) {
		// 동적 쿼리를 해결하기가 쉽지 않다.
		List<Order> resultList = em.createQuery("select o from Order o join o.member m" +
				" where o.status = :status" +
				" and m.name like :name", Order.class)
			.setParameter("status", orderSearch.getOrderStatus())
			.setParameter("name",orderSearch.getMemberName())
			.setMaxResults(1000) // 최대 1000건 가져온다.
			.getResultList();

		return resultList;
	}*/

}
