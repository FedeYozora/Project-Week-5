package it.epicode.U5W4BW.config;

import it.epicode.U5W4BW.entities.Client;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private String mailgunAPIKey;

    private String domainName;

    public EmailSender(@Value("${mailgun.apikey}") String mailgunAPIKey,
                       @Value("${mailgun.domainname}") String domainName) {
        this.mailgunAPIKey = mailgunAPIKey;
        this.domainName = domainName;
    }

    public void sendInvoiceEmail(Client recipient) {
        System.out.println("API Key: " + mailgunAPIKey);
        System.out.println("Domain Name: " + domainName);

        Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailgunAPIKey)
                .queryString("from", "Francesco Buonocore <fbuonocore655@gmail.com>")
                .queryString("to", recipient.getContactEmail())
                .queryString("subject", "Invoice sent")
                .queryString("text", "The invoice has been sent to " + recipient.getContactName())
                .asJsonAsync(response -> {
                    if (response.getStatus() == 200) {
                        System.out.println("Sent!");
                    } else {
                        System.out.println("Error sending email: " + response.getStatus() + " " + response.getStatusText());
                    }
                });
    }
}