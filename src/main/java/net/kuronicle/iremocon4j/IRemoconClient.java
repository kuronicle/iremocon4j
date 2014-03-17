package net.kuronicle.iremocon4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author KUROIWA, Keijiro
 * 
 */
public class IRemoconClient {

    private static final int DEFAULT_PORT = 51013;

    public static final String COMMAND_CHECK_CONNECTION = "au";

    public static final String COMMAND_SEND_IR = "is";

    private static Logger logger = LoggerFactory
            .getLogger(IRemoconClient.class);

    private String hostName;

    private int port;

    private TelnetClient telnetClient;

    private String telnetSendCommandString = System
            .getProperty("line.separator");

    public IRemoconClient(String hostName) {
        this(hostName, DEFAULT_PORT);
    }

    public IRemoconClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        this.telnetClient = new TelnetClient();
    }

    /**
     * iRemoconにコマンドを送信する。事前に{@link #connect()}でiRemoconと接続しておくこと。
     * 
     * @param commandName
     * @param parameter
     * @return
     * @throws IOException
     */
    public String sendCommand(String commandName, String... parameter)
            throws IOException {
        String iRemoconCommand = buildIRemoconCommand(commandName, parameter);
        String telnetResponse = sendTelnetCommand(iRemoconCommand);
        return telnetResponse;
    }

    private String sendTelnetCommand(String iRemoconCommand) throws IOException {
        Writer requestWriter = new OutputStreamWriter(
                telnetClient.getOutputStream());
        BufferedReader responseReader = new BufferedReader(
                new InputStreamReader(telnetClient.getInputStream()));

        try {
            logger.debug("send command. command=" + iRemoconCommand);
            requestWriter.write(iRemoconCommand);
            requestWriter.flush();
            
            String telnetResponse = responseReader.readLine();
            logger.debug("receive response. response=" + telnetResponse);
            return telnetResponse;
        } finally {
            requestWriter.close();
            responseReader.close();
        }
    }

    private String buildIRemoconCommand(String commandName, String[] parameter) {
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("*");
        commandBuilder.append(commandName);
        for (int i = 0; i < parameter.length; i++) {
            commandBuilder.append(";" + parameter[i]);
        }
        commandBuilder.append(telnetSendCommandString);
        return commandBuilder.toString();
    }

    /**
     * iRemoconとTelnet接続する。
     * 
     * @throws SocketException
     * @throws IOException
     */
    public void connect() throws SocketException, IOException {
        logger.debug(String.format("connect iRemocon. hostName=%s, port=%d",
                hostName, port));
        telnetClient.connect(hostName, port);
    }

    /**
     * iRemoconと切断する。iRemoconの同時接続数は1であるため、接続後は必ず切断すること。
     * 
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if (telnetClient != null && telnetClient.isConnected()) {
            logger.debug("disconnect iRemoecon.");
            telnetClient.disconnect();
        }
    }

}
