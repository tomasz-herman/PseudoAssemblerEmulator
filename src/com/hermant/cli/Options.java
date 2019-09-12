package com.hermant.cli;

public final class Options {

    private boolean debug;
    private boolean unsafe;
    private boolean binary;
    private boolean abandon;
    private boolean version;
    private boolean help;
    private int sleep;
    private String input;
    private String output;

    public boolean debug() {
        return debug;
    }

    public boolean unsafe() {
        return unsafe;
    }

    public boolean binary() {
        return binary;
    }

    public boolean abandon() {
        return abandon;
    }

    public boolean version() {
        return version;
    }

    public boolean help() {
        return help;
    }

    public int sleep() {
        return sleep;
    }

    public String input() {
        return input;
    }

    public String output() {
        return output;
    }

    private Options(){ }

    @Override
    public String toString() {
        return "Options:\n" +
                "debug=" + debug + '\n' +
                "unsafe=" + unsafe + '\n' +
                "binary=" + binary + '\n' +
                "abandon=" + abandon + '\n' +
                "version=" + version + '\n' +
                "help=" + help + '\n' +
                "sleep=" + sleep + '\n' +
                "input='" + input + '\'' + '\n' +
                "output='" + output + '\'';
    }

    public static final class OptionsBuilder {
        private boolean debug = false;
        private boolean unsafe = false;
        private boolean binary = false;
        private boolean abandon = false;
        private boolean version = false;
        private boolean help = false;
        private int sleep = 0;
        private String input = "";
        private String output = "";

        void debug(){
            this.debug = true;
        }

        void unsafe(){
            this.unsafe = true;
        }

        void binary(){
            this.binary = true;
        }

        void abandon(){
            this.abandon = true;
        }

        void version(){
            this.version = true;
        }

        void help(){
            this.help = true;
        }

        public void sleep(String sleep){
            try {
                this.sleep = Integer.parseUnsignedInt(sleep);
            } catch(NumberFormatException e){
                e.printStackTrace();
                this.sleep = 0;
            }
        }


        public static void input(OptionsBuilder builder, String input){
            builder.input = input;
        }

        void output(String output){
            this.output = output;
        }

        Options build(){
            if(input.isEmpty())throw new IllegalStateException("Input file must be specified.");
            Options options = new Options();
            options.debug = debug;
            options.unsafe = unsafe;
            options.binary = binary;
            options.abandon = abandon;
            options.version = version;
            options.help = help;
            options.sleep = sleep;
            options.input = input;
            options.output = output;
            return options;
        }
    }
}
