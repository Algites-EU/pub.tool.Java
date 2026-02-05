package eu.algites.pltf.knitstro.structure.artifacts.model.utils;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AIiUidPartMetadata;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputTypeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputTypeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AInArtifactBuiltinOutputType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIrArtifactOutputTypeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIrTemplateDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinDependencyScopeBindingTemplate;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIiPurposeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIiPurposeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIiPurposeGroupDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIiPurposeGroupDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.export.AIiExportModeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.export.AIiExportModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage.AIiLocalUsageModeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage.AIiLocalUsageModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock.AIiLockKindDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock.AIiLockKindDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AIiSetDataInheritanceModeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AIiSetDataInheritanceModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIiSourceSetDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIiSourceSetDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIiSourceSetGroupDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIiSourceSetGroupDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AInBuiltinPurpose;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AInBuiltinPurposeGroup;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.export.AInBuiltinExportMode;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage.AInBuiltinLocalUsageMode;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock.AInBuiltinLockKind;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinSetDataInheritanceMode;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AInBuiltinSourceSet;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AInBuiltinSourceSetGroup;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIrPurposeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIrPurposeGroupDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.export.AIrExportModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage.AIrLocalUsageModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock.AIrLockKindDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AIrSetDataInheritanceModeDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIrSourceSetDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIrSourceSetGroupDataUidRecord;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

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
	OUTPUT_TYPE(
			"OUTPUT_TYPE",
			AIiArtifactOutputTypeDataType.class,
			AIrArtifactOutputTypeDataUidRecord.class,
			AIrArtifactOutputTypeDataUidRecord.getUidRecordFactory(),
			AIrArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInArtifactBuiltinOutputType.class,
			AInArtifactBuiltinOutputType::findByUid,
			AInArtifactBuiltinOutputType::getByUidOrThrow, false),
	DEPENDENCY_SCOPE_BINDING_TEMPLATE(
			"DEPENDENCY_SCOPE_RULE_TEMPLATE",
			AIiTemplateDataType.class,
			AIrTemplateDataUidRecord.class,
			AIrTemplateDataUidRecord.getUidRecordFactory(),
			AIrTemplateDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinDependencyScopeBindingTemplate.class,
			AInBuiltinDependencyScopeBindingTemplate::findByUid,
			AInBuiltinDependencyScopeBindingTemplate::getByUidOrThrow, true),
	DEPENDENCY_SOURCE_SET_GROUP(
			"DEPENDENCY_SOURCE_SET_GROUP",
			AIiSourceSetGroupDataType.class,
			AIrSourceSetGroupDataUidRecord.class,
			AIrSourceSetGroupDataUidRecord.getUidRecordFactory(),
			AIrSourceSetGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinSourceSetGroup.class,
			AInBuiltinSourceSetGroup::findByUid,
			AInBuiltinSourceSetGroup::getByUidOrThrow, false),
	DEPENDENCY_SOURCE_SET(
			"DEPENDENCY_SOURCE_SET",
			AIiSourceSetDataType.class,
			AIrSourceSetDataUidRecord.class,
			AIrSourceSetDataUidRecord.getUidRecordFactory(),
			AIrSourceSetDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinSourceSet.class,
			AInBuiltinSourceSet::findByUid,
			AInBuiltinSourceSet::getByUidOrThrow, false),
	DEPENDENCY_PURPOSE_GROUP(
			"DEPENDENCY_PURPOSE_GROUP",
			AIiPurposeGroupDataType.class,
			AIrPurposeGroupDataUidRecord.class,
			AIrPurposeGroupDataUidRecord.getUidRecordFactory(),
			AIrPurposeGroupDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinPurposeGroup.class,
			AInBuiltinPurposeGroup::findByUid,
			AInBuiltinPurposeGroup::getByUidOrThrow, false),
	DEPENDENCY_PURPOSE(
			"DEPENDENCY_PURPOSE",
			AIiPurposeDataType.class,
			AIrPurposeDataUidRecord.class,
			AIrPurposeDataUidRecord.getUidRecordFactory(),
			AIrPurposeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinPurpose.class,
			AInBuiltinPurpose::findByUid,
			AInBuiltinPurpose::getByUidOrThrow, false),
	DEPENDENCY_SCOPE_BINDING_SET_INHERITANCE_MODE(
			"DEPENDENCY_SCOPE_BINDING_SET_INHERITANCE_MODE",
			AIiSetDataInheritanceModeDataType.class,
			AIrSetDataInheritanceModeDataUidRecord.class,
			AIrSetDataInheritanceModeDataUidRecord.getUidRecordFactory(),
			AIrSetDataInheritanceModeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinSetDataInheritanceMode.class,
			AInBuiltinSetDataInheritanceMode::findByUid,
			AInBuiltinSetDataInheritanceMode::getByUidOrThrow, false),
	DEPENDENCY_SCOPE_BINDING_LOCK_KIND(
			"DEPENDENCY_SCOPE_BINDING_LOCK_KIND",
			AIiLockKindDataType.class,
			AIrLockKindDataUidRecord.class,
			AIrLockKindDataUidRecord.getUidRecordFactory(),
			AIrLockKindDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinLockKind.class,
			AInBuiltinLockKind::findByUid,
			AInBuiltinLockKind::getByUidOrThrow, false),
	DEPENDENCY_SCOPE_BINDING_LOCAL_USAGE_MODE(
			"DEPENDENCY_SCOPE_BINDING_LOCAL_USAGE_MODE",
			AIiLocalUsageModeDataType.class,
			AIrLocalUsageModeDataUidRecord.class,
			AIrLocalUsageModeDataUidRecord.getUidRecordFactory(),
			AIrLocalUsageModeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinLocalUsageMode.class,
			AInBuiltinLocalUsageMode::findByUid,
			AInBuiltinLocalUsageMode::getByUidOrThrow, false),
	DEPENDENCY_SCOPE_BINDING_EXPORT_MODE(
			"DEPENDENCY_SCOPE_BINDING_EXPORT_MODE",
			AIiExportModeDataType.class,
			AIrExportModeDataUidRecord.class,
			AIrExportModeDataUidRecord.getUidRecordFactory(),
			AIrExportModeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA,
			AInBuiltinExportMode.class,
			AInBuiltinExportMode::findByUid,
			AInBuiltinExportMode::getByUidOrThrow, false),
  ;

//	private static volatile Map<Class<? extends AIiUidEnumDataType<? extends AIiUidEnumDataRecord, Class<? extends AIiUidEnumDataRecord>>>, AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>> templateTypes = null;
//	private static Map<Class<? extends AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>>, AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>> typeImplementations;

	private final String code;

	private final Class<?> builtinEnumClass;
	private final Class<?> customRecordClass;
	private final boolean templateComponent;
	private final Function<String, ? extends AIiUidEnumDataRecord> builtinItemGetter;
	private final Class<? extends AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>> dataTypeInterfaceType;
	private final AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> dataType;
	private final Function<String, Optional<? extends AIiUidEnumDataRecord>> builtinItemFinder;
	private final BiFunction<String, List<String>, ? extends AIiUidEnumDataRecord> uidRecordFactory;
	private final List<AIiUidPartMetadata<AInUidEnumDataOrigin>> specificUidPartsMetadata;

	AInComponentType(String aCode,
			final Class<? extends AIiUidEnumDataType<?, AInUidEnumDataOrigin>> aDataTypeInterfaceType, final Class<?> aCustomRecordClass,
			final BiFunction<String, List<String>, ? extends AIiUidEnumDataRecord> aUidRecordFactory,
			final List<AIiUidPartMetadata<AInUidEnumDataOrigin>> aSpecificUidPartsMetadata,
			final Class<?> aBuiltinEnumClass,
			final Function<String, Optional<? extends AIiUidEnumDataRecord>> aBuiltinItemFinder,
			final	Function<String, ? extends AIiUidEnumDataRecord> aBuiltinItemGetter,
			final boolean aTemplateComponent) {
		code = aCode;
		dataTypeInterfaceType = aDataTypeInterfaceType;
		dataType = AIsComponentUtils.newNestedInstanceFromMarkerInterfaceOrThrow(dataTypeInterfaceType);
		builtinItemFinder = aBuiltinItemFinder;
		builtinItemGetter = aBuiltinItemGetter;
		builtinEnumClass = aBuiltinEnumClass;
		customRecordClass = aCustomRecordClass;
		templateComponent = aTemplateComponent;
		uidRecordFactory = aUidRecordFactory;
		specificUidPartsMetadata = aSpecificUidPartsMetadata;
	}

	/**
	 * Gets the metadata for the specific UID parts of the component.
	 * @return the specificUidPartsMetadata
	 */
	public List<AIiUidPartMetadata<AInUidEnumDataOrigin>> getSpecificUidPartsMetadata() {
		return specificUidPartsMetadata;
	}

	/**
	 * Gets the item getter for the given component builtin type enum. The getter throws an exception
	 * if the passed builtin code is unknown.
	 * @return the builtin item corresponding with the Uid.
	 * @param <O> type of the builtin enum
	 */
	@SuppressWarnings("unchecked")
	public <O extends AIiUidEnumDataRecord> Function<String, O> getBuiltinItemGetter() {
		return (Function<String, O>) builtinItemGetter;
	}

	/**
	 * Gets the item finder for the given component builtin type enum. The finder returns null
	 * if the passed builtin code is unknown.
	 * @return the builtin item corresponding with the Uid.
	 * @param <O> type of the builtin enum
	 */
	@SuppressWarnings("unchecked")
	public <O extends AIiUidEnumDataRecord> Function<String, Optional<O>> getBuiltinItemFinder() {
		return (Function) builtinItemFinder;
	}


	/**
	 * @return kind class code used in UIDs ({@code builtin} or {@code custom})
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the templateComponent
	 */
	public boolean isTemplateComponent() {
		return templateComponent;
	}

	/**
	 * @return the builtinEnumClass
	 */
	public Class<?> getBuiltinEnumClass() {
		return builtinEnumClass;
	}

	/**
	 * @return the customRecordClass
	 */
	public Class<?> getCustomRecordClass() {
		return customRecordClass;
	}

	/**
	 * @return the uidRecordFactory
	 * @param <O> type of the factory output
	 */
	@SuppressWarnings("unchecked")
	public <O extends AIiUidEnumDataRecord> BiFunction<String, List<String>, O> getUidRecordFactory() {
		return (BiFunction<String, List<String>, O>) uidRecordFactory;
	}

	/**
	 * @return the dataTypeInterface
	 */
	public Class<? extends AIiUidEnumDataType<?, AInUidEnumDataOrigin>> getDataTypeInterfaceType() {
		return dataTypeInterfaceType;
	}

	/**
	 * @return the dataType
	 */
	public AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> getDataType() {
		return dataType;
	}

	@SuppressWarnings("all")
	private static class AIcArtifactOutputTypeDataType
			extends AIcComponentDataType<AIiArtifactOutputTypeDataUidRecord>
			implements AIiArtifactOutputTypeDataType {

		protected AIcArtifactOutputTypeDataType() {
			super(OUTPUT_TYPE);
		}
	}

	@SuppressWarnings("all")
	private static class AIcTemplateDataType
			extends AIcComponentDataType<AIiTemplateDataUidRecord>
			implements AIiTemplateDataType {

		private AIcTemplateDataType() {
			super(DEPENDENCY_SCOPE_BINDING_TEMPLATE);
		}
	}

	@SuppressWarnings("all")
	private static class AIcSourceSetDataType
			extends AIcComponentDataType<AIiSourceSetDataUidRecord>
			implements AIiSourceSetDataType {

		private AIcSourceSetDataType() {
			super(DEPENDENCY_SOURCE_SET);
		}
	}

	@SuppressWarnings("all")
	private static class AIcSourceSetGroupDataType
			extends AIcComponentDataType<AIiSourceSetGroupDataUidRecord>
			implements AIiSourceSetGroupDataType {

		private AIcSourceSetGroupDataType() {
			super(DEPENDENCY_SOURCE_SET_GROUP);
		}
	}

	@SuppressWarnings("all")
	private static class AIcPurposeDataType
			extends AIcComponentDataType<AIiPurposeDataUidRecord>
			implements AIiPurposeDataType {

		private AIcPurposeDataType() {
			super(DEPENDENCY_PURPOSE);
		}
	}

	@SuppressWarnings("all")
	private static class AIcPurposeGroupDataType
			extends AIcComponentDataType<AIiPurposeGroupDataUidRecord>
			implements AIiPurposeGroupDataType {

		private AIcPurposeGroupDataType() {
			super(DEPENDENCY_PURPOSE_GROUP);
		}
	}

	@SuppressWarnings("all")
	private static class AIcSetDataInheritanceModeDataType
			extends AIcComponentDataType<AIiSetDataInheritanceModeDataUidRecord>
			implements AIiSetDataInheritanceModeDataType {

		private AIcSetDataInheritanceModeDataType() {
			super(DEPENDENCY_SCOPE_BINDING_SET_INHERITANCE_MODE);
		}
	}

	@SuppressWarnings("all")
	private static class AIcLockKindDataType
			extends AIcComponentDataType<AIiLockKindDataUidRecord>
			implements AIiLockKindDataType {

		private AIcLockKindDataType() {
			super(DEPENDENCY_SCOPE_BINDING_LOCK_KIND);
		}
	}

	@SuppressWarnings("all")
	private static class AIcLocalUsageModeDataType
			extends AIcComponentDataType<AIiLocalUsageModeDataUidRecord>
			implements AIiLocalUsageModeDataType {

		private AIcLocalUsageModeDataType() {
			super(DEPENDENCY_SCOPE_BINDING_LOCAL_USAGE_MODE);
		}
	}

	@SuppressWarnings("all")
	private static class AIcExportModeDataType
			extends AIcComponentDataType<AIiExportModeDataUidRecord>
			implements AIiExportModeDataType {

		private AIcExportModeDataType() {
			super(DEPENDENCY_SCOPE_BINDING_EXPORT_MODE);
		}
	}

}
