package si.fri.prpoVaje03.storitve;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private static final Logger LOGGER = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    private int numCalls = 0;

    public void call() {
        numCalls++;
        LOGGER.log(Level.INFO, "Number of method calls: " + numCalls);
    }

    public int getNumCalls() {
        return numCalls;
    }
}
