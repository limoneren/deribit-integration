package com.limoneren.deribit.persistence.jpa;

import com.limoneren.deribit.persistence.model.UserAccountSummaryEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserAccountSummaryRepository extends CrudRepository<UserAccountSummaryEntity, Long> {
    List<UserAccountSummaryEntity> findAll();

    List<UserAccountSummaryEntity> findByClientId(String clientId);
}
