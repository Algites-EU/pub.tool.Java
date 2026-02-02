package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.exception.AIxRuntimeException;
import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

/**
 * Base exception for version scheme/structure/utils errors.
 *
 * This exception family is used by the version utils to signal:
 * - scheme configuration errors,
 * - ambiguous parsing results,
 * - invalid canonical normalization,
 * - invalid transitions,
 * - invalid component value operations.
 */
public class AIxVersionException extends AIxRuntimeException {

	public AIxVersionException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aMessageOutputMode);
	}

	public AIxVersionException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final boolean aEnableSuppression,
			final boolean aWritableStackTrace,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aEnableSuppression, aWritableStackTrace, aMessageOutputMode);
	}

	public AIxVersionException(
			final @NotNull Supplier<String> aMessageSupplier,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aMessageOutputMode);
	}
}
