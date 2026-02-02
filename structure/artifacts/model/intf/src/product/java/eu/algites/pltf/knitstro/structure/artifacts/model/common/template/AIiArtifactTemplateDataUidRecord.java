package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import eu.algites.lib.common.enums.AIiEnumItem;
import eu.algites.lib.common.enums.uiddata.AIiUidPartMetadata;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.lib.common.enums.uiddata.AIrUidPartMetadata;
import eu.algites.lib.common.object.props.labels.AIaFieldLabel;
import eu.algites.lib.common.object.props.labels.AIsFieldLabelUtils;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiArtifactTemplateDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Template Uid parts record
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
public interface AIiArtifactTemplateDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "renderPattern-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "renderPattern-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "renderPattern-namespace")
	@Override
	String namespace();

	/**
	 * The templateCode field name
	 */
	@SuppressWarnings("all")
	String TEMPLATE_CODE_FIELD_NAME = "templateCode";

	/**
	 * @return the templateCode
	 */
	@AIaFieldLabel(label = "renderPattern-sourceSetCode")
	String templateCode();

	@Override
	default String code() { return templateCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiArtifactTemplateDataUidRecord.class,
					TEMPLATE_CODE_FIELD_NAME),
					AInUidEnumDataOrigin.values(), true));

	/**
	 * Static validation of the record
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiArtifactTemplateDataUidRecord.class,
				TEMPLATE_CODE_FIELD_NAME);
	}

}
