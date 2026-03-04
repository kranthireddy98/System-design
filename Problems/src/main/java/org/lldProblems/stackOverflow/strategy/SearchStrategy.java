package org.lldProblems.stackOverflow.strategy;

import org.lldProblems.stackOverflow.entities.Question;

import java.util.List;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);
}
