package org.example.service;

import org.junit.Test;
import static org.junit.Assert.*;

public class PayrollBatchServiceTest {
    
    @Test
    public void testQueueOperations() {
        PayrollBatchService service = new PayrollBatchService();
        
        // Test initial empty queue
        assertEquals(0, service.getQueueSize());
        
        // Test adding to queue
        service.addToQueue("emp-1");
        service.addToQueue("emp-2");
        assertEquals(2, service.getQueueSize());
    }
}