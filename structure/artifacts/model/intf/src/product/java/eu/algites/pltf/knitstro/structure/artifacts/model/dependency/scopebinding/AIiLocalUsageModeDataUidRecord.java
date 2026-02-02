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
 * Title: {@link AIiLocalUsageModeDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Binding set inheritance mode record
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
public interface AIiLocalUsageModeDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "local-inclusion-mode-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "local-inclusion-mode-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "local-inclusion-mode-namespace")
	@Override
	String namespace();

	/**
	 * The modeCode field name
	 */
	@SuppressWarnings("all")
	String MODE_CODE_FIELD_NAME = "modeCode";

	/**
	 * @return the modeCode
	 */
	@AIaFieldLabel(label = "local-inclusion-mode-code")
	String modeCode();

	@Override
	default String code() { return modeCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiLocalUsageModeDataUidRecord.class,
					MODE_CODE_FIELD_NAME), true));

	/**
	 * Static validation of the record fields.
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiLocalUsageModeDataUidRecord.class, MODE_CODE_FIELD_NAME);
	}


}
