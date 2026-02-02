package eu.algites.pltf.knitstro.structure.artifacts.model.common.version;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;

public class AIcVersionReleaseLineRevision implements AIiVersionReleaseLineRevision {

	private int number;
	private int revisionStringSize;

	public AIcVersionReleaseLineRevision() {
	}

	public AIcVersionReleaseLineRevision(int aNumber, int aRevisionStringSize) {
		number = aNumber;
		revisionStringSize = aRevisionStringSize;
	}

	@Override
	public int getNumber() {
		return number;
	}

	public void setNumber(int aNumber) {
		this.number = aNumber;
	}

	/**
	 * @return the revisionStringSize
	 */
	@Override
	public int getRevisionStringSize() {
		return revisionStringSize;
	}

	/**
	 * @param aRevisionStringSize the revisionStringSize
	 */
	public void setRevisionStringSize(final int aRevisionStringSize) {
		revisionStringSize = aRevisionStringSize;
	}

	@Override
	public boolean equals(final Object aO) {
		if (aO == null || getClass() != aO.getClass())
			return false;

		AIcVersionReleaseLineRevision locthat = (AIcVersionReleaseLineRevision) aO;
		return number == locthat.number && revisionStringSize == locthat.revisionStringSize;
	}

	@Override
	public int hashCode() {
		int result = number;
		result = 31 * result + revisionStringSize;
		return result;
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{" +
					"number=" + number +
					", revisionStringSize=" + revisionStringSize +
					'}';
		else
			return "AIcVersionReleaseLineRevision{" +
					"            number=" + number +
					",             revisionStringSize=" + revisionStringSize +
					'}';
	}
}
