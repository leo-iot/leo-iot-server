package at.htl.shared;

public interface Module {
    default void start() { }
    default void stop() { }
}
