package com.api.exporter.repository;

import com.api.exporter.model.HeartListApi.Series;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeartListRepository extends JpaRepository<Series, Long> {

}