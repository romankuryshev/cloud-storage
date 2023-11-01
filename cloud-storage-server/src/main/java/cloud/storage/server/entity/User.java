package cloud.storage.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class User {
    public static final int DEFAULT_ATTEMPTS_COUNT = 3;

    private String username;
    private String password;
    private boolean blocked;
    private int attemptsCount;

    /**
     * Метод уменьшает оставшееся количество попыток на аутентификацию.
     * Если количество попыток становится равным 0, то пользователь блокируется и больше не имеет доступа к системе.
     */
    public void reduceAttemptsCount() {
        attemptsCount--;
        if (attemptsCount == 0) {
            blocked = true;
        }
    }
}
