package org.github.hroof;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/database0?serverTimezone=UTC");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("database0", dataSource1);

        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/database1?serverTimezone=UTC");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("database1", dataSource2);

        ShardingTableRuleConfiguration tableRuleConfiguration = new ShardingTableRuleConfiguration("t_order", "database${0..1}.t_order_${0..1}");
        tableRuleConfiguration.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id",
                "dbShardingAlgorithm"));
        tableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id",
                "tableShardingAlgorithm"));

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "database${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        shardingRuleConfig.getTables().add(tableRuleConfiguration);

        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singletonList(shardingRuleConfig),
                new Properties());
        String sql = "insert into t_order (user_id, order_id) values (?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 2);

        preparedStatement.execute();

    }
}
