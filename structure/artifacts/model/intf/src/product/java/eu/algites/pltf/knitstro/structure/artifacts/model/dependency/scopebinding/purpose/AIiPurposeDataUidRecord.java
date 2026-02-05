package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose;

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
 * Title: {@link AIiPurposeDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Dependency Purpose Uid record
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
public interface AIiPurposeDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "purpose-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "purpose-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "purpose-namespace")
	@Override
	String namespace();

	/**
	 * The purposeCode field name
	 */
	@SuppressWarnings("all")
	String PURPOSE_CODE_FIELD_NAME = "purposeCode";

	/**
	 * @return the purposeCode
	 */
	@AIaFieldLabel(label = "purpose-code")
	String purposeCode();

	@Override
	default String code() { return purposeCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiPurposeDataUidRecord.class,
					PURPOSE_CODE_FIELD_NAME), true));

	/**
	 * Static validation of the record
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiPurposeDataUidRecord.class, PURPOSE_CODE_FIELD_NAME);
	}

}
