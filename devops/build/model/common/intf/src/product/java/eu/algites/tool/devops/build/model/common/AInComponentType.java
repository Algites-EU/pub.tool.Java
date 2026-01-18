package eu.algites.tool.devops.build.model.common;

import java.util.List;
import java.util.function.Function;
import static eu.algites.tool.devops.build.model.common.AIsComponentUtils.AIcComponentUidPartMetadataRecord
/**
 * <p>
 * Title: {@link AInComponentType}
 * </p>
 * <p>
 * Description: Definition of the type of the given data - output type or template.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 13.01.26 17:00
 */
public enum AInComponentType {
	OUTPUT_TYPE("OUTPUT_TYPE",
			List.of(
					new AIcComponentUidPartMetadataRecord("class", true, true),
					new AIcComponentUidPartMetadataRecord("namespace", false, true),
					new AIcComponentUidPartMetadataRecord("classifier", false, false),
					new AIcComponentUidPartMetadataRecord("packaging-file-type", true, true)),
			AInArtifactBuiltinOutputType.class,
			aStrings -> new AIsComponentUtils.AIcArtifactOutputTypeUidPartsRecord(
					AInComponentOriginClass.getByCodeOrThrow(aStrings[0]),
					aStrings[1], aStrings[2], aStrings[3]), AInArtifactBuiltinOutputType::getByUidOrThrow),
	DEPENDENCY_SCOPE_RULE_TEMPLATE(
			"DEPENDENCY_SCOPE_RULE_TEMPLATE",
			List.of(
					new AIcComponentUidPartMetadataRecord("class", true, true),
					new AIcComponentUidPartMetadataRecord("namespace", false, true),
					new AIcComponentUidPartMetadataRecord("template-id", true, true)),
			AInArtifactBuiltinDependencyScopeRuleTemplate.class,
			aStrings -> new AIsComponentUtils.AIcArtifactTemplateUidPartsRecord(
					AInComponentOriginClass.getByCodeOrThrow(aStrings[0]),
					aStrings[1], aStrings[2]),
			AInArtifactBuiltinDependencyScopeRuleTemplate::getByUidOrThrow);

	private final String code;

	private final List<AIcComponentUidPartMetadataRecord> uidSegmentMetadata;
	private final Class<?> builtinEnumClass;
	private final Function<String[], AIiArtifactUidPartsRecord> customRecordConstructor;
	private final Function<String, AIiArtifactUidPartsRecord> builtinRecordConstructor;

	public static final int COMPONENT_CLASS_UID_POSITION = 0;
	public static final int NAMESPACE_UID_POSITION = 1;

	AInComponentType(String aCode, final List<AIcComponentUidPartMetadataRecord> aUidSegmentMetadata,
			Class<?> aBuiltinEnumClass, Function<String[], AIiArtifactUidPartsRecord> aCustomRecordConstructor,
			final Function<String, AIiArtifactUidPartsRecord> aBuiltinRecordConstructor) {
		code = aCode;
		uidSegmentMetadata = aUidSegmentMetadata;
		builtinEnumClass = aBuiltinEnumClass;

		customRecordConstructor = aCustomRecordConstructor;
		builtinRecordConstructor = aBuiltinRecordConstructor;
	}

	/**
	 * @return kind class code used in UIDs ({@code builtin} or {@code custom})
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the uidSegmentCount
	 */
	public int getUidSegmentCount() {
		return uidSegmentMetadata.size();
	}

	/**
	 * Gets the list of the metadata records. The order of the entries corresponds with the order of the segments in the UID.
	 * @return the uidSegmentMetadata
	 */
	public List<AIcComponentUidPartMetadataRecord> getUidSegmentMetadata() {
		return uidSegmentMetadata;
	}

	/**
	 * @return the builtinEnumClass
	 */
	public Class<?> getBuiltinEnumClass() {
		return builtinEnumClass;
	}

	/**
	 * @return the customRecordConstructor
	 */
	public Function<String[], AIiArtifactUidPartsRecord> getCustomRecordConstructor() {
		return customRecordConstructor;
	}

	/**
	 * @return the builtinRecordConstructor
	 */
	public Function<String, AIiArtifactUidPartsRecord> getBuiltinRecordConstructor() {
		return builtinRecordConstructor;
	}


}
