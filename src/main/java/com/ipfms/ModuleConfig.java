package com.ipfms;

import javax.persistence.EntityManager;

import com.ipfms.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.katharsis.core.internal.boot.TransactionRunner;
//import io.katharsis.jpa.JpaModule;
//import io.katharsis.jpa.JpaRepositoryConfig;

@Configuration
public class ModuleConfig {

    @Autowired
    private EntityManager em;

    @Autowired
    private TransactionRunner transactionRunner;

    /**
     * Expose JPA entities as repositories.
     * @return module
     */
/*    @Bean
    public JpaModule jpaModule() {
        JpaModule module = JpaModule.newServerModule(em, transactionRunner);

        // directly expose entity
        module.addRepository(JpaRepositoryConfig.builder(ClassHierarchy.class).build());
        module.addRepository(JpaRepositoryConfig.builder(Classification.class).build());
        module.addRepository(JpaRepositoryConfig.builder(Container.class).build());
        module.addRepository(JpaRepositoryConfig.builder(CustomAttribute.class).build());
        module.addRepository(JpaRepositoryConfig.builder(CustomAttributeValue.class).build());
        module.addRepository(JpaRepositoryConfig.builder(LabelColor.class).build());
        module.addRepository(JpaRepositoryConfig.builder(Location.class).build());
        module.addRepository(JpaRepositoryConfig.builder(Record.class).build());
        module.addRepository(JpaRepositoryConfig.builder(RecordClassification.class).build());
        module.addRepository(JpaRepositoryConfig.builder(RecordType.class).build());
        module.addRepository(JpaRepositoryConfig.builder(Role.class).build());
        module.addRepository(JpaRepositoryConfig.builder(User.class).build());
        module.addRepository(JpaRepositoryConfig.builder(UserLocation.class).build());
        module.addRepository(JpaRepositoryConfig.builder(UserRole.class).build());
        return module;
    }*/
}
