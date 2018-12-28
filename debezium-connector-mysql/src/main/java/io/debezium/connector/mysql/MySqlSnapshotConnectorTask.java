/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.mysql;

import io.debezium.annotation.NotThreadSafe;
import io.debezium.config.Configuration;
import io.debezium.config.Field;
import io.debezium.connector.common.BaseSourceTask;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@NotThreadSafe
public final class MySqlSnapshotConnectorTask extends BaseSourceTask {
    private volatile MySqlTaskContext taskContext;
    private volatile SnapshotReader snapshotReader;
    private MySqlSnapshotConnectorConfig config;
    private static final Logger log = LoggerFactory.getLogger(MySqlSnapshotConnectorTask.class);

    @Override
    public synchronized void start(Configuration config) {
        this.config = new MySqlSnapshotConnectorConfig(config);
        this.taskContext = new MySqlTaskContext(config);

        if (this.config.snapshotStatusFile == null) {
            throw new ConnectException("snapshot.status.file is empty");
        }

        log.info("snapshot init, path: " + this.config.snapshotStatusFile);
        Path path = Paths.get(this.config.snapshotStatusFile);

        if (Files.exists(path)) {
            log.error("snapshot status exist");
            throw new ConnectException("snapshot status exist");
        }


        try {
            log.info("snapshot start, path: " + this.config.snapshotStatusFile);
            Files.createDirectories(path.getParent());
            Files.write(path, "started".getBytes());
        } catch (IOException e) {
            throw new ConnectException(e.toString());
        }

        this.taskContext.start();
        if (taskContext.historyExists()) {
            throw new ConnectException("schema history exist");
        }
        taskContext.initializeHistoryStorage();

        snapshotReader = new SnapshotReader("snapshot", taskContext);
        snapshotReader.generateInsertEvents();
        snapshotReader.uponCompletion(this::completReaders);
        snapshotReader.initialize();
        snapshotReader.start();
    }

    protected void completReaders() {
        Path path = Paths.get(this.config.snapshotStatusFile);
        log.info("snapshot finish, path: " + this.config.snapshotStatusFile);
        try {
            Files.write(path, "completed".getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new ConnectException(e.toString());
        }
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        if (snapshotReader == null) {
            return null;
        }

        return snapshotReader.poll();
    }

    @Override
    public void stop() {
        if (snapshotReader != null) {
            snapshotReader.stop();
        }
    }

    @Override
    public String version() {
        return Module.version();
    }

    @Override
    protected Iterable<Field> getAllConfigurationFields() {
        return MySqlSnapshotConnectorConfig.ALL_FIELDS;
    }
}
