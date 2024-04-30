package org.example.shop.repository;

import java.util.List;

import org.example.shop.domain.item.Item;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(em);
		} else {
			em.merge(em);
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i",Item.class)
			.getResultList();
	}
}
