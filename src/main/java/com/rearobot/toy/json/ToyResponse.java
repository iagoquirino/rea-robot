package com.rearobot.toy.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author iago
 */
@JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToyResponse<T> {

    private T result;

    private List<T> history;

    /**
     * Constructor
     *
     * @param result
     * @param history
     */
    public ToyResponse(T result, List<T> history) {
        super();
        this.result = result;
        this.history = history;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @return the history
     */
    public List<T> getHistory() {
        return history;
    }

}
