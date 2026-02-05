package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_SOURCE_SET;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInBuiltinSourceSet}
 * </p>
 * <p>
 * Description: Contains the definition of the source sets
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
public enum AInBuiltinSourceSet
		implements
		AIiSourceSetData {
	MAIN("main", true, false),
	TEST("test", false, true),
	;

	private final String sourceSetCode;
	private final boolean main;

	private final boolean test;
	private final String uid;

	AInBuiltinSourceSet(final String aSourceSetCode, final boolean aMain, boolean aTest) {
		sourceSetCode = aSourceSetCode;
		main = aMain;
		test = aTest;
		uid = AIsUidEnumDataUtils.createBuiltinUid(
				List.of(sourceSetCode),
				AIiSourceSetDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA);
		AIcUidEnumDataRegistry.getInstance().registerData(true, true, true, this);
	}

	/**
	 * @return the purposeCode
	 */
	@JsonValue
	@Override
	public String sourceSetCode() {
		return sourceSetCode;
	}

	/**
	 * @return the uid
	 */
	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AIiSourceSetDataUidRecord getDataRecord() {
		return this;
	}

	/**
	 * @return the mainIncluded
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * @return the testIncluded
	 */
	public boolean isTest() {
		return test;
	}

	@Override
	public AIiSourceSetDataType getDataType() {
		return (AIiSourceSetDataType) DEPENDENCY_SOURCE_SET.getDataType();
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
	 * @param aCode code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInBuiltinSourceSet getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInBuiltinSourceSet value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown sourceSet: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInBuiltinSourceSet findByCode(final String aCode) {
		for (AInBuiltinSourceSet locValue : values()) {
			if (locValue.sourceSetCode.equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}

	/**
	 * Gets the {@link AInBuiltinSourceSet} by its properties.
	 *
	 * @param aOrigin the origin 
	 * @param aNamespace the namespace
	 * @param aSourceSetsCode the source Sets Code (last UID component)
	 * @return the found source set or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
	 *                                  or {@link #getByUidOrThrow(String)} throws an exception
	 */
	public static AInBuiltinSourceSet getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aSourceSetsCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN) {
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', source-set-id='" + aSourceSetsCode + "'");
		}
		return getByUidOrThrow(createBuiltinUid(List.of(aSourceSetsCode), AIiSourceSetDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInBuiltinSourceSet} by its source set UID.
	 *
	 * @param aUid source set UID
	 * @return the found source set or throws an exception if not found
	 * @throws IllegalArgumentException if the UID is invalid or not found among enum values
	 */
	public static AInBuiltinSourceSet getByUidOrThrow(final String aUid) throws IllegalArgumentException {
		return findByUid(aUid)
				.orElseThrow(() -> new IllegalArgumentException("Illegal source set Uid: '" + aUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-UID.
	 *
	 * @param aUid UID, for which the built-in item has to be searched for
	 * @return the found built-in item or empty optional, if no built-in item was found
	 */
	public static Optional<AInBuiltinSourceSet> findByUid(final String aUid) {
		if (aUid == null || aUid.isBlank()) {
			return Optional.empty();
		}
		AIiSourceSetDataUidRecord locParsedRecord = (AIiSourceSetDataUidRecord) parseUid(
				DEPENDENCY_SOURCE_SET.getDataType(), aUid);
		if (locParsedRecord.origin() != BUILTIN) {
			return Optional.empty();
		}
		return Stream.of(values())
				.filter(locItem -> locItem.sourceSetCode().equals(locParsedRecord.sourceSetCode()))
				.findAny();
	}
}
