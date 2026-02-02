package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.FIRST_UID_SPECIFIC_PART_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.NAMESPACE_UID_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.ORIGIN_UID_POSITION;

import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Parsed representation of the local inclusion modes UID.
 *
 * @param uid uid (must be not empty)
 * @param origin origin (builtin/custom)
 * @param namespace namespace segment (empty for builtin, non-empty for custom)
 * @param modeCode modeCode (must be not empty)
 * @author linhart1
 */
public record AIrLocalUsageModeDataUidRecord(
		String uid,
		AInUidEnumDataOrigin origin,
		String namespace,
		String modeCode
) implements AIiLocalUsageModeDataUidRecord {

	static {
		AIiLocalUsageModeDataUidRecord.staticValidation();
	}

	/**
	 * Factory of the record from the uid parts.
	 * @return the factory
	 */
	public static BiFunction<String, List<String>, AIrLocalUsageModeDataUidRecord> getUidRecordFactory() {
		return (aUid, aParts) -> {
			AInUidEnumDataOrigin locOrigin = AInUidEnumDataOrigin.getByCodeOrThrow(aParts.get(ORIGIN_UID_POSITION));
			return new AIrLocalUsageModeDataUidRecord(aUid,
					locOrigin,
					aParts.get(NAMESPACE_UID_POSITION),
					aParts.get(FIRST_UID_SPECIFIC_PART_POSITION)
			);
		};
	}

}
