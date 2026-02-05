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
 * Title: {@link AIiPurposeGroupDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Source Set Group Uid record
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
public interface AIiPurposeGroupDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "purpose-group-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "purpose-group-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "purpose-group-namespace")
	@Override
	String namespace();

	/**
	 * The groupCode field name
	 */
	@SuppressWarnings("all")
	String GROUP_CODE_FIELD_NAME = "groupCode";

	/**
	 * @return the groupCode
	 */
	@AIaFieldLabel(label = "purpose-group-code")
	String groupCode();

	@Override
	default String code() { return groupCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiPurposeGroupDataUidRecord.class,
					GROUP_CODE_FIELD_NAME), true));

	/**
	 * Static validation of the record fields.
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiPurposeGroupDataUidRecord.class, GROUP_CODE_FIELD_NAME);
	}


}
