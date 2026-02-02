package eu.algites.pltf.knitstro.structure.artifacts.model.utils;

import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.ORIGIN_UID_POSITION;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AIiUidPartMetadata;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.List;
import java.util.function.BiFunction;

/**
 * <p>
 * Title: {@link AIcComponentDataType}
 * </p>
 * <p>
 * Description: TODO: Add description
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 11:37
 */
public abstract class AIcComponentDataType<R extends AIiUidEnumDataRecord> implements AIiComponentDataType<R> {

	private final AInComponentType componentType;

	/**
	 * Constructor used in the {@link AInComponentType} or possible other custom definitions
	 * @param aComponentType component type for which the type is being initialized
	 */
	protected AIcComponentDataType(AInComponentType aComponentType) {
		componentType = aComponentType;
	}

	@Override
	public AInComponentType getComponentType() {
		return componentType;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BiFunction<String, List<String>, R> getUidRecordFactory() {
		return (aUid, aParts) -> {
			AInUidEnumDataOrigin locOrigin = AInUidEnumDataOrigin.getByCodeOrThrow(aParts.get(ORIGIN_UID_POSITION));
			if (locOrigin != AInUidEnumDataOrigin.BUILTIN)
				return (R) componentType.getUidRecordFactory().apply(aUid, aParts);

			return (R) componentType.getBuiltinItemGetter().apply(aUid);
		};
	}

	@Override
	public List<AIiUidPartMetadata<AInUidEnumDataOrigin>> getSpecificUidPartsMetadata() {
		return componentType.getSpecificUidPartsMetadata();
	}

}
