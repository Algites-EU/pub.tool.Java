package eu.algites.pltf.knitstro.structure.version.utils;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a transition cannot be applied due to missing transition definition
 * or because the transition produces an invalid result.
 */
public class AIxVersionTransitionException extends AIxVersionException {

	public AIxVersionTransitionException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aMessageOutputMode);
	}

	public AIxVersionTransitionException(
			final Supplier<String> aMessageSupplier,
			final Throwable aCause,
			final boolean aEnableSuppression,
			final boolean aWritableStackTrace,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aCause, aEnableSuppression, aWritableStackTrace, aMessageOutputMode);
	}

	public AIxVersionTransitionException(
			final @NotNull Supplier<String> aMessageSupplier,
			final AInStringOutputMode aMessageOutputMode) {
		super(aMessageSupplier, aMessageOutputMode);
	}
}
