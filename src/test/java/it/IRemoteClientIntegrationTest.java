package it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.kuronicle.iremocon4j.IRemoconClient;

import org.junit.Test;

/**
 * 
 * @author KUROIWA, Keijiro
 *
 */
public class IRemoteClientIntegrationTest {

    private static final String IREMOCON_HOST_NAME = "192.168.11.50";

    @Test
    public void testCheckConnection() throws Exception {
        String commandName = IRemoconClient.COMMAND_CHECK_CONNECTION;
        String expected = "ok";

        IRemoconClient iRemoconClient = new IRemoconClient(IREMOCON_HOST_NAME);
        String actual;
        try {
            iRemoconClient.connect();
            actual = iRemoconClient.sendCommand(commandName);
        } finally {
            iRemoconClient.disconnect();
        }

        assertThat(actual, is(expected));
    }

    @Test
    public void testSendIr() throws Exception {
        String commandName = IRemoconClient.COMMAND_SEND_IR;
        String parameter = "1";
        String expected = "is;ok";

        IRemoconClient iRemoconClient = new IRemoconClient(IREMOCON_HOST_NAME);
        String actual;
        try {
            iRemoconClient.connect();
            actual = iRemoconClient.sendCommand(commandName, parameter);
        } finally {
            iRemoconClient.disconnect();
        }

        assertThat(actual, is(expected));
    }

}
