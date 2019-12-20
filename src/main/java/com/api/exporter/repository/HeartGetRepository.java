package com.api.exporter.repository;

import com.api.exporter.model.HeartGetApi.GetBody;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeartGetRepository extends JpaRepository<GetBody, Long> {

}