package srteixeiradias.libraryapi.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LibraryDataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource hirakiDataSource(){
        final var ds = new HikariConfig();

        ds.setDriverClassName(this.driverClassName);
        ds.setJdbcUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);

        ds.setMaximumPoolSize(10); //maximo de conexões liberadas
        ds.setMinimumIdle(1); //tamanho inicial do pool
        ds.setPoolName("library-pool");
        ds.setMaxLifetime(600000); //600k ms (10 minutos) maximo de conexão
        ds.setConnectionTimeout(100000); //tempo para estabelecer uma conexão
        ds.setConnectionTestQuery("select 1"); //query de teste ao iniciar o ds
        return new HikariDataSource(ds);
    }

}
