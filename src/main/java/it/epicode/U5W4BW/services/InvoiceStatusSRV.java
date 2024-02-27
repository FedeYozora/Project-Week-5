package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.InvoiceStatus;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.payloads.InvoiceStatusDTO;
import it.epicode.U5W4BW.repositories.InvoiceStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvoiceStatusSRV {
    @Autowired
    InvoiceStatusDAO invoiceStatusDAO;

    public Page<InvoiceStatus> getInvoiceStatus(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return invoiceStatusDAO.findAll(pageable);
    }

    public InvoiceStatus saveInvoiceStatus(InvoiceStatusDTO newInvoiceStatus) {
        invoiceStatusDAO.findByStatus(newInvoiceStatus.invoice_status()).ifPresent(invoiceStatus -> {
            throw new BadRequestException("Invoice status " + newInvoiceStatus.invoice_status() + " already exist!");
        });
        InvoiceStatus invoiceStatus = new InvoiceStatus(newInvoiceStatus.invoice_status());
        return invoiceStatusDAO.save(invoiceStatus);
    }

    public InvoiceStatus getInvoiceStatusById(UUID id) {
        return invoiceStatusDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteInvoiceStatus(UUID id) {
        InvoiceStatus found = getInvoiceStatusById(id);
        invoiceStatusDAO.delete(found);
    }

}
