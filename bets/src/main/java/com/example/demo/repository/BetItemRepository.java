package com.example.demo.repository;

import com.example.demo.model.BetItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BetItemRepository extends JpaRepository<BetItem, Long> {

}