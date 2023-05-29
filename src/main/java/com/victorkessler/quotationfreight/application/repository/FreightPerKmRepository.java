package com.victorkessler.quotationfreight.application.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.victorkessler.quotationfreight.domain.model.FreightPerKm;

@Repository
public interface FreightPerKmRepository extends CrudRepository<FreightPerKm, UUID> {}
