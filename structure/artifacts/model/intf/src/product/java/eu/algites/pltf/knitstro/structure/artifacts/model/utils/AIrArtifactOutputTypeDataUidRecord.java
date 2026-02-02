package eu.algites.pltf.knitstro.structure.artifacts.model.utils;

import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.FIRST_UID_SPECIFIC_PART_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.NAMESPACE_UID_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.ORIGIN_UID_POSITION;

import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.AIiArtifactOutputTypeDataUidRecord;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Parsed representation of an OutputType UID.
 *
 * @param uid uid (must be not empty)
 * @param origin origin (builtin/custom)
 * @param namespace namespace segment (empty for builtin, non-empty for custom)
 * @param classifier classifier segment (may be empty)
 * @param packagingFileTypeCode file type segment (non-empty)
 * @author linhart1
 */
public record AIrArtifactOutputTypeDataUidRecord(
		String uid,
		AInUidEnumDataOrigin origin,
		String namespace,
		String classifier,
		String packagingFileTypeCode
) implements AIiArtifactOutputTypeDataUidRecord {

	static {
		AIiArtifactOutputTypeDataUidRecord.staticValidation();
	}

	/**
	 * Factory of the record from the uid parts.
	 * @return the factory
	 */
	public static BiFunction<String, List<String>, AIrArtifactOutputTypeDataUidRecord> getUidRecordFactory() {
		return (aUid, aParts) -> {
			AInUidEnumDataOrigin locOrigin = AInUidEnumDataOrigin.getByCodeOrThrow(aParts.get(ORIGIN_UID_POSITION));
			return new AIrArtifactOutputTypeDataUidRecord(aUid,
					locOrigin,
					aParts.get(NAMESPACE_UID_POSITION),
					aParts.get(FIRST_UID_SPECIFIC_PART_POSITION),
					aParts.get(FIRST_UID_SPECIFIC_PART_POSITION + 1)
			);
		};
	}

}
