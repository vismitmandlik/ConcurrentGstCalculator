package src.com.gstcalculator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class InvoiceInput {
    BlockingQueue<Invoice> invoiceQueue;

    // Constructor initializes the queue
    public InvoiceInput() {
        invoiceQueue = new LinkedBlockingQueue<>();
    }

    // Method to add new invoices
    public void addInvoice(Invoice invoice) throws InterruptedException {
        invoiceQueue.put(invoice);
    }
}
