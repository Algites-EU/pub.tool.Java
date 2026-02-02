package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Default immutable implementation of {@link AIiVersionScheme}.
 *
 * <p>
 * A scheme groups structures for unique canonical parsing and validation.
 * This implementation currently focuses on:
 * </p>
 * <ul>
 *   <li>storing scheme UID and UID data type</li>
 *   <li>holding a set of known structures</li>
 *   <li>basic static validation (unique structure codes, each structure validates)</li>
 * </ul>
 *
 * <p>
 * Transition/engine logic is intentionally outside of this class.
 * </p>
 */
public final class AIcVersionScheme implements AIiVersionScheme {

	private final AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> dataType;
	private final String uid;
	private final List<AIiVersionStructure> structures;

	/**
	 * Creates a version scheme.
	 *
	 * @param aDataType scheme UID data type
	 * @param aUid scheme UID
	 * @param aStructures structures in this scheme
	 */
	public AIcVersionScheme(
			final AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> aDataType,
			final String aUid,
			final List<AIiVersionStructure> aStructures) {

		dataType = Objects.requireNonNull(aDataType, "aDataType");

		String locUid = Objects.requireNonNull(aUid, "aUid").trim();
		if (locUid.isEmpty()) {
			throw new IllegalArgumentException("Scheme UID must not be blank.");
		}
		uid = locUid;

		List<AIiVersionStructure> locStructures = aStructures == null
				? Collections.emptyList()
				: aStructures;
		structures = Collections.unmodifiableList(new ArrayList<>(locStructures));
	}

	@Override
	public AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> getDataType() {
		return dataType;
	}

	@Override
	public String uid() {
		return uid;
	}

	@Override
	public List<AIiVersionStructure> structures() {
		return structures;
	}

	@Override
	public Optional<AIiVersionStructure> findStructureByCode(final String aStructureCode) {
		if (aStructureCode == null) {
			return Optional.empty();
		}

		for (AIiVersionStructure locStructure : structures) {
			if (locStructure != null && aStructureCode.equals(locStructure.code())) {
				return Optional.of(locStructure);
			}
		}

		return Optional.empty();
	}

	@Override
	public void validate() {
		validateUniqueStructureCodes();
		validateStructures();
	}

	private void validateUniqueStructureCodes() {
		Set<String> locCodes = new LinkedHashSet<>();
		for (AIiVersionStructure locStructure : structures) {
			if (locStructure == null) {
				throw new AIxVersionException(
						() -> "Scheme contains null structure. schemeUid=" + uid,
						AInStringOutputMode.USER
				);
			}

			String locCode = locStructure.code();
			if (locCode == null || locCode.trim().isEmpty()) {
				throw new AIxVersionException(
						() -> "Scheme contains structure with blank code. schemeUid=" + uid,
						AInStringOutputMode.USER
				);
			}

			if (!locCodes.add(locCode)) {
				throw new AIxVersionException(
						() -> "Duplicate structure code in scheme. schemeUid=" + uid + ", structureCode=" + locCode,
						AInStringOutputMode.USER
				);
			}
		}
	}

	private void validateStructures() {
		for (AIiVersionStructure locStructure : structures) {
			try {
				locStructure.validate();
			} catch (RuntimeException locEx) {
				throw new AIxVersionException(
						() -> "Structure validation failed. schemeUid=" + uid + ", structureCode=" + locStructure.code(),
						locEx,
						AInStringOutputMode.USER
				);
			}
		}
	}

}
