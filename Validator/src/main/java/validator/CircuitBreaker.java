package dit355.validator;

import java.time.Duration;
import java.time.Instant;

public class CircuitBreaker {
    private static final int INTERVAL_MILLIS = 1000;
    private static final int ALLOWED_NUMBER_OF_REQUESTS = 16;

    private io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;
    private int currentCount = 0;
    private Instant last;

    public CircuitBreaker() {
        circuitBreaker = io.github.resilience4j.circuitbreaker.CircuitBreaker.ofDefaults("validator");
        last = Instant.now();
    }

    public boolean isClosed() {
        return this.circuitBreaker.getState().equals(io.github.resilience4j.circuitbreaker.CircuitBreaker.State.CLOSED);
    }

    public void registerRequest() {
        currentCount++;

        if (Duration.between(last, Instant.now()).toMillis() > INTERVAL_MILLIS) {
            reset();
            last = Instant.now();
        }

        if (currentCount > (ALLOWED_NUMBER_OF_REQUESTS-1) && isClosed()) {
            circuitBreaker.transitionToOpenState();
        }
    }

    private void reset() {
        currentCount = 0;
        if (! circuitBreaker.getState().equals(io.github.resilience4j.circuitbreaker.CircuitBreaker.State.CLOSED)) {
            circuitBreaker.transitionToClosedState();
        }
    }
}
