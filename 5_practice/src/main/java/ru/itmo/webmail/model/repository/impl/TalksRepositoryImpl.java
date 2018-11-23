package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.TalksRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalksRepositoryImpl implements TalksRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(Talk talk, String text) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Talk (sourceUserId, targetUserId, text, creationTime) VALUES (?, ?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, talk.getSourceUserId());
                statement.setLong(2, talk.getTargetUserId());
                statement.setString(3, text);
                if (statement.executeUpdate() == 1) {
                    //No operations.
                } else {
                    throw new RepositoryException("Can't save Talk.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Talk.", e);
        }
    }

    @Override
    public List<Talk> findAllForUser(long userId) {
        List<Talk> talks = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Talk ORDER BY creationTime")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Talk curTalk = toTalk(statement.getMetaData(), resultSet, userId);

                        if (curTalk != null) {
                            talks.add(curTalk);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all users.", e);
        }
        return talks;
    }

    private Talk toTalk(ResultSetMetaData metaData, ResultSet resultSet, long userId) throws SQLException {
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                talk.setId(resultSet.getLong(i));
            } else if ("sourceUserId".equalsIgnoreCase(columnName)) {
                talk.setSourceUserId(resultSet.getLong(i));
            } else if ("targetUserId".equalsIgnoreCase(columnName)) {
                talk.setTargetUserId(resultSet.getLong(i));
            } else if ("text".equalsIgnoreCase(columnName)) {
                talk.setText(resultSet.getString(i));
            } else if ("creationTime".equalsIgnoreCase(columnName)) {
                talk.setCreationTime(resultSet.getTimestamp(i));
            } else {
                throw new RepositoryException("Unexpected column 'Talk." + columnName + "'.");
            }
        }

        if (talk.getSourceUserId() != userId && talk.getTargetUserId() != userId) {
            return null;
        }

        return talk;
    }
}
