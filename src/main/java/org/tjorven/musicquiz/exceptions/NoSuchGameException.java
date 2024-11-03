package org.tjorven.musicquiz.exceptions;

public class NoSuchGameException extends RuntimeException {
    public NoSuchGameException(String message) {
        super(message);
    }
}
