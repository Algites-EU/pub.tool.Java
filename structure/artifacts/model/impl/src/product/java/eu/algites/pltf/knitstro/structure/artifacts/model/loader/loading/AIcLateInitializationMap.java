package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Title: {@link AIcLateInitializationMap}
 * </p>
 * <p>
 * Description: <br/>
 * A map-like structure that preserves key insertion order and supports "late initialization" of values.
 * <p>
 * Typical workflow:
 * <ol>
 *   <li>Declare keys in the desired order.</li>
 *   <li>Initialize values later (possibly in a different phase).</li>
 *   <li>Resolve values via {@link AIcRuntimeReference}.</li>
 * </ol>
 *
 * @param <K> key type (e.g., renderPattern ID, artifact ID)
 * @param <V> value type (e.g., renderPattern object, artifact object)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 7:39
 */
public abstract class AIcLateInitializationMap<K, V> implements Iterable<K> {

	private final LinkedHashMap<K, AIcLateInitializationSlot<V>> locSlotsByKey;
	private final AIiLateInitializationMapInsertValidator<K, V> locInsertValidator;

	/**
	 * Creates a map with the default validator (disallows re-initialization).
	 */
	public AIcLateInitializationMap() {
		this(new AIcDefaultLateInitializationMapInsertValidator<>());
	}

	/**
	 * Creates a map with a custom validator that decides whether insert/replace is allowed.
	 *
	 * @param aInsertValidator validator invoked on each initialization attempt
	 */
	public AIcLateInitializationMap(final AIiLateInitializationMapInsertValidator<K, V> aInsertValidator) {
		locSlotsByKey = new LinkedHashMap<>();
		locInsertValidator = Objects.requireNonNull(aInsertValidator, "aInsertValidator must not be null");
	}

	/**
	 * Returns the key for a given value.
	 * @param aValue value
	 * @return key resolved from the value
	 */
	public abstract K computeKeyFor(V aValue);

	/**
	 * Declares a key while preserving insertion order.
	 * If the key already exists, this method is idempotent (no reordering).
	 *
	 * @param aKey key to declare
	 * @return a slot bound to the key
	 */
	public AIcLateInitializationSlot<V> declareKey(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		AIcLateInitializationSlot<V> locExistingSlot = locSlotsByKey.get(aKey);
		if (locExistingSlot != null) {
			return locExistingSlot;
		}
		AIcLateInitializationSlot<V> locNewSlot = new AIcLateInitializationSlot<>();
		locSlotsByKey.put(aKey, locNewSlot);
		return locNewSlot;
	}

	/**
	 * Initializes (or replaces) the value for a key.
	 * If the key was not declared before, it will be declared now (thus inserted at this moment in order).
	 * <p>
	 * A validator is invoked before the value is applied.
	 *
	 * @param aValue value to set
	 */
	public void initialize(final V aValue) {
		Objects.requireNonNull(aValue, "aValue must not be null");
		K locKey = computeKeyFor(aValue);
		Objects.requireNonNull(locKey, "aKey must not be null for value: " + aValue + ".");

		AIcLateInitializationSlot<V> locSlot = locSlotsByKey.computeIfAbsent(
				locKey, k -> new AIcLateInitializationSlot<>());

		if (locInsertValidator.validateInsert(this, locKey, locSlot, aValue))
			locSlot.setValue(aValue);
	}

	/**
	 * Returns a slot for a key (declares it if absent).
	 *
	 * @param aKey key
	 * @return slot
	 */
	public AIcLateInitializationSlot<V> getOrDeclareSlot(final K aKey) {
		return declareKey(aKey);
	}

	/**
	 * Returns an optional value for a key.
	 *
	 * @param aKey key
	 * @return optional initialized value
	 */
	public Optional<V> findValue(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		AIcLateInitializationSlot<V> locSlot = locSlotsByKey.get(aKey);
		if (locSlot == null) {
			return Optional.empty();
		}
		return locSlot.findValue();
	}

	/**
	 * Returns the initialized value or throws a map-specific exception.
	 *
	 * @param aKey key
	 * @return value
	 */
	public V getValueOrThrow(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		AIcLateInitializationSlot<V> locSlot = locSlotsByKey.get(aKey);
		if (locSlot == null) {
			throw new AIxLateInitializationMapException("Key not found in late initialization map: " + aKey);
		}
		return locSlot.getValueOrThrow("Value not initialized for key: " + aKey);
	}

	/**
	 * Returns whether the key exists (declared or implicitly created by initialize).
	 *
	 * @param aKey key
	 * @return true if present
	 */
	public boolean containsKey(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		return locSlotsByKey.containsKey(aKey);
	}

	/**
	 * Returns whether the value for a key is initialized.
	 *
	 * @param aKey key
	 * @return true if initialized
	 */
	public boolean isInitialized(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		AIcLateInitializationSlot<V> locSlot = locSlotsByKey.get(aKey);
		return locSlot != null && locSlot.isInitialized();
	}

	/**
	 * Returns an unmodifiable view of the internal key->slot map, preserving insertion order.
	 *
	 * @return unmodifiable ordered map
	 */
	public Map<K, AIcLateInitializationSlot<V>> viewSlotsByKey() {
		return Collections.unmodifiableMap(locSlotsByKey);
	}

	/**
	 * Copies all keys and initialized values from the given map into this map.
	 * <p>
	 * The iteration order of {@code aOtherMap} is preserved for keys that are not yet present in this map.
	 * Keys that already exist in this map keep their current position.
	 * <p>
	 * For each initialized slot in {@code aOtherMap}, this method attempts to initialize the corresponding
	 * slot in this map. The configured insert validator is applied.
	 * <p>
	 * If the target slot is already initialized with an equal value, the operation is treated as idempotent
	 * and is skipped.
	 *
	 * @param aOtherMap source map to copy from
	 */
	public void putAll(final AIcLateInitializationMap<K, V> aOtherMap) {
		Objects.requireNonNull(aOtherMap, "aOtherMap must not be null");

		for (Map.Entry<K, AIcLateInitializationSlot<V>> locEntry : aOtherMap.locSlotsByKey.entrySet()) {
			K locKey = locEntry.getKey();
			AIcLateInitializationSlot<V> locSourceSlot = locEntry.getValue();
			AIcLateInitializationSlot<V> locTargetSlot = declareKey(locKey);

			if (locSourceSlot == null || !locSourceSlot.isInitialized()) {
				continue;
			}

			V locSourceValue = locSourceSlot.getValueOrThrow(
					"Source value not initialized for key: " + locKey
			);

			if (locTargetSlot.isInitialized()) {
				V locExistingValue = locTargetSlot.getValueOrThrow(
						"Target value not initialized for key: " + locKey
				);
				if (Objects.equals(locExistingValue, locSourceValue)) {
					continue;
				}
			}

			if (locInsertValidator.validateInsert(this, locKey, locTargetSlot, locSourceValue)) {
				locTargetSlot.setValue(locSourceValue);
			}
		}
	}

	/**
	 * Returns a list of keys that are present in this map but whose slots are not initialized.
	 * The returned list preserves the map's key insertion order.
	 *
	 * @return unmodifiable list of keys with uninitialized slots
	 */
	public List<K> findKeysWithUninitializedSlots() {
		List<K> locKeys = new ArrayList<>();
		for (Map.Entry<K, AIcLateInitializationSlot<V>> locEntry : locSlotsByKey.entrySet()) {
			AIcLateInitializationSlot<V> locSlot = locEntry.getValue();
			if (locSlot == null || !locSlot.isInitialized()) {
				locKeys.add(locEntry.getKey());
			}
		}
		return Collections.unmodifiableList(locKeys);
	}

	/**
	 * Returns a key->slot map containing only slots that are not initialized.
	 * The returned map preserves the key insertion order.
	 *
	 * @return unmodifiable ordered map of uninitialized slots
	 */
	public Map<K, AIcLateInitializationSlot<V>> findUninitializedSlotsByKey() {
		LinkedHashMap<K, AIcLateInitializationSlot<V>> locResult = new LinkedHashMap<>();
		for (Map.Entry<K, AIcLateInitializationSlot<V>> locEntry : locSlotsByKey.entrySet()) {
			AIcLateInitializationSlot<V> locSlot = locEntry.getValue();
			if (locSlot == null || !locSlot.isInitialized()) {
				locResult.put(locEntry.getKey(), locSlot);
			}
		}
		return Collections.unmodifiableMap(locResult);
	}

	/**
	 * Creates a runtime reference bound to this map and the given key.
	 *
	 * @param aKey key
	 * @return runtime reference
	 */
	public AIcRuntimeReference<K, V> createRuntimeReference(final K aKey) {
		Objects.requireNonNull(aKey, "aKey must not be null");
		return new AIcRuntimeReference<>(this, aKey);
	}

	/**
	 * @return number of keys (declared or implicitly created)
	 */
	public int size() {
		return locSlotsByKey.size();
	}

	@Override
	public Iterator<K> iterator() {
		return Collections.unmodifiableSet(locSlotsByKey.keySet()).iterator();
	}
}

