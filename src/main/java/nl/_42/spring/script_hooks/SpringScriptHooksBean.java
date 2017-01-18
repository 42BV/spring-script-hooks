package nl._42.spring.script_hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Responsible for the actual setting up and tearing down of the container. The setting up
 * is triggered at @PostConstruct time. The tearing down is triggered at @PreDestroy time.
 */
public class SpringScriptHooksBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringScriptHooksBean.class);

    private final SpringScriptHooksProperties properties;

    public SpringScriptHooksBean(SpringScriptHooksProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info(">>> Configuring Docker Postgres");
        LOGGER.info("| Image name: " + properties.getImageName());
        LOGGER.info("| Container name: " + properties.getContainerName());
        LOGGER.info("| Port: " + properties.getPort());
        LOGGER.info("| Password: " + properties.getPassword());
        LOGGER.info("| Std out: " + properties.getStdOutFilename());
        LOGGER.info("| Std err: " + properties.getStdErrFilename());
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info(">>> Tearing down Docker 42");
    }

}
