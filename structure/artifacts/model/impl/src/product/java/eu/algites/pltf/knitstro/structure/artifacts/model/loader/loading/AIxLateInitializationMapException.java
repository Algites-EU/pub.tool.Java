package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

/**
 * Exception thrown for invalid late-initialization map operations.
 */
final class AIxLateInitializationMapException extends RuntimeException {

	public AIxLateInitializationMapException(final String aMessage) {
		super(aMessage);
	}

	public AIxLateInitializationMapException(final String aMessage, final Throwable aCause) {
		super(aMessage, aCause);
	}
}
