package it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.kuronicle.iremocon4j.IRemoconClientImpl;
import net.kuronicle.iremocon4j.IRemoconCommand;

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
        String expected = "ok";

        IRemoconClientImpl iRemoconClient = new IRemoconClientImpl(IREMOCON_HOST_NAME);
        String actual;
        try {
            iRemoconClient.connect();
            actual = iRemoconClient.sendCommand(IRemoconCommand.CHECK_CONNECTION);
        } finally {
            iRemoconClient.disconnect();
        }

        assertThat(actual, is(expected));
    }

    @Test
    public void testSendIr() throws Exception {
        String parameter = "1";
        String expected = "is;ok";

        IRemoconClientImpl iRemoconClient = new IRemoconClientImpl(IREMOCON_HOST_NAME);
        String actual;
        try {
            iRemoconClient.connect();
            actual = iRemoconClient.sendCommand(IRemoconCommand.SEND_IR, parameter);
        } finally {
            iRemoconClient.disconnect();
        }

        assertThat(actual, is(expected));
    }

}
