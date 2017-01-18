package nl._42.spring.script_hooks;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "docker.postgres", ignoreUnknownFields = false)
public class SpringScriptHooksProperties {

    private boolean enabled = false;

    private String stdOutFilename = "docker-std-out.log";

    private String stdErrFilename = "docker-std-err.log";

    private String password = "postgres";

    private Integer port = 5432;

    private String imageName = "postgres";

    private String containerName = "postgression";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getStdOutFilename() {
        return stdOutFilename;
    }

    public void setStdOutFilename(String stdOutFilename) {
        this.stdOutFilename = stdOutFilename;
    }

    public String getStdErrFilename() {
        return stdErrFilename;
    }

    public void setStdErrFilename(String stdErrFilename) {
        this.stdErrFilename = stdErrFilename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public Map<String, String> getProperties() {
        Map<String,String> properties = new HashMap<>();
        properties.put("stdOutFilename", getStdOutFilename());
        properties.put("stdErrFilename", getStdErrFilename());
        properties.put("password", getPassword());
        properties.put("port", getPort().toString());
        properties.put("containerName", getContainerName());
        properties.put("imageName", getImageName());
        return properties;
    }

}
