package src.com.gstcalculator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class InvoiceMonitor implements Runnable {
    private final BlockingQueue<Invoice> invoiceQueue;
    private final ExecutorService executorService;

    public InvoiceMonitor(BlockingQueue<Invoice> invoiceQueue, ExecutorService executorService) {
        this.invoiceQueue = invoiceQueue;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Invoice invoice = invoiceQueue.take();  
                executorService.submit(new InvoiceProcessor(invoice));  
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  
        }
    }
}
