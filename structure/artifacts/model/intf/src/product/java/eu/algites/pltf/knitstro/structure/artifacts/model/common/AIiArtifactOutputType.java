package eu.algites.pltf.knitstro.structure.artifacts.model.common;

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
	 * The classifier field name
	 */
	@SuppressWarnings("all")
	String CLASSIFIER_FIELD_NAME = "classifier";

	/**
	 * @return the classifier
	 */
	String classifier();

	/**
	 * The packaging file type packagingCode field name
	 */
	@SuppressWarnings("all")
	String PACKAGING_FILE_TYPE_CODE_FIELD_NAME = "packagingFileTypeCode";

	/**
	 * @return the packaging
	 */
	String packagingFileTypeCode();
}
