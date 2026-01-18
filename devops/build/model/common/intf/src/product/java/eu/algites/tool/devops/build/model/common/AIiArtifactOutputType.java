package eu.algites.tool.devops.build.model.common;

/**
 * <p>
 * Title: {@link AIiArtifactOutputType}
 * </p>
 * <p>
 * Description: interface for the artifact output type
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
	String classifier();

	/**
	 * @return the packaging
	 */
	String packagingFileTypeCode();
}
