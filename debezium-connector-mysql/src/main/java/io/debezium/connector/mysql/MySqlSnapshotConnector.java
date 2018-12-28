/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.mysql;

import org.apache.kafka.common.config.Config;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlSnapshotConnector extends SourceConnector {
    private Map<String, String> props;

    @Override
    public String version() {
        return Module.version();
    }

    @Override
    public void start(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public void stop() {
        this.props = null;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MySqlSnapshotConnectorTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        return props == null ? Collections.emptyList() : Collections.singletonList(new HashMap<String, String>(props));
    }

    @Override
    public ConfigDef config() {
        return MySqlSnapshotConnectorConfig.configDef();
    }

    @Override
    public Config validate(Map<String, String> connectorConfigs) {
        return super.validate(connectorConfigs);
    }
}
