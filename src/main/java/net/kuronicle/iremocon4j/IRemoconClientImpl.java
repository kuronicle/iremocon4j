package net.kuronicle.iremocon4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.SocketException;
import java.util.Date;
import java.util.List;

import net.kuronicle.iremocon4j.dto.Timer;
import net.kuronicle.iremocon4j.exception.IRemoconException;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author KUROIWA, Keijiro
 * 
 */
public class IRemoconClientImpl implements IRemoconClient {

    private static final int DEFAULT_PORT = 51013;

    private static Logger logger = LoggerFactory
            .getLogger(IRemoconClientImpl.class);

    private String hostName;

    private int port;

    private TelnetClient telnetClient;

    private String telnetSendCommandString = System
            .getProperty("line.separator");

    public IRemoconClientImpl(String hostName) {
        this(hostName, DEFAULT_PORT);
    }

    public IRemoconClientImpl(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        this.telnetClient = new TelnetClient();
    }

    public void connect() throws SocketException, IOException {
        logger.debug(String.format("connect iRemocon. hostName=%s, port=%d",
                hostName, port));
        telnetClient.connect(hostName, port);
    }

    public void disconnect() throws IOException {
        if (telnetClient != null && telnetClient.isConnected()) {
            logger.debug("disconnect iRemoecon.");
            telnetClient.disconnect();
        }
    }

    public String sendCommand(IRemoconCommand command, String... parameters)
            throws IRemoconException {
        String iRemoconCommand = buildIRemoconCommand(command, parameters);
        String telnetResponse;
        try {
            telnetResponse = sendTelnetCommand(iRemoconCommand);
        } catch (IOException e) {
            String message = "Exception was occerd when sending telnet command to iRemocon.";
            IRemoconException iRemoconException = new IRemoconException(message, e);
            throw iRemoconException;
        }
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

    private String buildIRemoconCommand(IRemoconCommand command, String[] parameters) {
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("*");
        commandBuilder.append(command.getCommand());
        for (int i = 0; i < parameters.length; i++) {
            commandBuilder.append(";" + parameters[i]);
        }
        commandBuilder.append(telnetSendCommandString);
        return commandBuilder.toString();
    }

    public boolean checkConnection() {
        // TODO Auto-generated method stub
        return false;
    }

    public void sendIr(int irId) throws IRemoconException {
        // TODO Auto-generated method stub
        
    }

    public void startLearningIr(int irId) throws IRemoconException {
        // TODO Auto-generated method stub
        
    }

    public void stopLearningIr() throws IRemoconException {
        // TODO Auto-generated method stub
        
    }


    public void startLearningIr(int irid, int timeoutSec)
            throws IRemoconException {
        // TODO Auto-generated method stub
        
    }

    public void setTimer(int irId, Date timerDate, int repeatTimeSec)
            throws IRemoconException {
        // TODO Auto-generated method stub
        
    }

    public List<Timer> getTimerList() {
        // TODO Auto-generated method stub
        return null;
    }

    public void deleteTimer(int timerId) {
        // TODO Auto-generated method stub
        
    }

    public void setTime(Date date) {
        // TODO Auto-generated method stub
        
    }

    public long getTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

}
