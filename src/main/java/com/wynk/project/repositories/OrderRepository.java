package com.wynk.project.repositories;

import com.wynk.project.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Item, Integer> {

}
