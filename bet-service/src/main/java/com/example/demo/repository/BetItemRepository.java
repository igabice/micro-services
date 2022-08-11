package com.example.demo.repository;

import com.example.demo.dto.BetItemData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BetItemRepository extends JpaRepository<BetItemData, Long> {

}