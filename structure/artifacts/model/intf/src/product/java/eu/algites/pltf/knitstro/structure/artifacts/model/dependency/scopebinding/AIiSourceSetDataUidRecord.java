package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

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
 * Title: {@link AIiSourceSetDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Source Set Uid record
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
public interface AIiSourceSetDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "source-set-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "source-set-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "source-set-namespace")
	@Override
	String namespace();

	/**
	 * The sourceSetCode field name
	 */
	@SuppressWarnings("all")
	String SOURCE_SET_CODE_FIELD_NAME = "sourceSetCode";

	/**
	 * @return the sourceSetCode
	 */
	@AIaFieldLabel(label = "source-set-code")
	String sourceSetCode();

	@Override
	default String code() { return sourceSetCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiSourceSetDataUidRecord.class,
					SOURCE_SET_CODE_FIELD_NAME), true));

	/**
	 * Static validation of the record
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiSourceSetDataUidRecord.class, SOURCE_SET_CODE_FIELD_NAME);
	}
}
