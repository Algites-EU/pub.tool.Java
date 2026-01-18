package eu.algites.tool.devops.build.model.common.version;

/**
 * <p>
 * Title: {@link AIiVersionReleaseLineRevision}
 * </p>
 * <p>
 * Description: Definition of the revision number of the release line
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 08.01.26 0:52
 */
public interface AIiVersionReleaseLineRevision {

	/**
	 * Gets the revision number
	 *
	 * @return the revision number
	 */
	int getNumber();

	/**
	 * Gets the size of the revision string if generated from the revision number
	 * It is used to append the "0" characters up to this size minus the string
	 * size of the number coming from {@link #getNumber()}.<br/>
	 * So, for example, if {@link #getRevisionStringSize()} is 3,
	 * then the revision string will be "001" for revision number 1
	 * and 012 for revision number 12.
	 * @return size of the generated version string into the installed/deployed version
	 */
	int getRevisionStringSize();

}
