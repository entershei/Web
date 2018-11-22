package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailConfirmationRepositoryImpl implements EmailConfirmationRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public List<EmailConfirmation> findAll() {
        List<EmailConfirmation> users = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM User ORDER BY id")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(toEmailConfirmation(statement.getMetaData(), resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all email confirmations.", e);
        }
        return users;
    }

    @Override
    public EmailConfirmation findBySecret(String secret) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM EmailConfirmation WHERE secret=?")) {
                statement.setString(1, secret);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return toEmailConfirmation(statement.getMetaData(), resultSet);
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User by secret.", e);
        }
    }

    @Override
    public void save(long userId) {
        String secret = genNewSecret(userId);
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO EmailConfirmation (userId, secret, creationTime) VALUES (?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, userId);
                statement.setString(2, secret);
                if (statement.executeUpdate() == 1) {
                } else {
                    throw new RepositoryException("Can't save EmailConfirmation.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save EmailConfirmation.", e);
        }
    }

    private String genNewSecret(long userId) {
        StringBuilder secret = new StringBuilder();
        String key = "HelloWorld";

        for (int i = 0; i < key.length(); ++i) {
            secret.append(key.charAt(i));
            secret.append(userId % 10);
            userId /= 10;
        }

        return secret.toString();
    }

    private EmailConfirmation toEmailConfirmation (ResultSetMetaData metaData, ResultSet resultSet)
                                                                                            throws SQLException  {
        EmailConfirmation user = new EmailConfirmation();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if ("id".equalsIgnoreCase(columnName)) {
                user.setId(resultSet.getLong(i));
            } else if ("userId".equalsIgnoreCase(columnName)) {
                user.setUserId(resultSet.getLong(i));
            } else if ("secret".equalsIgnoreCase(columnName)) {
                user.setSecret(resultSet.getString(i));
            } else if ("creationTime".equalsIgnoreCase(columnName)) {
                user.setCreationTime(resultSet.getTimestamp(i));
            } else {
                throw new RepositoryException("Unexpected column 'EmailConfirmation." + columnName + "'.");
            }
        }
        return user;
    }

    private Date findCreationTime(long userId) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT creationTime FROM User WHERE id=?")) {
                statement.setLong(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getTimestamp(1);
                    }
                }
                throw new RepositoryException("Can't find EmailConfirmation.creationTime by id.");
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find EmailConfirmation.creationTime by id.", e);
        }
    }
}
