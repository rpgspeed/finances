package com.finances.repositories;

import com.finances.domain.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository  extends CrudRepository<StockEntity, Integer> {

}
