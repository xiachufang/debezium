package io.debezium.connector.mysql;

import io.debezium.config.Configuration;
import io.debezium.config.Field;
import org.apache.kafka.common.config.ConfigDef;

public class MySqlSnapshotConnectorConfig extends MySqlConnectorConfig {

  public static final String SNAPSHOT_STATUS_FILE_NAME = "snapshot.status.file";
  public static final Field SNAPSHOT_STATUS_FILE = Field.create(SNAPSHOT_STATUS_FILE_NAME)
          .withDisplayName("Snapshot Status File Path")
          .withType(ConfigDef.Type.STRING)
          .withImportance(ConfigDef.Importance.MEDIUM)
          .withDescription("Snapshot Status File Path");

  public final String snapshotStatusFile;

  public MySqlSnapshotConnectorConfig(Configuration config) {
    super(config);
    snapshotStatusFile = config.getString(SNAPSHOT_STATUS_FILE);
  }

  protected static ConfigDef configDef() {
    ConfigDef config = MySqlConnectorConfig.configDef();
    Field.group(config, "snapshot", SNAPSHOT_STATUS_FILE);
    return config;
  }
}
