package pl.gajdek.alekino.config.http;


import org.hibernate.sql.exec.spi.JdbcOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories("pl.gajdek.alekino.domain.*")
@EnableTransactionManagement
public class HttpSessionConfig {

    @Autowired
    private JdbcOperations jdbcOperation;

    @Bean
    public JdbcOperationsSessionRepository sessionRepository() {
        return new JdbcOperationsSessionRepository(jdbcOperation);
    }

}
