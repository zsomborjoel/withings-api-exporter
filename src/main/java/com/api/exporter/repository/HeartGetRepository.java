package com.api.exporter.repository;

import com.api.exporter.model.HeartGet;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeartGetRepository extends JpaRepository<HeartGet, Long> {

}