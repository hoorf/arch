package org.github.hoorf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondJdbcTemplate;


    @Transactional
    public void totalCommit() {

        primaryJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (1,1)");
        secondJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (2,2)");
        primaryJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (3,3)");

    }

    @Transactional
    public void totalRollback()  {

        primaryJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (1,1)");
        secondJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (2,2)");
        primaryJdbcTemplate.execute("insert into t_order_1(user_id,order_id) values (3,3)");
        throw new RuntimeException();
    }

}
