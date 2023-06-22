package com.victorkessler.quotationfreight.infrastructure.repository;

import com.victorkessler.quotationfreight.domain.model.Freight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FreightRepository extends CrudRepository<Freight, UUID> {}
