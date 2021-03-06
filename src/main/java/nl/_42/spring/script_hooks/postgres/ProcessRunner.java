package nl._42.spring.script_hooks.postgres;

import nl._42.spring.script_hooks.SpringScriptHooksProperties;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ProcessRunner extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessRunner.class);

    private final static String COMMAND =
            "docker run --rm -e POSTGRES_PASSWORD=${password} -p ${port}:5432 --name ${containerName} ${imageName}:${imageVersion}";

    private final SpringScriptHooksProperties properties;

    private final DockerTailer tailer;

    public ProcessRunner(SpringScriptHooksProperties properties) {
        super();

        try {
            cleanupFile(properties.getStdErrFilename());
            cleanupFile(properties.getStdOutFilename());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.properties = properties;
        this.tailer = new DockerTailer(
                this,
                properties.getStdOutFilename(),
                properties.getStdErrFilename(),
                properties.getStartupVerificationText(),
                properties.getTimeout());
    }

    public boolean verify() throws IOException {
        return tailer.verify();
    }

    private static String[] replacePlaceholders(Map<String, String> properties, String commandLine) {
        StrSubstitutor sub = new StrSubstitutor(properties);
        String[] oldCmd = commandLine.split(" ");
        String[] newCmd = new String[oldCmd.length];
        int counter = 0;
        for (String cmd : oldCmd) {
            newCmd[counter++] = sub.replace(cmd);
        }
        return newCmd;
    }

    private static void cleanupFile(String standardOutFilename) throws IOException {
        File output = new File(standardOutFilename);
        output.delete();
        output.createNewFile();
    }

    public void run() {

        File output = new File(properties.getStdOutFilename());
        File errors = new File(properties.getStdErrFilename());

        final Process process;
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.redirectOutput(output);
            pb.redirectError(errors);

            LOGGER.debug("| Process redirectInput(): " + pb.redirectInput());
            LOGGER.debug("| Process redirectOutput(): " + pb.redirectOutput());
            LOGGER.debug("| Process redirectError(): " + pb.redirectError());

            pb.command(replacePlaceholders(properties.getProperties(), COMMAND));
            process = pb.start();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            while (process.isAlive()) {
                Thread.sleep(100);
            }
        } catch (InterruptedException err) {
            LOGGER.info("| Interruption signal received, proceeding to destroy process");
            process.destroy();
            LOGGER.info("| Process destroyed");
        }
    }

}
