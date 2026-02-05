package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AInBuiltinPurpose.COMPILATION;
import static eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AInBuiltinPurpose.RUNTIME;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_PURPOSE_GROUP;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
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
 * Title: {@link AInBuiltinPurposeGroup}
 * </p>
 * <p>
 * Description: Contains the definition of the purposes
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
public enum AInBuiltinPurposeGroup implements
		AIiPurposeGroupData,
		AIiPurposeGroupDataUidRecord {
	RUNTIME_ONLY("runtime-only", Set.of(RUNTIME)),
	COMPILATION_AND_RUNTIME("compilation-and-runtime", Set.of(COMPILATION, RUNTIME)),
	ALL_BUILTIN("all-builtin", Set.of(AInBuiltinPurpose.values()));

	private final String groupCode;
	private final String uid;

	private final Map<String, AIiPurposeData> purposes;

	AInBuiltinPurposeGroup(final String aPurposesCode,
			final Collection<AIiPurposeData> aPurposes) {
		groupCode = aPurposesCode;
		purposes = aPurposes.stream().collect(Collectors.toMap(
				(locItem) -> locItem.getDataRecord().uid(),
				(locItem) -> locItem));
		uid = AIsUidEnumDataUtils.createBuiltinUid(
				List.of(groupCode),
				AIiPurposeGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA);
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

	@Override
	public Map<String, AIiPurposeData> purposes() {
		return purposes;
	}

	@Override
	public AIiPurposeGroupDataType getDataType() {
		return (AIiPurposeGroupDataType) DEPENDENCY_PURPOSE_GROUP.getDataType();
	}

	/**
	 * @return the uid
	 */
	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AIiPurposeGroupDataUidRecord getDataRecord() {
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
	 * @param aCode code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInBuiltinPurposeGroup getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInBuiltinPurposeGroup value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown sourceSet: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInBuiltinPurposeGroup findByCode(final String aCode) {
		for (AInBuiltinPurposeGroup locValue : values()) {
			if (locValue.groupCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}

	/**
	 * Gets the {@link AInBuiltinPurposeGroup} by its properties.
	 *
	 * @param aOrigin the origin 
	 * @param aNamespace the namespace
	 * @param aPurposeGroupCode the purpose group Code (last UID component)
	 * @return the found purpose group or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
	 *                                  or {@link #getByUidOrThrow(String)} throws an exception
	 */
	public static AInBuiltinPurposeGroup getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aPurposeGroupCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN) {
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', purpose-group-id='" + aPurposeGroupCode + "'");
		}
		return getByUidOrThrow(createBuiltinUid(List.of(aPurposeGroupCode), AIiPurposeGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInBuiltinPurposeGroup} by its purpose group UID.
	 *
	 * @param aUid purpose group UID
	 * @return the found purpose group or throws an exception if not found
	 * @throws IllegalArgumentException if the UID is invalid or not found among enum values
	 */
	public static AInBuiltinPurposeGroup getByUidOrThrow(final String aUid) throws IllegalArgumentException {
		return findByUid(aUid)
				.orElseThrow(() -> new IllegalArgumentException("Illegal purposeGroupUid: '" + aUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-UID.
	 *
	 * @param aUid UID, for which the built-in item has to be searched for
	 * @return the found built-in item or empty optional, if no built-in item was found
	 */
	public static Optional<AInBuiltinPurposeGroup> findByUid(final String aUid) {
		if (aUid == null || aUid.isBlank()) {
			return Optional.empty();
		}
		AIiPurposeGroupDataUidRecord locParsedRecord = (AIiPurposeGroupDataUidRecord) parseUid(
				DEPENDENCY_PURPOSE_GROUP.getDataType(), aUid);
		if (locParsedRecord.origin() != BUILTIN) {
			return Optional.empty();
		}
		return Stream.of(values())
				.filter(locItem -> locItem.groupCode().equals(locParsedRecord.groupCode()))
				.findAny();
	}
}
