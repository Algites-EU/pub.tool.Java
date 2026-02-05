package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_SCOPE_BINDING_SET_INHERITANCE_MODE;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_PURPOSE;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AIiScopeBinding;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInBuiltinLocalUsageMode}
 * </p>
 * <p>
 * Description: Defines the modes of the local inclusion of the properties
 *    (like {@link AIiScopeBinding#getSourceSets()},
 *    {@link AIiScopeBinding#getPurposes()}
 *    and
 *    {@link AIiScopeBinding#getExclusions()}
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 18:14
 */
public enum AInBuiltinLocalUsageMode implements AIiLocalUsageModeData {
	DISABLED("disabled"),
	WITH_TRANSITIVES("with-transitives"),
	WITHOUT_TRANSITIVES("without-transitives"),
	;

	private final String code;
	private final String uid;

	AInBuiltinLocalUsageMode(final String aCode) {
		code = aCode;
		uid = AIsUidEnumDataUtils.createBuiltinUid(
				List.of(code),
				AIiLocalUsageModeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA);
		AIcUidEnumDataRegistry.getInstance().registerData(true, true, true, this);
	}

	@Override
	public String code() {
		return code;
	}


	@Override
	public AIiLocalUsageModeDataType getDataType() {
		return (AIiLocalUsageModeDataType) DEPENDENCY_SCOPE_BINDING_SET_INHERITANCE_MODE.getDataType();
	}

	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AInUidEnumDataOrigin origin() {
		return BUILTIN;
	}

	@Override
	public String namespace() {
		return "";
	}

	@Override
	public String modeCode() {
		return code();
	}

	@Override
	public AIiLocalUsageModeDataUidRecord getDataRecord() {
		return this;
	}

	/**
	 * Gets the {@link AInBuiltinLocalUsageMode} by its properties.
	 *
	 * @param aOrigin the origin
	 * @param aNamespace the namespace
	 * @param aCode the code (last UID component)
	 * @return the found code or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
	 *                                  or {@link #getByUidOrThrow(String)} throws an exception
	 */
	public static AInBuiltinLocalUsageMode getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN) {
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', local-inclusion-mode-id='" + aCode + "'");
		}
		return getByUidOrThrow(createBuiltinUid(List.of(aCode), AIiLocalUsageModeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInBuiltinLocalUsageMode} by its UID.
	 *
	 * @param aUid UID
	 * @return the found item or throws an exception if not found
	 * @throws IllegalArgumentException if the UID is invalid or not found among enum values
	 */
	public static AInBuiltinLocalUsageMode getByUidOrThrow(final String aUid) throws IllegalArgumentException {
		return findByUid(aUid)
				.orElseThrow(() -> new IllegalArgumentException("Illegal modeUid: '" + aUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-UID.
	 *
	 * @param aUid UID, for which the built-in item has to be searched for
	 * @return the found built-in item or empty optional, if no built-in item was found
	 */
	public static Optional<AInBuiltinLocalUsageMode> findByUid(final String aUid) {
		if (aUid == null || aUid.isBlank()) {
			return Optional.empty();
		}
		AIiLocalUsageModeDataUidRecord locParsedRecord = (AIiLocalUsageModeDataUidRecord) parseUid(
				DEPENDENCY_PURPOSE.getDataType(), aUid);
		if (locParsedRecord.origin() != BUILTIN) {
			return Optional.empty();
		}
		return Stream.of(values())
				.filter(locItem -> locItem.code().equals(locParsedRecord.code()))
				.findAny();
	}
	
}
