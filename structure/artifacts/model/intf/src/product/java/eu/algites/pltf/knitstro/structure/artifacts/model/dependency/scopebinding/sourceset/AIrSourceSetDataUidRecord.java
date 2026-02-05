package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset;

import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.FIRST_UID_SPECIFIC_PART_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.NAMESPACE_UID_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.ORIGIN_UID_POSITION;

import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Parsed representation of the Source Set UID.
 *
 * @param uid uid (must be not empty)
 * @param origin origin (builtin/custom)
 * @param namespace namespace segment (empty for builtin, non-empty for custom)
 * @param sourceSetCode groupCode (must be not empty)
 * @author linhart1
 */
public record AIrSourceSetDataUidRecord(
		String uid,
		AInUidEnumDataOrigin origin,
		String namespace,
		String sourceSetCode
) implements AIiSourceSetDataUidRecord {

	static {
		AIiSourceSetDataUidRecord.staticValidation();
	}

	/**
	 * Factory of the record from the uid parts.
	 * @return the factory
	 */
	public static BiFunction<String, List<String>, AIrSourceSetDataUidRecord> getUidRecordFactory() {
		return (aUid, aParts) -> {
			AInUidEnumDataOrigin locOrigin = AInUidEnumDataOrigin.getByCodeOrThrow(aParts.get(ORIGIN_UID_POSITION));
			return new AIrSourceSetDataUidRecord(aUid,
					locOrigin,
					aParts.get(NAMESPACE_UID_POSITION),
					aParts.get(FIRST_UID_SPECIFIC_PART_POSITION)
			);
		};
	}


}
