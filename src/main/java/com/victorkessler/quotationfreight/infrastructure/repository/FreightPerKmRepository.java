package com.victorkessler.quotationfreight.infrastructure.repository;

import com.victorkessler.quotationfreight.domain.model.FreightPerKm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FreightPerKmRepository extends CrudRepository<FreightPerKm, UUID> {}
