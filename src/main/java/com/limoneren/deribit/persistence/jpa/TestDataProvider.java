package com.limoneren.deribit.persistence.jpa;

import com.limoneren.deribit.persistence.model.UserAccountSummaryEntity;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Used to insert data into db on startup
 */
@Singleton
public class TestDataProvider {

  private static final Logger LOG = LoggerFactory.getLogger(TestDataProvider.class);
  private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
  private final UserAccountSummaryRepository userAccountSummaryRepository;

  public TestDataProvider(final UserAccountSummaryRepository userAccountSummaryRepository) {
    this.userAccountSummaryRepository = userAccountSummaryRepository;
  }

  @EventListener
  public void init(StartupEvent event) {
    if (userAccountSummaryRepository.findAll().isEmpty()) {
      LOG.info("Adding test data as empty database was found!");
      UserAccountSummaryEntity accountSummaryEntity = new UserAccountSummaryEntity();
      accountSummaryEntity.setClientId("aaabbbccc");
      accountSummaryEntity.setCurrency("BTC");
      accountSummaryEntity.setBalance( BigDecimal.ZERO);
      accountSummaryEntity.setReservedFunds( BigDecimal.ZERO);
      userAccountSummaryRepository.save(accountSummaryEntity);

      UserAccountSummaryEntity accountSummaryEntity2 = new UserAccountSummaryEntity();
      accountSummaryEntity2.setClientId("7H4NajEy");
      accountSummaryEntity2.setCurrency("SOL");
      accountSummaryEntity2.setBalance( BigDecimal.ZERO);
      accountSummaryEntity2.setReservedFunds( BigDecimal.ZERO);
      userAccountSummaryRepository.save(accountSummaryEntity2);

      UserAccountSummaryEntity accountSummaryEntity3 = new UserAccountSummaryEntity();
      accountSummaryEntity3.setClientId("7H4NajEy");
      accountSummaryEntity3.setCurrency("USDC");
      accountSummaryEntity3.setBalance( BigDecimal.ONE);
      accountSummaryEntity3.setReservedFunds( BigDecimal.ONE);
      userAccountSummaryRepository.save(accountSummaryEntity3);
    }
  }
}
