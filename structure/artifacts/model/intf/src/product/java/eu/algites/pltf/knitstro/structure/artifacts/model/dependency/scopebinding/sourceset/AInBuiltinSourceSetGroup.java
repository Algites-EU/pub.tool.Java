package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AInBuiltinSourceSet.MAIN;
import static eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AInBuiltinSourceSet.TEST;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_SOURCE_SET_GROUP;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInBuiltinSourceSetGroup}
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
public enum AInBuiltinSourceSetGroup implements
		AIiSourceSetGroupData,
		AIiSourceSetGroupDataUidRecord {
	MAIN_ONLY("mainOnly", true, false, Set.of(MAIN)),
	TEST_ONLY("testOnly", false, true, Set.of(TEST)),
	MAIN_AND_TEST("mainAndTest", true, true, Set.of(MAIN, TEST));

	private final String groupCode;
	private final String uid;
	private final boolean mainIncluded;

	private final boolean testIncluded;
	private final Map<String, AIiSourceSetData> sourceSets;

	AInBuiltinSourceSetGroup(final String aGroupCode, final boolean aMainIncluded, boolean aTestIncluded,
			final Collection<AIiSourceSetData> aSourceSets) {
		groupCode = aGroupCode;
		mainIncluded = aMainIncluded;
		testIncluded = aTestIncluded;
		sourceSets = aSourceSets.stream().collect(Collectors.toMap(AIiUidEnumData::uid, locItem -> locItem));
		uid = AIsUidEnumDataUtils.createBuiltinUid(
				List.of(groupCode),
				AIiSourceSetGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA);
		AIcUidEnumDataRegistry.getInstance().registerData(true, true, true, this);
	}

	/**
	 * @return the groupCode
	 */
	@JsonValue
	@Override
	public String groupCode() {
		return groupCode;
	}

	/**
	 * @return the mainIncluded
	 */
	public boolean isMainIncluded() {
		return mainIncluded;
	}

	/**
	 * @return the testIncluded
	 */
	public boolean isTestIncluded() {
		return testIncluded;
	}

	@Override
	public Map<String, AIiSourceSetData> sourceSets() {
		return sourceSets;
	}

	@Override
	public AIiSourceSetGroupDataType getDataType() {
		return (AIiSourceSetGroupDataType) DEPENDENCY_SOURCE_SET_GROUP.getDataType();
	}

	/**
	 * @return the uid
	 */
	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AIiSourceSetGroupDataUidRecord getDataRecord() {
		return this;
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
	 * @param aCode Code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInBuiltinSourceSetGroup getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInBuiltinSourceSetGroup value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown sourceSet: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInBuiltinSourceSetGroup findByCode(final String aCode) {
		for (AInBuiltinSourceSetGroup locValue : values()) {
			if (locValue.groupCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}

	/**
	 * Gets the {@link AInBuiltinSourceSetGroup} by its properties.
	 *
	 * @param aOrigin the origin 
	 * @param aNamespace the namespace
	 * @param aSourceSetsCode the source Sets Code (last UID component)
	 * @return the found item or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
	 *                                  or {@link #getByUidOrThrow(String)} throws an exception
	 */
	public static AInBuiltinSourceSetGroup getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aSourceSetsCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN) {
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', source-set-group-id='" + aSourceSetsCode + "'");
		}
		return getByUidOrThrow(createBuiltinUid(List.of(aSourceSetsCode), AIiSourceSetGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInBuiltinSourceSetGroup} by its UID.
	 *
	 * @param aUid UID
	 * @return the found item or throws an exception if not found
	 * @throws IllegalArgumentException if the UID is invalid or not found among enum values
	 */
	public static AInBuiltinSourceSetGroup getByUidOrThrow(final String aUid) throws IllegalArgumentException {
		return findByUid(aUid)
				.orElseThrow(() -> new IllegalArgumentException("Illegal sourceSetGroupUid: '" + aUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-UID.
	 *
	 * @param aUid UID, for which the built-in item has to be searched for
	 * @return the found built-in item or empty optional, if no built-in item was found
	 */
	public static Optional<AInBuiltinSourceSetGroup> findByUid(final String aUid) {
		if (aUid == null || aUid.isBlank()) {
			return Optional.empty();
		}
		AIiSourceSetGroupDataUidRecord locParsedRecord = (AIiSourceSetGroupDataUidRecord) parseUid(
				DEPENDENCY_SOURCE_SET_GROUP.getDataType(), aUid);
		if (locParsedRecord.origin() != BUILTIN) {
			return Optional.empty();
		}
		return Stream.of(values())
				.filter(locItem -> locItem.groupCode().equals(locParsedRecord.groupCode()))
				.findAny();
	}
}
