package eu.algites.tool.devops.build.model.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static eu.algites.tool.devops.build.model.common.AIsComponentUtils.AIcComponentUidPartMetadataRecord;

import eu.algites.lib.common.enumdata.AIiGloballyUniqueEnumDataType;
import eu.algites.lib.common.enumdata.AIiUidPartsRecord;
import eu.algites.lib.common.enumdata.AInEnumDataOrigin;
import eu.algites.lib.common.enumdata.AIrUidPartMetadata;

import org.gradle.internal.impldep.org.apache.commons.lang3.function.TriFunction;

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
public enum AInComponentType implements AIiGloballyUniqueEnumDataType<AIiUidPartsRecord> {
	OUTPUT_TYPE("OUTPUT_TYPE",
			List.of(
					new AIrUidPartMetadata("classifier", /* not required: */ Collections.emptyMap()),
					new AIrUidPartMetadata("packaging-file-type", Map.of(AInEnumDataOrigin.BUILTIN, true, AInEnumDataOrigin.CUSTOM, true)),
			AInArtifactBuiltinOutputType.class,
			aStrings -> new AIsComponentUtils.AIcArtifactOutputTypeUidPartsRecord(
					AInEnumDataOrigin.getByCodeOrThrow(aStrings[0]),
					aStrings[1], aStrings[2], aStrings[3]), AInArtifactBuiltinOutputType::getByUidOrThrow),
	DEPENDENCY_SCOPE_RULE_TEMPLATE(
			"DEPENDENCY_SCOPE_RULE_TEMPLATE",
			List.of(
					new AIcComponentUidPartMetadataRecord("class", true, true),
					new AIcComponentUidPartMetadataRecord("namespace", false, true),
					new AIcComponentUidPartMetadataRecord("template-id", true, true)),
			AInArtifactBuiltinDependencyScopeBindingTemplate.class,
			aStrings -> new AIsComponentUtils.AIcArtifactTemplateUidPartsRecord(
					AInEnumDataOrigin.getByCodeOrThrow(aStrings[0]),
					aStrings[1], aStrings[2]),
			AInArtifactBuiltinDependencyScopeBindingTemplate::getByUidOrThrow);

	private final String code;

	private final List<AIcComponentUidPartMetadataRecord> specificUidPartsMetadata;
	private final Class<?> builtinEnumClass;
	private final Function<String[], AIiUidPartsRecord> customRecordConstructor;
	private final Function<String, AIiUidPartsRecord> builtinRecordConstructor;

	public static final int COMPONENT_CLASS_UID_POSITION = 0;
	public static final int NAMESPACE_UID_POSITION = 1;

	AInComponentType(String aCode, final List<AIcComponentUidPartMetadataRecord> aSpecificUidPartsMetadata,
			Class<?> aBuiltinEnumClass, Function<String[], AIiUidPartsRecord> aCustomRecordConstructor,
			final Function<String, AIiUidPartsRecord> aBuiltinRecordConstructor) {
		code = aCode;
		specificUidPartsMetadata = aSpecificUidPartsMetadata;
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


	@Override
	public TriFunction<AIiGloballyUniqueEnumDataType, String, List<String>, AIiUidPartsRecord> getUidRecordConstructor() {
		return null;
	}

	@Override
	public List<AIcComponentUidPartMetadataRecord> getSpecificUidPartsMetadata() {
		return specificUidPartsMetadata;
	}
}
