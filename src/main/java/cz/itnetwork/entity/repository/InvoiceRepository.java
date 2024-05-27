package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * SQL query to get statistics of all invoices.
     * @return Statistics of all invoices, which contain: this year SUM of prices, all time SUM of prices and number of invoices.
     */
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
