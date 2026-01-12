package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.lib.common.object.stringoutput.AInStringOutputMode.USER;
import static eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils.isUsedStringOutputMode;

/**
 * <p>
 * Title: {@link AIcArtifactDependencyScopeLevelOutputs}
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
 * @date 12.01.26 5:02
 */
public class AIcArtifactDependencyScopeLevelOutputs {
	private final AInArtifactKind artifactKind;
	private final AInArtifactOutputPackagingClass packagingClass;

	public AIcArtifactDependencyScopeLevelOutputs(
			final AInArtifactKind aArtifactKind,
			final AInArtifactOutputPackagingClass aPackagingClass) {
		artifactKind = aArtifactKind;
		packagingClass = aPackagingClass;
	}

	/**
	 * @return the artifactKind
	 */
	public AInArtifactKind getArtifactKind() {
		return artifactKind;
	}

	/**
	 * @return the packagingClass
	 */
	public AInArtifactOutputPackagingClass getPackagingClass() {
		return packagingClass;
	}

	@Override
	public String toString() {
		if (isUsedStringOutputMode(USER))
			return "{" +
					"artifactKind=" + artifactKind +
					", packagingClass=" + packagingClass +
					'}';
		else
			return "AIcArtifactDependencyScopeLevelOutputs{" +
					"            artifactKind=" + artifactKind +
					",             packagingClass=" + packagingClass +
					'}';
	}
}
