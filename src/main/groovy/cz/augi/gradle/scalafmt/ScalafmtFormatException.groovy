package cz.augi.gradle.scalafmt

import org.gradle.api.GradleException

class ScalafmtFormatException extends GradleException {
    public ScalafmtFormatException(List<String> filePaths) {
        super("Files incorrectly formatted: " + filePaths.join(","))
    }
}
