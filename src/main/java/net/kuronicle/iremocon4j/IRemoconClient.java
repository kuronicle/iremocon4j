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

public class IRemoconClient {

    /**
     * iRemoconのデフォルトポート番号。
     */
    private static final int DEFAULT_PORT = 51013;

    /**
     * iRemoconコマンド：接続確認用。
     */
    public static final String COMMAND_CHECK_CONNECTION = "au";
    
    /**
     * iRemoeconコマンド：赤外線発信。
     */
    public static final String COMMAND_SEND_IR = "is";
    
    /**
     * ロガー。
     */
    private static Logger logger = LoggerFactory.getLogger(IRemoconClient.class);

    /**
     * iRemoconのホスト名。
     */
    private String hostName;

    /**
     * iRemoconのポート番号。
     */
    private int port;

    /**
     * Tenlneクライアント。
     */
    private TelnetClient telnetClient;

    public IRemoconClient(String hostName) {
        this(hostName, DEFAULT_PORT);
    }

    public IRemoconClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        this.telnetClient = new TelnetClient();
    }

    public String sendCommand(String commandName, String... parameter) {
        String iRemoconCommand = buildIRemoconCommand(commandName, parameter);
        String telnetResponse = sendTelnetCommand(iRemoconCommand);
        return telnetResponse;
    }

    private String sendTelnetCommand(String iRemoconCommand) {
        Writer requestWriter = new OutputStreamWriter(telnetClient.getOutputStream());
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(telnetClient.getInputStream()));
        String telnetResponse;
        try {
            logger.debug("send command. command=" + iRemoconCommand);
            requestWriter.write(iRemoconCommand);
            requestWriter.flush();
            Thread.sleep(1000);
            
            telnetResponse = responseReader.readLine();
            logger.debug("receive response. response=" + telnetResponse);
            return telnetResponse;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                requestWriter.close();
                responseReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    private String buildIRemoconCommand(String commandName, String[] parameter) {
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("*");
        commandBuilder.append(commandName);
        for (int i = 0; i < parameter.length; i++) {
            commandBuilder.append(";" + parameter[i]);
        }
        commandBuilder.append(System.getProperty("line.separator"));
        return commandBuilder.toString();
    }

    public void connect() {
        try {
            logger.debug(String.format("connect iRemocon. hostName=%s, port=%d", hostName, port));
            telnetClient.connect(hostName, port);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (telnetClient != null) {
            try {
                logger.debug("disconnect iRemoecon.");
                telnetClient.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
