package com.hermant.parser;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @MethodSource("abcStringProvider")
    @ParameterizedTest
    public void oneElement(String argument) {
        Trie<Integer> trie = new Trie<>();
        trie.put(argument, -1);
        assertEquals(-1, trie.get(argument));
        assertEquals(1, trie.size());
    }

    @Test
    public void unaryTrie() {
        Trie<Integer> trie = new Trie<>();
        List<String> list = abcStringProvider().collect(Collectors.toList());
        list.forEach(s -> trie.put(s, s.length() + 1));
        list.forEach(s -> assertEquals(s.length() + 1, trie.get(s)));
        assertEquals(list.size(), trie.size());
    }

    @Test
    public void multipleElements() {
        Trie<Integer> trie = new Trie<>();
        List<String> list = stringProvider().collect(Collectors.toList());
        list.forEach(s -> trie.put(s, s.hashCode() + 3));
        list.forEach(s -> assertEquals(s.hashCode() + 3, trie.get(s)));
        assertEquals(list.size(), trie.size());
    }

    @Test
    public void removeElements() {
        Trie<Integer> trie = new Trie<>();
        List<String> inserted = stringProvider().collect(Collectors.toList());
        int skip = 3;
        int size = inserted.size();
        int limit = size / skip + Math.min(size % skip, 1);

        List<String> removed = Stream.iterate(0, i -> i + skip)
                .limit(limit)
                .map(inserted::get)
                .collect(Collectors.toList());

        List<String> left = inserted.stream().filter(s -> !removed.contains(s)).collect(Collectors.toList());

        inserted.forEach(s -> trie.put(s, s.hashCode() + 3));

        removed.forEach(trie::remove);

        removed.forEach(s -> assertNull(trie.get(s)));
        left.forEach(s -> assertEquals(s.hashCode() + 3, trie.get(s).hashCode()));
        assertEquals(left.size(), trie.size());
    }

    @Test
    public void clearElements() {
        Trie<Integer> trie = new Trie<>();
        Stream<String> stream = stringProvider();
        stream.forEach(s -> trie.put(s, s.hashCode() + 3));
        trie.clear();
        stream = stringProvider();
        stream.forEach(s -> assertNull(trie.get(s)));
        assertTrue(trie.isEmpty());
        assertEquals(0, trie.size());
    }

    @NotNull
    @org.jetbrains.annotations.Contract(pure = true)
    static Stream<String> abcStringProvider() {
        return Stream.of("", "A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG", "ABCDEFGH", "ABCDEFGHI", "ABCDEFGHIJ");
    }

    @NotNull
    @org.jetbrains.annotations.Contract(pure = true)
    static Stream<String> stringProvider() {
        return Stream.of("", "A", "AD", "AS", "AI", "AA", "AN", "ADD", "AND", "ARE", "ANY", "ANT",
                "ALL", "APP", "ADS", "ACT", "ACTS", "ADDS", "ARMY", "ARMS", "ARTS", "AUTO",
                "ABOUT", "ABUSE", "ABOVE", "ADDED", "ADULT", "AGENT", "AGREE", "AGAIN",
                "ADJUST", "AGAINST", "ABILITY", "ACTUALLY", "ACTIVITY", "ADVANCED", "ADVANTAGE",
                "ADDITIONAL", "APPLICABLE", "ABSOLUTELY", "APPLICATION", "ADDITIONALLY",
                "APPRECIATION", "APPROXIMATELY", "ADVERTISEMENTS", "APPRENTICESHIP",
                "ACKNOWLEDGMENTS", "ANTHROPOCENTRICITIES", "ANTIFERROMAGNETICALLY",
                "B", "BE", "BIG", "BOY", "BACK", "BANK", "BEEN", "BROTHER", "BELIEVED");
    }

}