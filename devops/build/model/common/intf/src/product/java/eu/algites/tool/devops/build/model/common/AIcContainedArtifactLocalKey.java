package eu.algites.tool.devops.build.model.common;

import java.nio.file.Path;
import java.util.Objects;

import jakarta.annotation.Nonnull;

/**
 * <p>
 * Title: {@link AIcContainedArtifactLocalKey}
 * </p>
 * <p>
 * Description: contains the key used for the containers of the artifacts.
 *    path cannot be used exclusivel like the keyx, because the artificial artifacts
 *    (created for single core artifact mode) are referencing the same configuration file,
 *    like the original single core artifact, so the path is not unique since
 *    in the artificial aggregator artifact has to exist also artificial policy artifact
 *    for the given core artifact.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 17:39
 */
public final class AIcContainedArtifactLocalKey {

	/**
	 * Generates the new contained artifact local key
	 * @param aArtifactConfigurationFile relative path to the given contained artifact
	 * @return the new contained artifact local key
	 */
	public static AIcContainedArtifactLocalKey newKey(final @Nonnull Path aArtifactConfigurationFile) {
		return new AIcContainedArtifactLocalKey(aArtifactConfigurationFile);
	}

	/**
	 * The only constructor of the key
	 *
	 * @param aArtifactConfigurationFile the path to the artifact configuration file
	 */
	private AIcContainedArtifactLocalKey(final @Nonnull Path aArtifactConfigurationFile) {
		artifactConfigurationFile = aArtifactConfigurationFile;
	}

	private final Path artifactConfigurationFile;

	/**
	 * Gets the configuration file path.
	 * @return the configuration file path
	 */
	public @Nonnull Path getArtifactConfigurationFile() {
		return artifactConfigurationFile;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcContainedArtifactLocalKey locthat))
			return false;

		return Objects.equals(artifactConfigurationFile, locthat.artifactConfigurationFile);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(artifactConfigurationFile);
		return result;
	}

	@Override
	public String toString() {
		return "AIcContainedArtifactKey{" +
				"artifactConfigurationFile=" + artifactConfigurationFile +
				'}';
	}
}
