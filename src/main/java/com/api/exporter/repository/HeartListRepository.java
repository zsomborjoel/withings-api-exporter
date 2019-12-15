package com.api.exporter.repository;

import com.api.exporter.model.HeartList;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeartListRepository extends JpaRepository<HeartList, Long> {

}