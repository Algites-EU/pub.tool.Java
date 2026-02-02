package eu.algites.pltf.knitstro.structure.artifacts.model.common;

import eu.algites.lib.common.enums.uiddata.AIiUidPartMetadata;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIrUidPartMetadata;
import eu.algites.lib.common.object.props.labels.AIaFieldLabel;
import eu.algites.lib.common.object.props.labels.AIsFieldLabelUtils;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiArtifactOutputTypeDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Artifact Output Type Uid parts record
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 14:34
 */
public interface AIiArtifactOutputTypeDataUidRecord extends AIiArtifactOutputType, AIiUidEnumDataRecord {

	@Override
	@AIaFieldLabel(label = "output-type-uid")
	String uid();

	@Override
	@AIaFieldLabel(label = "output-type-origin")
	AInUidEnumDataOrigin origin();

	@Override
	@AIaFieldLabel(label = "output-type-namespace")
	String namespace();

	@Override
	@AIaFieldLabel(label = "output-type-classifier")
	String classifier();

	@Override
	@AIaFieldLabel(label = "output-type-packaging-file-type")
	String packagingFileTypeCode();

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on {@link AIiArtifactOutputType}
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(
							() -> AIsFieldLabelUtils.findLabel(
									AIiArtifactOutputTypeDataUidRecord.class,
	AIiArtifactOutputTypeDataUidRecord.CLASSIFIER_FIELD_NAME),false),
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiArtifactOutputTypeDataUidRecord.class,
	AIiArtifactOutputTypeDataUidRecord.PACKAGING_FILE_TYPE_CODE_FIELD_NAME),true));

	/**
	 * Static validation of the record
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiArtifactOutputTypeDataUidRecord.class,
				CLASSIFIER_FIELD_NAME);
		AIsFieldLabelUtils.requirePropertyExists(
				AIiArtifactOutputTypeDataUidRecord.class,
				PACKAGING_FILE_TYPE_CODE_FIELD_NAME);
	}
}
