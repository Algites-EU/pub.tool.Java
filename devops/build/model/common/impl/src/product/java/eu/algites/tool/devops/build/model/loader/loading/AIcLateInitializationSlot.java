package eu.algites.tool.devops.build.model.loader.loading;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a potentially-late-initialized value slot.
 *
 * @param <V> value type
 * @author linhart1
 */
public final class AIcLateInitializationSlot<V> {

	private V locValue;
	private boolean locInitialized;

	/**
	 * @return true if a value has been set
	 */
	public boolean isInitialized() {
		return locInitialized;
	}

	/**
	 * Returns an optional value.
	 *
	 * @return optional value
	 */
	public Optional<V> findValue() {
		return locInitialized ? Optional.of(locValue) : Optional.empty();
	}

	/**
	 * Returns the value or throws an exception with the provided message.
	 *
	 * @param aMessage message used if not initialized
	 * @return value
	 */
	public V getValueOrThrow(final String aMessage) {
		if (!locInitialized) {
			throw new AIxLateInitializationMapException(aMessage);
		}
		return locValue;
	}

	/**
	 * Sets the value and marks the slot initialized.
	 *
	 * @param aValue value
	 */
	public void setValue(final V aValue) {
		Objects.requireNonNull(aValue, "aValue must not be null");
		locValue = aValue;
		locInitialized = true;
	}
}
