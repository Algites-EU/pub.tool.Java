package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.List;
import java.util.Optional;

/**
 * A version scheme groups a set of structures and transitions into a coherent state machine.
 * <p>
 * Persisted version state:
 * - A canonical version text (string) is stored externally (e.g., version-state.yml).
 * - Canonical parsing is expected to uniquely identify exactly one structure in this scheme.
 * </p>
 * <p>
 * Transitions:
 * - Transitions define allowed movement from one structure to another.
 * - The utils applies mutations to component values and re-normalizes using the target structure's canonical format.
 * </p>
 * @author linhart1
 */
public interface AIiVersionScheme extends AIiUidEnumData<
		AIiUidEnumDataRecord,
		AInUidEnumDataOrigin,
		AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>> {

	/**
	 * Gets all structures known to this scheme.
	 *
	 * @return structures
	 */
	List<AIiVersionStructure> structures();

	/**
	 * Finds a structure by its code.
	 *
	 * @param aStructureCode structure code
	 * @return optional structure
	 */
	Optional<AIiVersionStructure> findStructureByCode(String aStructureCode);

	/**
	 * Validates scheme consistency (static validation).
	 *
	 * Implementations should validate:
	 * - unique structure codes,
	 * - unique transition codes,
	 * - transition endpoints exist (from/to structures),
	 * - each structure validates itself.
	 *
	 * Engine-level validation may also ensure cross-structure canonical parsing uniqueness.
	 *
	 * @throws eu.algites.pltf.knitstro.structure.version.AIxVersionException when invalid
	 */
	void validate();

}
