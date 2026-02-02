package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_PURPOSE;

import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInBuiltinPurpose}
 * </p>
 * <p>
 * Description: Contains the definition of the purpose for the dependencies
 *    on which the operations like dependencies etc. are being applied.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 7:47
 */
public enum AInBuiltinPurpose implements
		AIiPurposeData, AIiPurposeDataUidRecord {
	SOURCE_PROCESSOR("source-processor"),
	COMPILATION("compilation"),
	COMPILATION_POSTPROCESSING("compilation-postprocessing"),
	RUNTIME("runtime"),
  ;
	private final String purposeCode;
	private final String uid;

	AInBuiltinPurpose(final String aPurposeCode) {
		purposeCode = aPurposeCode;
		uid = AIsUidEnumDataUtils.createBuiltinUid(
				List.of(purposeCode),
				AIiPurposeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA);
	}

	@Override
	public AInUidEnumDataOrigin origin() {
		return BUILTIN;
	}

	@Override
	public String namespace() {
		return "";
	}
	/**
	 * @return the purposeCode
	 */
	@JsonValue
	@Override
	public String purposeCode() {
		return purposeCode;
	}

	@Override
	public AIiPurposeDataType getDataType() {
		return (AIiPurposeDataType) DEPENDENCY_PURPOSE.getDataType();
	}

	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AIiPurposeDataUidRecord getDataRecord() {
		return this;
	}

	/**
	 * Gets the {@link AInBuiltinPurpose} by its properties.
	 *
	 * @param aOrigin the origin
	 * @param aNamespace the namespace
	 * @param aPurposeCode the purpose Code (last UID component)
	 * @return the found purpose or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
	 *                                  or {@link #getByUidOrThrow(String)} throws an exception
	 */
	public static AInBuiltinPurpose getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aPurposeCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN) {
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', purpose-id='" + aPurposeCode + "'");
		}
		return getByUidOrThrow(createBuiltinUid(List.of(aPurposeCode), AIiPurposeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInBuiltinPurpose} by its purpose UID.
	 *
	 * @param aUid purpose UID
	 * @return the found purpose or throws an exception if not found
	 * @throws IllegalArgumentException if the UID is invalid or not found among enum values
	 */
	public static AInBuiltinPurpose getByUidOrThrow(final String aUid) throws IllegalArgumentException {
		return findByUid(aUid)
				.orElseThrow(() -> new IllegalArgumentException("Illegal purposeUid: '" + aUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-UID.
	 *
	 * @param aUid UID, for which the built-in item has to be searched for
	 * @return the found built-in item or empty optional, if no built-in item was found
	 */
	public static Optional<AInBuiltinPurpose> findByUid(final String aUid) {
		if (aUid == null || aUid.isBlank()) {
			return Optional.empty();
		}
		AIiPurposeDataUidRecord locParsedPurpose = (AIiPurposeDataUidRecord) parseUid(DEPENDENCY_PURPOSE.getDataType(), aUid);
		if (locParsedPurpose.origin() != BUILTIN) {
			return Optional.empty();
		}
		return Stream.of(values())
				.filter(locPurpose -> locPurpose.purposeCode().equals(locParsedPurpose.purposeCode()))
				.findAny();
	}

}
