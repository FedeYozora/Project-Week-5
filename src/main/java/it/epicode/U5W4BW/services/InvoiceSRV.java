package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.config.EmailSender;
import it.epicode.U5W4BW.entities.Client;
import it.epicode.U5W4BW.entities.Invoice;
import it.epicode.U5W4BW.entities.InvoiceStatus;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.payloads.InvoiceDTO;
import it.epicode.U5W4BW.repositories.ClientDAO;
import it.epicode.U5W4BW.repositories.InvoiceDAO;
import it.epicode.U5W4BW.repositories.InvoiceStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InvoiceSRV {
    @Autowired
    private InvoiceDAO invoiceDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private InvoiceStatusDAO invoiceStatusDAO;
    @Autowired
    private EmailSender emailSender;

    public Page<Invoice> getInvoices(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return invoiceDAO.findAll(pageable);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Invoice saveInvoice(InvoiceDTO newInvoice) {
        Client found = clientDAO
                .findByEmail(newInvoice.email())
                .orElseThrow(() -> new NotFoundException(newInvoice.email()));
        InvoiceStatus invoiceStatus = invoiceStatusDAO
                .findByStatus(newInvoice.invoice_status())
                .orElseThrow(() -> new NotFoundException(newInvoice.invoice_status()));
        Invoice invoice = new Invoice(newInvoice.amount(), found, invoiceStatus);
        Invoice savedInvoice = invoiceDAO.save(invoice);
        emailSender.sendInvoiceEmail(found);
        return savedInvoice;
    }

    public void deleteInvoice(Long id) {
        Invoice found = getInvoiceById(id);
        invoiceDAO.delete(found);
    }

    public Page<Invoice> findByEmissionDate(int page, int size, String order, LocalDate data) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return invoiceDAO.findByEmissionDate(pageable, data);
    }

    public Page<Invoice> findByIdClient(int page, int size, String order, UUID id) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return invoiceDAO.findByClientId(pageable, id);
    }


    public Page<Invoice> findByAmount(int page, int size, String order, double imp1, double imp2) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return invoiceDAO.findByAmountBetween(pageable, imp1, imp2);
    }

    public Page<Invoice> findByYear(int page, int size, String order, int y) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return invoiceDAO.getByYear(pageable, y);

    }
}
