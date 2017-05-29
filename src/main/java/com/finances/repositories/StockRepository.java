package com.finances.repositories;

import com.finances.domain.StockEntity;
import org.hibernate.type.ListType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StockRepository  extends JpaRepository<StockEntity, Integer> {

    StockEntity findBySymbol(final String symbol);

    @Query("SELECT t FROM StockEntity t ORDER BY t.updatedAt asc")
    List<StockEntity> getLastOldStocks();
}
