package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock;

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
 * Title: {@link AIiLockKindDataUidRecord}
 * </p>
 * <p>
 * Description:Interface for the Lock kind Uid record
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
public interface AIiLockKindDataUidRecord extends AIiUidEnumDataRecord, AIiEnumItem {

	@AIaFieldLabel(label = "lock-kind-uid")
	@Override
	String uid();

	@AIaFieldLabel(label = "lock-kind-origin")
	@Override
	AInUidEnumDataOrigin origin();

	@AIaFieldLabel(label = "lock-kind-namespace")
	@Override
	String namespace();

	/**
	 * The kindCode field name
	 */
	@SuppressWarnings("all")
	String KIND_CODE_FIELD_NAME = "kindCode";

	/**
	 * @return the kindCode
	 */
	@AIaFieldLabel(label = "lock-kind-code")
	String kindCode();

	@Override
	default String code() { return kindCode(); }

	/**
	 * The metadata of the specific parts of the record, expressed by fields defined on this interface
	 */
	@SuppressWarnings("unchecked")
	List<AIiUidPartMetadata<AInUidEnumDataOrigin>> RECORD_SPECIFIC_PARTS_METADATA = List.of(
			new AIrUidPartMetadata(() -> AIsFieldLabelUtils.findLabel(
					AIiLockKindDataUidRecord.class,
					KIND_CODE_FIELD_NAME), true));

	/**
	 * Static validation of the record fields.
	 */
	static void staticValidation() {
		AIsFieldLabelUtils.requirePropertyExists(
				AIiLockKindDataUidRecord.class, KIND_CODE_FIELD_NAME);
	}


}
