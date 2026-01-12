package eu.algites.tool.build.model.utils;

import static eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledArtifact.ARTIFACT_CONFIG_FILE_NAME_WITHOUT_EXT;

import eu.algites.tool.build.model.artifact.intf.AInArtifactKind;
import eu.algites.tool.build.model.common.intf.AIcContainedArtifactLocalKey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: {@link AIsArtifactModelUtils}
 * </p>
 * <p>
 * Description: Utilities for the artifact handling
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 09.01.26 10:43
 */
public class AIsArtifactModelUtils {

	/** Allowed chars for groupId and artifactId: letters, digits, dot, underscore, hyphen. */
	public static final Pattern GROUP_ID_AND_ARTIFACT_ID_SEGMENT_PATTERN = Pattern.compile("^[A-Za-z0-9._-]+$");

	/**
	 * CoordinateId must be exactly {@code <groupId>:<artifactId>} using the same segment pattern
	 * and exactly one colon.
	 */
	public static final Pattern COORDINATE_ID_PATTERN =
			Pattern.compile("^([A-Za-z0-9._-]+):([A-Za-z0-9._-]+)$");

	public static final String UNSPECIFIED_VERSION_PLACEHOLDER = "---- UNSPECIFIED VERSION -----";

	private AIsArtifactModelUtils() {
	}

	/**
	 * Performs the resolution of the normalized coordinated Identification
	 * of an artifact.
	 * @param aGroupId groupId of the artifact
	 * @param aArtifactBaseId baseId of the artifact
	 * @return the normalized coordinated Identification of the artifact
	 */
	public static String toCoordinateId(String aGroupId, String aArtifactBaseId) {
		return aGroupId.trim() + ":" + aArtifactBaseId.trim();
	}

	/**
	 * Resolves the filename without extension from the given path
	 * @param aPath path to the file
	 * @return filename without extension
	 */
	public static String filenameWithoutExtension(Path aPath) {
		String name = aPath.getFileName().toString();
		int dot = name.lastIndexOf('.');
		return (dot > 0) ? name.substring(0, dot) : name;
	}

	/**
	 * Resolves the normalized form of the artificial artifact for the Single Core Artifacts
	 * @param aSingleCoreArtifactId artifactId or DTO ID of the artifact
	 * @param aArtificialArtifactKind kind of artificial artifact to be delivered
	 * @return normalized form of the artificial artifact
	 * @throws IllegalArgumentException if the passed argument for the artificial argument is null
	 *    or does not fulfill the condition {@link AInArtifactKind#isPotentialArtificialArtifact()}
	 */
	public static String resolveArtificialArtifactId(final String aSingleCoreArtifactId, final AInArtifactKind aArtificialArtifactKind)
			throws IllegalArgumentException {
		if (aArtificialArtifactKind == null || !aArtificialArtifactKind.isPotentialArtificialArtifact())
			throw new IllegalArgumentException("Artificial artifact kind must not be null and must be a potential artificial artifact. Passed value: "
					+ aArtificialArtifactKind + " does not fulfill this condition for the passed aSingleCoreArtifactId:" + aSingleCoreArtifactId);
		return aSingleCoreArtifactId + "-artificial-" + aArtificialArtifactKind.name();
	}

	/**
	 * For the given file name without extension and preferred extension in the give file folder resolves
	 * the supported configuration file according to the file extension and if such file does not exist,
	 * it tests also the other supported extensions and file types.
	 * @param aConfigFileWithoutExt name of the configuration file without extension
	 * @param aPreferredConfigFileExtension preferred configuration extension, which should be tested as the first one
	 * @param aConfigFileFolder folder where the configuration file has to be searched
	 * @param aPathChecker checker of the path resolved if it is a correct and possible path for a given method call.
	 *    If the checker fails, then it should throw the runtime exception, which should then capture the
	 *    caller of this method
	 * @return path to the existent configuration file
	 */
	public static Path resolveConfigFilePath(
			final String aConfigFileWithoutExt,
			final String aPreferredConfigFileExtension,
			final Path aConfigFileFolder,
			final Consumer<AIcContainedArtifactLocalKey> aPathChecker) {
		Path locContainedArtifactPath = aConfigFileFolder.resolve(
				aConfigFileWithoutExt + "." + aPreferredConfigFileExtension);
		aPathChecker.accept(locContainedArtifactPath);
		AtomicReference<List<String>> locTestedExtensions = new AtomicReference<>();
		while (locContainedArtifactPath != null && Files.notExists(locContainedArtifactPath)) {
			if (locTestedExtensions.get() == null) {
				locTestedExtensions.set(new ArrayList<>());
				locTestedExtensions.get().add(aPreferredConfigFileExtension);
			}
			String locNextExtensionToTest = null;
			for (AInConfigurationFileKind locSource : AInConfigurationFileKind.values()) {
				for (String locSourceExtension : locSource.getConfigurationFileExtensionPossibilities()	) {
					if (locTestedExtensions.get().contains(locSourceExtension))
						continue;
					locNextExtensionToTest = locSourceExtension;
					locTestedExtensions.get().add(locNextExtensionToTest);
					break;
				}
			}
			if (locNextExtensionToTest == null) {
				locContainedArtifactPath = null;
			}
			else {
				locContainedArtifactPath = aConfigFileFolder.resolve(
						ARTIFACT_CONFIG_FILE_NAME_WITHOUT_EXT + "." + locNextExtensionToTest);
			}
			aPathChecker.accept(locContainedArtifactPath);
		}
		return locContainedArtifactPath;
	}

	/**
	 * Validates consistency between {@code groupId}, {@code artifactId} and {@code coordinateId}.
	 *
	 * <ul>
	 *   <li>Validates non-null / non-blank inputs</li>
	 *   <li>Validates {@code groupId} and {@code artifactId} against {@link #GROUP_ID_AND_ARTIFACT_ID_SEGMENT_PATTERN}</li>
	 *   <li>Validates {@code coordinateId} format and allowed chars</li>
	 *   <li>Validates that {@code coordinateId == groupId + ":" + artifactId}</li>
	 * </ul>
	 *
	 * @param groupId group id (required)
	 * @param artifactId artifact base id (required)
	 * @param coordinateId combined id (required)
	 * @param ownerHumanReadable optional "owner" label used in error messages (e.g. "artifact", "dependencyRef")
	 * @throws IllegalArgumentException if validation fails (with user-friendly message)
	 */
	public static void validateCoordinateConsistency(
			String groupId,
			String artifactId,
			String coordinateId,
			String ownerHumanReadable
	) {
		final String owner = (ownerHumanReadable == null || ownerHumanReadable.isBlank())
				? "artifact coordinate"
				: ownerHumanReadable.trim();

		// Basic null checks with clear messages.
		if (groupId == null) {
			throw new IllegalArgumentException(owner + ": 'groupId' is missing (null).");
		}
		if (artifactId == null) {
			throw new IllegalArgumentException(owner + ": 'artifactId' is missing (null).");
		}
		if (coordinateId == null) {
			throw new IllegalArgumentException(owner + ": 'coordinateId' is missing (null).");
		}

		// Trim for consistent behaviour; you may prefer to hard-fail on leading/trailing spaces.
		final String g = groupId.trim();
		final String a = artifactId.trim();
		final String c = coordinateId.trim();

		if (g.isEmpty()) {
			throw new IllegalArgumentException(owner + ": 'groupId' is empty.");
		}
		if (a.isEmpty()) {
			throw new IllegalArgumentException(owner + ": 'artifactId' is empty.");
		}
		if (c.isEmpty()) {
			throw new IllegalArgumentException(owner + ": 'coordinateId' is empty.");
		}

		// Segment validation
		if (!GROUP_ID_AND_ARTIFACT_ID_SEGMENT_PATTERN.matcher(g).matches()) {
			throw new IllegalArgumentException(owner + ": invalid 'groupId' '" + groupId + "'. "
					+ "Allowed characters: letters, digits, '.', '_' and '-'.");
		}
		if (!GROUP_ID_AND_ARTIFACT_ID_SEGMENT_PATTERN.matcher(a).matches()) {
			throw new IllegalArgumentException(owner + ": invalid 'artifactId' '" + artifactId + "'. "
					+ "Allowed characters: letters, digits, '.', '_' and '-'.");
		}

		// coordinateId format + char validation + exactly one colon
		var m = COORDINATE_ID_PATTERN.matcher(c);
		if (!m.matches()) {
			String colonHint = (countChar(c, ':') == 0)
					? "Missing ':' separator."
					: (countChar(c, ':') > 1 ? "Too many ':' separators." : "Invalid characters detected.");
			throw new IllegalArgumentException(owner + ": invalid 'coordinateId' '" + coordinateId + "'. "
					+ "Expected format: '<groupId>:<artifactId>' with allowed characters [A-Za-z0-9._-]. "
					+ colonHint);
		}

		// Cross-check parsed parts vs provided parts
		final String cidGroup = m.group(1);
		final String cidArtifactBase = m.group(2);

		if (!Objects.equals(cidGroup, g) || !Objects.equals(cidArtifactBase, a)) {
			final String expected = g + ":" + a;
			throw new IllegalArgumentException(owner + ": inconsistent coordinates. "
					+ "Provided groupId/artifactId = '" + g + ":" + a + "', "
					+ "but coordinateId = '" + coordinateId + "'. "
					+ "Expected coordinateId = '" + expected + "'.");
		}
	}

	/**
	 * Optional helper: derive canonical coordinateId from groupId + artifactId
	 * after validating segments.
	 * @param aGroupId groupId of artifact
	 * @param aArtifactId artifactId of artifact
	 * @param aOwnerHumanReadableObjectDescription optional "owner" label used in error messages (e.g. "artifact", "dependencyRef")
	 * @return coordinateId of artifact - same result like if called #toCoordinateId(String, String)
	 *    but with validation throwing human readable error if the data are incorrect
	 */
	public static String toValidatedCoordinateId(String aGroupId, String aArtifactId, String aOwnerHumanReadableObjectDescription) {
		validateCoordinateConsistency(aGroupId, aArtifactId, aGroupId.trim() + ":" + aArtifactId.trim(), aOwnerHumanReadableObjectDescription);
		return toCoordinateId(aGroupId, aArtifactId);
	}

	private static int countChar(String s, char ch) {
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ch) n++;
		}
		return n;
	}
}
