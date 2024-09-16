package src.com.gstcalculator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GSTCalculator {
    public static void main(String[] args) throws InterruptedException {
        InvoiceInput input = new InvoiceInput();
        input.addInvoice(new Invoice(1, 100, 4));  
        input.addInvoice(new Invoice(2, 200, 12));
    

        
        int numProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numProcessors*2);

        Thread monitorThread = new Thread(new InvoiceMonitor(input.invoiceQueue, executorService));
        monitorThread.start();

        while (!monitorThread.isAlive()) {
            if (!monitorThread.isAlive()) {
                System.out.println("Monitor thread interrupted or terminated, restarting...");
                monitorThread = new Thread(new InvoiceMonitor(input.invoiceQueue, executorService));
                monitorThread.start();
            }
            
            Thread.sleep(2000);
        }

        input.addInvoice(new Invoice(3, 200, 12));
        monitorThread.join();


        System.out.println("All invoices processed. Total processed: " + InvoiceProcessor.getProcessedCount());
    }
}
