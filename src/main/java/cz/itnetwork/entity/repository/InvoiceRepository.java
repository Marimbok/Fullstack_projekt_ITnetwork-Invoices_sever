package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    List<InvoiceEntity> findByBuyer(PersonEntity buyer);

    List<InvoiceEntity> findBySeller(PersonEntity seller);

    @Query("""
            SELECT new cz.itnetwork.dto.InvoiceStatisticDTO(
            ROUND(COALESCE(SUM(actual.price), 0), 2),
            ROUND(COALESCE(SUM(everything.price), 0), 2),
            COUNT(*))
            FROM invoice everything
            LEFT JOIN invoice actual
            ON everything.id = actual.id
            AND YEAR(actual.issued) = YEAR(CURRENT_DATE)
            """)
    InvoiceStatisticDTO getInvoiceStatistics();
}
