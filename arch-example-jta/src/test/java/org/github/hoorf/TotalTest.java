package org.github.hoorf;

import org.github.hoorf.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalTest {

    @Autowired
    private TestService testService;

    @Test
    public void commit() {
        testService.totalCommit();
    }

    @Test
    public void rollback()  {
        testService.totalRollback();
    }
}
