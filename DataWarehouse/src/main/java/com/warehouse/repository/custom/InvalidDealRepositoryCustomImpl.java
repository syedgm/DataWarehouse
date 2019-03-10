package com.warehouse.repository.custom;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.warehouse.model.InvalidDeals;

@Repository
public class InvalidDealRepositoryCustomImpl implements InvalidDealRepositoryCustom {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidDealRepositoryCustomImpl.class);

    @Autowired
    private EntityManager entityManager;

	@Override
	public void batchSave(List<InvalidDeals> invalidDeals) {
        final List<List<InvalidDeals>> batch = Lists.partition(invalidDeals, 1000);

        batch.forEach(b -> {
            Session session = entityManager.unwrap(Session.class);
            session.doWork(connection -> {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(createInsertQuery(b));
                    preparedStatement.executeLargeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Error occurred on PreparedStatement.", e);
                } finally {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            });
        });
    }

    private String createInsertQuery(List<InvalidDeals> invalidDeals) {
        final StringBuilder stringBuilder = new StringBuilder(100000);
        stringBuilder.append("INSERT INTO invalid_deals (deal_id, from_currency, to_currency, deal_time, amount, file_name) VALUES ");

        for (Iterator<InvalidDeals> iterator = invalidDeals.iterator(); iterator.hasNext();) {
            InvalidDeals invalidDeal = iterator.next();
            stringBuilder.append("(");
            stringBuilder.append("'").append(invalidDeal.getDealId()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getFromCurrency()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getToCurrency()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getDealTime()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getAmount()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getFileName()).append("'");
            stringBuilder.append(")");

            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }
}
