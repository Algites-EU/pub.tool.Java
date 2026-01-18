package eu.algites.tool.devops.build.model.common;

/**
 * <p>
 * Title: {@link AIiArtifactUidPartsRecord}
 * </p>
 * <p>
 * Description: General interface for the Uid parts records
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 15:46
 */
public interface AIiArtifactUidPartsRecord {
	/**
	 * @return kind class (builtin/custom)
	 */
	AInComponentOriginClass originClass();

	/**
	 * @return namespace segment (empty for builtin, non-empty for custom)
	 */
	String namespace();
}
