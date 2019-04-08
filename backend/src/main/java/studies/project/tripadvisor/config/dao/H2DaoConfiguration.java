package studies.project.tripadvisor.config.dao;

import io.vavr.collection.List;
import io.vavr.control.Try;
import org.apache.commons.dbcp2.BasicDataSource;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileReader;
import java.net.URL;

@Configuration
public class H2DaoConfiguration {

    private final List<String> scripts = List.of("/trip-schema.sql", "/trip-schema-id-sequence.sql");

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.dbcp2.initial-size:5}") Integer initialSize, @Value("${spring.datasource.dbcp2.max-total:20}") Integer maxTotal) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setInitialSize(initialSize);
        basicDataSource.setMaxTotal(maxTotal);
        basicDataSource.setPoolPreparedStatements(true);
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:mem:;;DB_CLOSE_DELAY=-1");
        executeScripts(basicDataSource);
        return basicDataSource;
    }

    private void executeScripts(DataSource ds) {
        Try.withResources(ds::getConnection)
                .of(connection -> {
                    scripts.map(script -> getClass().getResource(script)).map(URL::getFile)
                            .map(file -> Try.of(() -> new FileReader(file)).get())
                            .forEach(fileReader -> Try.of(() -> RunScript.execute(connection, fileReader)).get());
                    return null;
                }).get();
    }
}