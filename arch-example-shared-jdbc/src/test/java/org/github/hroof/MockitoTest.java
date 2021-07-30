package org.github.hroof;

import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MockitoTest {


    @Test
    public void test() throws SQLException {

        DataSource dataSource = Mockito.mock(DataSource.class);
        //Mockito.when(dataSource.getConnection()).thenReturn(ff)
        System.out.println(dataSource.getConnection());
    }

}
