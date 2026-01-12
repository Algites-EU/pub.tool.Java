package eu.algites.tool.build.model.loader;

import eu.algites.tool.build.model.artifact.dto.AIcArtifactCoordinateVersionDefDTO;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;

import java.util.Objects;

/**
 * <p>
 * Title: {@link AIcArtifactVersionModelLoadingContainer}
 * </p>
 * <p>
 * Description: TODO: Add description
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 14:50
 */
public class AIcArtifactVersionModelLoadingContainer<A extends AIiAbstractArtifact> {
	private A artifact;
	private AIcArtifactCoordinateVersionDefDTO artifactDefDTO;
	private boolean primaryArtifactRegistration;

	AIcArtifactVersionModelLoadingContainer(
			final A aArtifact, final AIcArtifactCoordinateVersionDefDTO aArtifactDefDTO,
			final boolean aPrimaryArtifactRegistration) {
		artifact = aArtifact;
		artifactDefDTO = aArtifactDefDTO;
		primaryArtifactRegistration = aPrimaryArtifactRegistration;
	}

	/**
	 * @return the artifacts
	 */
	public A getArtifact() {
		return artifact;
	}

	/**
	 * @return the artifactDefDTO
	 * @param <ADTO> type of the Artifact DTO
	 */
	@SuppressWarnings("unchecked")
	public <ADTO extends AIcArtifactCoordinateVersionDefDTO> ADTO getArtifactDefDTO() {
		return (ADTO) artifactDefDTO;
	}

	/**
	 * @return the primaryArtifactRegistration
	 */
	public boolean isPrimaryArtifactRegistration() {
		return primaryArtifactRegistration;
	}

	/**
	 * @param aArtifact the artifact
	 */
	public void setArtifact(final A aArtifact) {
		artifact = aArtifact;
	}

	/**
	 * @param aArtifactDefDTO the artifactDefDTO
	 */
	public void setArtifactDefDTO(final AIcArtifactCoordinateVersionDefDTO aArtifactDefDTO) {
		artifactDefDTO = aArtifactDefDTO;
	}

	/**
	 * @param aPrimaryArtifactRegistration the primaryArtifactRegistration
	 */
	public void setPrimaryArtifactRegistration(final boolean aPrimaryArtifactRegistration) {
		primaryArtifactRegistration = aPrimaryArtifactRegistration;
	}

	@Override
	public final boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactVersionModelLoadingContainer<?> locthat))
			return false;

		return primaryArtifactRegistration == locthat.primaryArtifactRegistration && Objects.equals(artifact, locthat.artifact)
				&& Objects.equals(artifactDefDTO, locthat.artifactDefDTO);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(artifact);
		result = 31 * result + Objects.hashCode(artifactDefDTO);
		result = 31 * result + Boolean.hashCode(primaryArtifactRegistration);
		return result;
	}

	@Override
	public String toString() {
		return "AIcArtifactVersionModelLoadingContainer{" +
				"artifact=" + artifact +
				", artifactDefDTO=" + artifactDefDTO +
				", primaryArtifactRegistration=" + primaryArtifactRegistration +
				'}';
	}
}
