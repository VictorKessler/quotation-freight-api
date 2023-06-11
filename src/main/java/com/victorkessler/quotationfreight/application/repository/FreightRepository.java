package com.victorkessler.quotationfreight.application.repository;

import com.victorkessler.quotationfreight.domain.model.Freight;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FreightRepository extends CrudRepository<Freight, UUID> {
}
