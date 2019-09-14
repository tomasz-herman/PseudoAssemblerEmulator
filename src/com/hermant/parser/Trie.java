package com.hermant.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Trie<V> {

    private static final int CHARS = 26;

    private boolean hasValue = false;
    private V value = null;
    private List<Trie<V>> children;

    Trie() {
        children = new ArrayList<>(Collections.nCopies(CHARS, null));
    }

    void put(String key, V value){
        Trie<V> current = this;
        for (int i = 0; i < key.length(); i++) {
            final int index = key.charAt(i) - 'A';
            final List<Trie<V>> children = current.children;
            if(children.get(index) == null)
                children.set(index, new Trie<>());
            current = children.get(index);
        }
        if(current.hasValue)throw new IllegalStateException("The key already exist in the trie.");
        current.hasValue = true;
        current.value = value;
    }

    V get(String key){
        Trie<V> current = this;
        for (int i = 0; i < key.length(); i++) {
            current = current.children.get(key.charAt(i) - 'A');
            if(current == null) return null;
        }
        return current.value;
    }

}
