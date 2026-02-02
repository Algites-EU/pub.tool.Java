package eu.algites.pltf.knitstro.structure.version.utils;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a version text matches more than one candidate (e.g., multiple structures or outputOnlyFormats).
 */
public class AIxVersionAmbiguousException extends AIxVersionException {

	public AIxVersionAmbiguousException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aMessageOutputMode);
	}

	public AIxVersionAmbiguousException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final boolean aEnableSuppression,
			final boolean aWritableStackTrace,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aEnableSuppression, aWritableStackTrace, aMessageOutputMode);
	}

	public AIxVersionAmbiguousException(
			final @NotNull Supplier<String> aMessageSupplier,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aMessageOutputMode);
	}
}
