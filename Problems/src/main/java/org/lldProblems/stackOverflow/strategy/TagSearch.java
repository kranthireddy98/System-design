package org.lldProblems.stackOverflow.strategy;

import org.lldProblems.stackOverflow.entities.Question;
import org.lldProblems.stackOverflow.entities.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearch implements SearchStrategy{

    private final Tag tag;

    public TagSearch(Tag tag) {
        this.tag = tag;
    }

    @Override
    public List<Question> filter(List<Question> questions){
        return questions.stream()
                .filter(q -> q.getTags().stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(tag.getName())))
                .collect(Collectors.toList());
    }
}
