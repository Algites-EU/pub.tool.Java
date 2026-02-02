package eu.algites.pltf.knitstro.structure.version.utils;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a version text does not match any canonical parse pattern.
 */
public class AIxVersionNotRecognizedException extends AIxVersionException {

	public AIxVersionNotRecognizedException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aMessageOutputMode);
	}

	public AIxVersionNotRecognizedException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final boolean aEnableSuppression,
			final boolean aWritableStackTrace,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aEnableSuppression, aWritableStackTrace, aMessageOutputMode);
	}

	public AIxVersionNotRecognizedException(
			final @NotNull Supplier<String> aMessageSupplier,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aMessageOutputMode);
	}
}
