package nl._42.spring.script_hooks;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "docker.postgres", ignoreUnknownFields = false)
public class SpringScriptHooksProperties {

    private boolean enabled;

    private String stdOutFilename;

    private String stdErrFilename;

    private String password;

    private Integer port;

    private String imageName;

    private String containerName;

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

}
