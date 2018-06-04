package io.debezium.connector.mysql;

public final class SelfRestartingMySqlConnectorTask extends SelfRestartingTask<MySqlConnectorTask>{

    public SelfRestartingMySqlConnectorTask() {
        super(MySqlConnectorTask.class);
    }
}
