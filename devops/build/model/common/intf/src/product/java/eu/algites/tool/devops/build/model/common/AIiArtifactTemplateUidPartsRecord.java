package eu.algites.tool.devops.build.model.common;

import eu.algites.lib.common.enumdata.AIiUidPartsRecord;

/**
 * <p>
 * Title: {@link AIiArtifactTemplateUidPartsRecord}
 * </p>
 * <p>
 * Description:Interface for the Template Uid parts record
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
public interface AIiArtifactTemplateUidPartsRecord extends AIiUidPartsRecord {
	/**
	 * @return the classifier
	 */
	String templateId();

}
