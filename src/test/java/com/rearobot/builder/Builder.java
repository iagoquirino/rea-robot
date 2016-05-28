package com.rearobot.builder;

/**
 * @author iago
 */
public abstract class Builder<T> {

    protected T prototype;

    public Builder() {
        prototype = newPrototype();
        startPrototype();
    }

    public T build() {
        return clonePrototype();
    }

    protected void startPrototype() {
        // do nothing by default :)
    }

    protected abstract T newPrototype();

    protected abstract T clonePrototype();

}
