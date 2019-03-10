package com.warehouse.repository.custom;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.warehouse.model.ValidDeals;

@Repository
public class ValidDealRepositoryCustomImpl implements ValidDealRepositoryCustom {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidDealRepositoryCustomImpl.class);

    @Autowired
    private EntityManager entityManager;

	@Override
	public void batchSave(List<ValidDeals> validDeals) {
        final List<List<ValidDeals>> batch = Lists.partition(validDeals, 1000);

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

    private String createInsertQuery(List<ValidDeals> validDeals) {
        final StringBuilder stringBuilder = new StringBuilder(100000);
        stringBuilder.append("INSERT INTO valid_deals (deal_id, from_currency, to_currency, deal_time, amount, file_name) VALUES ");

        for (Iterator<ValidDeals> iterator = validDeals.iterator(); iterator.hasNext();) {
            ValidDeals validDeal = iterator.next();
            stringBuilder.append("(");
            stringBuilder.append("'").append(validDeal.getDealId()).append("', ");
            stringBuilder.append("'").append(validDeal.getFromCurrency().toString()).append("', ");
            stringBuilder.append("'").append(validDeal.getToCurrency().toString()).append("', ");
            stringBuilder.append("'").append(validDeal.getDealTime().toString()).append("', ");
            stringBuilder.append(String.valueOf(validDeal.getAmount())).append(", ");
            stringBuilder.append("'").append(validDeal.getFileName()).append("'");
            stringBuilder.append(")");

            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

}
