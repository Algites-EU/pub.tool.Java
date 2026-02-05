package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.FIRST_UID_SPECIFIC_PART_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.NAMESPACE_UID_POSITION;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.ORIGIN_UID_POSITION;

import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Parsed representation of an Template UID.
 *
 * @param uid uid (must be not empty)
 * @param origin origin (builtin/custom)
 * @param namespace namespace segment (empty for builtin, non-empty for custom)
 * @param templateCode templateCode (must be not empty)
 * @author linhart1
 */
public record AIrTemplateDataUidRecord(
		String uid,
		AInUidEnumDataOrigin origin,
		String namespace,
		String templateCode
) implements AIiTemplateDataUidRecord {

	static {
		AIiTemplateDataUidRecord.staticValidation();
	}

	/**
	 * Factory of the record from the uid parts.
	 * @return the factory
	 */
	public static BiFunction<String, List<String>, AIrTemplateDataUidRecord> getUidRecordFactory() {
		return (aUid, aParts) -> {
			AInUidEnumDataOrigin locOrigin = AInUidEnumDataOrigin.getByCodeOrThrow(aParts.get(ORIGIN_UID_POSITION));
			return new AIrTemplateDataUidRecord(aUid,
					locOrigin,
					aParts.get(NAMESPACE_UID_POSITION),
					aParts.get(FIRST_UID_SPECIFIC_PART_POSITION)
			);
		};
	}
}
