package example.kafka;

import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class UserEvent {

    private final int userId;
    private final String firstName;
    private final String lastName;
    private final Instant eventTime;

    public UserEvent(int userId,
                     @NonNull String firstName,
                     @NonNull String lastName) {
        this.userId = userId;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.eventTime = Instant.now();
    }

    public int getUserId() {
        return userId;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public Instant getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
