/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.connector.mysql;

public final class SelfRestartingMySqlConnectorTask extends SelfRestartingTask<MySqlConnectorTask>{

    public SelfRestartingMySqlConnectorTask() {
        super(MySqlConnectorTask.class);
    }
}
