package com.hermant.parser;

import java.util.*;

class Trie<V> extends AbstractMap<String, V> implements Map<String, V> {

    private static final int CHARS = 26;

    private boolean hasValue = false;
    private V value = null;
    private List<Trie<V>> children;
    private int size = 0;

    Trie() {
        children = new ArrayList<>(Collections.nCopies(CHARS, null));
    }

    @Override
    public V get(Object key) {
        Trie<V> current = this;
        if(!(key instanceof String))throw new IllegalArgumentException();
        String k = (String)key;
        for (int i = 0; i < k.length(); i++) {
            current = current.children.get(k.charAt(i) - 'A');
            if(current == null) return null;
        }
        return current.value;
    }

    public V put(String key, V value){
        Trie<V> current = this;
        final Stack<Trie<V>> path = new Stack<>();
        for (int i = 0; i < key.length(); i++) {
            path.add(current);
            final int index = key.charAt(i) - 'A';
            final List<Trie<V>> children = current.children;
            if(children.get(index) == null)
                children.set(index, new Trie<>());
            current = children.get(index);
        }
        if(current.hasValue){
            V old = current.value;
            current.value = value;
            return old;
        }
        while(!path.empty()){
            Trie<V> trie = path.pop();
            trie.size++;
        }
        current.size++;
        current.hasValue = true;
        current.value = value;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Trie<V> current = this;
        if(!(key instanceof String))throw new IllegalArgumentException();
        String k = (String)key;
        for (int i = 0; i < k.length(); i++) {
            current = current.children.get(k.charAt(i) - 'A');
            if(current == null) return false;
        }
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        for(var entry : m.entrySet()) put(entry.getKey(), entry.getValue());
    }

    @Override
    public void clear() {
        children = new ArrayList<>(Collections.nCopies(CHARS, null));
        size = 0;
        hasValue = false;
        value = null;
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

}
