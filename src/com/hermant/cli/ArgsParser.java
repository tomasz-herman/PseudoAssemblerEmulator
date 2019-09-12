package com.hermant.cli;

import com.hermant.cli.Options.OptionsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArgsParser {

    private static final Option VERSION = new Option("version", 'v', OptionsBuilder::version);
    private static final Option HELP = new Option("help", 'h', OptionsBuilder::help);
    private static final Option DEBUG = new Option("debug", 'd', OptionsBuilder::debug);
    private static final Option UNSAFE = new Option("unsafe", 'u', OptionsBuilder::unsafe);
    private static final Option BINARY = new Option("binary", 'b', OptionsBuilder::binary);
    private static final Option ABANDON = new Option("abandon", 'a', OptionsBuilder::abandon);
    private static final Option SLEEP = new Option("sleep", 's', OptionsBuilder::sleep);
    private static final Option INPUT = new Option("input", 'i', OptionsBuilder::input);
    private static final Option OUTPUT = new Option("output", 'o', OptionsBuilder::output);

    private static final List<Option> options;
    private static final HashMap<String, Option> longMap;
    private static final HashMap<Character, Option> shortMap;

    static {
        options = Arrays.asList(VERSION, HELP, DEBUG, UNSAFE, BINARY, ABANDON, SLEEP, INPUT, OUTPUT);
        longMap = new HashMap<>();
        shortMap = new HashMap<>();
        for (Option arg : options) {
            longMap.put(arg.LONG, arg);
            shortMap.put(arg.SHORT, arg);
        }
    }

    public static Options parse(String[] args){
        OptionsBuilder options = new OptionsBuilder();
        for(int i = 0; i < args.length; i++){
            String arg = args[i];
            if(arg.startsWith("--"))
                parseLongOption(options, arg.substring(2));
            else if(arg.startsWith("-"))
                i = parseShortOption(options, arg.substring(1), args[i+1]) ? i + 1 : i;
            else throw new IllegalArgumentException("Illegal argument " + arg);
        }
        return options.build();
    }

    private static boolean parseShortOption(OptionsBuilder options, String arg, String next) {
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            Option option = shortMap.get(c);
            if(option == null) throw new IllegalArgumentException("Option -" + c + " unrecognized");
            if(option.VALUE){
                if(i != arg.length() - 1) throw new IllegalArgumentException("Option -" + c + " requires value");
                option.apply(options, next);
                return true;
            } else option.apply(options, "");
        }
        return false;
    }

    private static void parseLongOption(OptionsBuilder options, String arg) {
        String[] opt = arg.split("=", 2);
        Option option = longMap.get(opt[0]);
        if(option == null) throw new IllegalArgumentException("Option --" + opt[0] + " unrecognized");
        if(option.VALUE){
            if(opt.length == 1) throw new IllegalArgumentException("Option --" + opt[0] + " requires value");
            option.apply(options, opt[1]);
        } else {
            if(opt.length == 2) throw new IllegalArgumentException("Option --" + opt[0] + " doesn't requires value");
            option.apply(options, "");
        }
    }

    static final class Option {

        public interface Setter {
            void apply(OptionsBuilder builder, String value);
        }

        public interface Switcher{
            void apply(OptionsBuilder builder);
        }

        private final String LONG;
        private final char SHORT;
        private final boolean VALUE;
        private final Setter SETTER;
        private final Switcher SWITCHER;

        private Option(String aLong, char aShort, Setter setter) {
            LONG = aLong;
            SHORT = aShort;
            VALUE = true;
            SETTER = setter;
            SWITCHER = null;
        }

        private Option(String aLong, char aShort, Switcher switcher) {
            LONG = aLong;
            SHORT = aShort;
            VALUE = false;
            SETTER = null;
            SWITCHER = switcher;
        }

        private void apply(OptionsBuilder builder, String value) {
            if(VALUE) {
                assert SETTER != null;
                SETTER.apply(builder, value);
            }
            else {
                assert SWITCHER != null;
                SWITCHER.apply(builder);
            }
        }
    }
}
