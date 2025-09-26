package org.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.example.entity.EmployeeTest;
import org.example.entity.PayrollTest;
import org.example.entity.AttendanceTest;
import org.example.service.PayrollBatchServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    EmployeeTest.class,
    PayrollTest.class,
    AttendanceTest.class,
    PayrollBatchServiceTest.class
})
public class PayrollTestRunner {
}