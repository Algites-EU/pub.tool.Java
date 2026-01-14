package eu.algites.tool.build.model.artifact.intf;

/**
 * <p>
 * Title: {@link AIiArtifactOutputType}
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
 * @date 13.01.26 17:31
 */
public interface AIiArtifactOutputType {
	/**
	 * @return the classifier
	 */
	String getClassifier();

	/**
	 * @return the packaging
	 */
	String getPackagingFileTypeCode();
}
