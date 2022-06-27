package de.aesettlingen.cardgame.commons.event;

import java.util.Objects;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public abstract class CancelableEvent extends Event {

    private boolean canceled;

    public CancelableEvent(boolean canceled) {
        this.canceled = canceled;
    }

    public CancelableEvent() {
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancelableEvent that = (CancelableEvent) o;
        return canceled == that.canceled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(canceled);
    }
}
