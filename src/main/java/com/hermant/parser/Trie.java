package com.hermant.parser;

import java.util.*;

public class Trie<V> extends AbstractMap<String, V> implements Map<String, V> {

    private static final int CHARS = 26;

    private boolean hasValue = false;
    private V value = null;
    private List<Trie<V>> children;
    private int size = 0;
    private int parent;

    Trie() {
        children = new ArrayList<>(Collections.nCopies(CHARS, null));
    }

    @Override
    public V get(Object key) {
        Trie<V> current = this;
        if(!(key instanceof String))throw new IllegalArgumentException();
        String k = (String)key;
        for (int i = 0; i < k.length(); i++) {
            int idx = k.charAt(i) - 'A';
            if(idx < 0 || idx >= CHARS) return null;
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
            current.parent = index;
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
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if(value.equals(this.value)) return true;
        for (Trie<V> trie : children) {
            if(trie == null) continue;
            if(trie.containsValue(value)) return true;
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        if(!(key instanceof String))throw new IllegalArgumentException();
        String k = (String) key;
        Trie<V> current = this;
        final Stack<Trie<V>> path = new Stack<>();
        for (int i = 0; i < k.length(); i++) {
            path.add(current);
            final int index = k.charAt(i) - 'A';
            final List<Trie<V>> children = current.children;
            if(children.get(index) == null)
                return null;
            current = children.get(index);
        }
        if(current.hasValue){
            V old = current.value;
            current.value = null;
            current.size--;
            current.hasValue = false;
            while(!path.empty()){
                Trie<V> trie = path.pop();
                if(current.size == 0) trie.children.set(current.parent, null);
                trie.size--;
                current = trie;
            }
            return old;
        }
        return null;
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
