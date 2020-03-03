package ie.ucd.DoHO.model.Exceptions;

public class ArtifactUnavailableException extends RuntimeException {
    public ArtifactUnavailableException() {
        super();
    }

    public ArtifactUnavailableException(String message) {
        super(message);
    }
}
