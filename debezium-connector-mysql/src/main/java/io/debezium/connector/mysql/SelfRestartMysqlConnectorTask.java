package io.debezium.connector.mysql;

public final class SelfRestartMysqlConnectorTask extends SelfRestartingTask<MySqlConnectorTask>{

    public SelfRestartMysqlConnectorTask() {
        super(MySqlConnectorTask.class);
    }
}
