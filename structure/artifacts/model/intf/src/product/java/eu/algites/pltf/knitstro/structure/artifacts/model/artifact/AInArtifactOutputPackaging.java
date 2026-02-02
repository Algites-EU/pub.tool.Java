package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

/**
 * <p>
 * Title: {@link AInArtifactOutputPackaging}
 * </p>
 * <p>
 * Description: packaging of the artifact
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 5:09
 */
public enum AInArtifactOutputPackaging {
	POM("pom"),
	JAR("jar"),
	;

	private final String outputPackagingCode;

	AInArtifactOutputPackaging(final String aOutputPackagingCode) {
		outputPackagingCode = aOutputPackagingCode;
	}

	/**
	 * @return the packaging file type code
	 */
	public String getPackagingFileTypeCode() {
		return outputPackagingCode;
	}

	/**
	 * finds the packaging from the packagingCode
	 * @param aOutputPackagingCode packagingCode of the packaging
	 * @return found packaging if found or null, if null is passed or if the value is passed,
	 *    which does not correspond to any defined packaging.
	 * @throws IllegalArgumentException if the packagingCode is unknown
	 */
	public static AInArtifactOutputPackaging getByCodeOrThrow(String aOutputPackagingCode)
	throws IllegalArgumentException {
		if (aOutputPackagingCode == null) { return null;}
		for (AInArtifactOutputPackaging packaging : values()) {
			if (packaging.getPackagingFileTypeCode().equals(aOutputPackagingCode)) {
				return packaging;
			}
		}
		throw new IllegalArgumentException("Unsupported packaging: '" + aOutputPackagingCode + "'");
	}
}
