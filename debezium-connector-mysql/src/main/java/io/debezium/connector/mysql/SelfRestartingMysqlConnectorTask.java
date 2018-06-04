package io.debezium.connector.mysql;

public final class SelfRestartingMysqlConnectorTask extends SelfRestartingTask<MySqlConnectorTask>{

    public SelfRestartingMysqlConnectorTask() {
        super(MySqlConnectorTask.class);
    }
}
