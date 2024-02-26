package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.repositories.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceSRV {
    @Autowired
    private InvoiceDAO invoiceDAO;


}
