package com.javohir.lastproject.repository;

import com.javohir.lastproject.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query(value = "select * from invoice where issued>due", nativeQuery = true)
    List<Invoice> getExpiredInvoices();

    @Query(value =
            "select i.id as invoice_id, py.amount as reimburse from invoice i\n" +
            "    join payment py on i.id=py.invoice_id group by i.id, py.amount, i.id having count(py.invoice_id)<>0;", nativeQuery = true)
    List<?> getOverpaidInvoices();
}
