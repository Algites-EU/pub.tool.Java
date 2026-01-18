package eu.algites.tool.devops.build.model.loader.loading;

/**
 * Validator invoked before applying a value initialization to {@link AIcLateInitializationMap}.
 * <p>
 * Implementations may throw {@link AIxLateInitializationMapException} to reject the operation.
 *
 * @param <K> key type
 * @param <V> value type
 */
@FunctionalInterface
public interface AIiLateInitializationMapInsertValidator<K, V> {

	/**
	 * Validates whether a value can be inserted (initialized or replaced) for the given key/slot.
	 *
	 * @param aMap owning map
	 * @param aKey key
	 * @param aSlot current slot (always non-null)
	 * @param aNewValue new value requested to be applied
	 * @return if true, the value is allowed to be inserted, if false, the insert should be not executed
	 * @throws AIxLateInitializationMapException if the validation fails with error - somethig what should not happen.
	 */
	boolean validateInsert(
			AIcLateInitializationMap<K, V> aMap,
			K aKey,
			AIcLateInitializationSlot<V> aSlot,
			V aNewValue
	) throws AIxLateInitializationMapException;
}
