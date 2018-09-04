package ro.esolutions.eipl.configurations

import org.postgresql.ds.PGPoolingDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import ru.yandex.qatools.embed.postgresql.PostgresExecutable
import ru.yandex.qatools.embed.postgresql.PostgresProcess
import ru.yandex.qatools.embed.postgresql.PostgresStarter
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig

import javax.annotation.PreDestroy
import javax.sql.DataSource

@Configuration
class EmbeddedPostgreSQLConfiguration {
    private PostgresProcess process
    PostgresConfig config = PostgresConfig.defaultWithDbName('eipl', 'eipl', 'eipl')

    @Bean
    @DependsOn('postgresProcess')
    DataSource dataSource() {
        println "hello dad"
        PGPoolingDataSource dataSource = new PGPoolingDataSource()
        dataSource.user = config.credentials().username()
        dataSource.password = config.credentials().password()
        dataSource.portNumber = config.net().port()
        dataSource.serverName = config.net().host()
        dataSource.databaseName = config.storage().dbName()
        return dataSource
    }

    @Bean(destroyMethod = 'stop', name = 'postgresProcess')
    PostgresProcess postgresProcess() throws IOException {
        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance()
        PostgresExecutable exec = runtime.prepare(config)
        process = exec.start()
        return process
    }

    @PreDestroy
    void preDestroy() {
        process.stop()
    }
}
