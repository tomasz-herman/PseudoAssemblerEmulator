// Generated from PseudoAssembler.g4 by ANTLR 4.8

package com.hermant.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PseudoAssemblerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, DC=5, DS=6, EXIT=7, SIN=8, RET=9, LOAD=10, 
		ST=11, JUMP=12, JG=13, JL=14, JE=15, JZ=16, ADD=17, SUB=18, MUL=19, PUSH=20, 
		OUT=21, STRING_TYPE=22, BYTE_TYPE=23, INT_TYPE=24, CHAR_TYPE=25, FLOAT_TYPE=26, 
		STRING=27, CHAR=28, EOL=29, COMMENT=30, LABEL=31, ID=32, WS=33, INT=34, 
		SIGNED_INT=35, FLOAT=36, HEX=37, BIN=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "DC", "DS", "EXIT", "SIN", "RET", "LOAD", 
			"ST", "JUMP", "JG", "JL", "JE", "JZ", "ADD", "SUB", "MUL", "PUSH", "OUT", 
			"STRING_TYPE", "BYTE_TYPE", "INT_TYPE", "CHAR_TYPE", "FLOAT_TYPE", "STRING", 
			"CHAR", "EOL", "COMMENT", "LABEL", "ID", "WS", "INT", "SIGNED_INT", "FLOAT", 
			"HEX", "BIN", "SIGN", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", 
			"Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'*'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "DC", "DS", "EXIT", "SIN", "RET", "LOAD", 
			"ST", "JUMP", "JG", "JL", "JE", "JZ", "ADD", "SUB", "MUL", "PUSH", "OUT", 
			"STRING_TYPE", "BYTE_TYPE", "INT_TYPE", "CHAR_TYPE", "FLOAT_TYPE", "STRING", 
			"CHAR", "EOL", "COMMENT", "LABEL", "ID", "WS", "INT", "SIGNED_INT", "FLOAT", 
			"HEX", "BIN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PseudoAssemblerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PseudoAssembler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u018e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00ef\n\34\f\34\16\34\u00f2\13\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\5\35\u00fa\n\35\3\35\3\35\3\36\6\36\u00ff"+
		"\n\36\r\36\16\36\u0100\3\37\3\37\7\37\u0105\n\37\f\37\16\37\u0108\13\37"+
		"\3\37\3\37\3 \3 \7 \u010e\n \f \16 \u0111\13 \3 \3 \3!\3!\7!\u0117\n!"+
		"\f!\16!\u011a\13!\3\"\6\"\u011d\n\"\r\"\16\"\u011e\3\"\3\"\3#\3#\7#\u0125"+
		"\n#\f#\16#\u0128\13#\3#\5#\u012b\n#\3$\5$\u012e\n$\3$\3$\3%\5%\u0133\n"+
		"%\3%\3%\3%\7%\u0138\n%\f%\16%\u013b\13%\5%\u013d\n%\3%\3%\6%\u0141\n%"+
		"\r%\16%\u0142\5%\u0145\n%\3&\3&\3&\3&\7&\u014b\n&\f&\16&\u014e\13&\3\'"+
		"\3\'\3\'\3\'\7\'\u0154\n\'\f\'\16\'\u0157\13\'\3(\3(\3)\3)\3*\3*\3+\3"+
		"+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3"+
		"=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\2\2C\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O\2Q\2S\2U\2W"+
		"\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081"+
		"\2\u0083\2\3\2\'\3\2$$\3\2))\4\2\f\f\17\17\5\2C\\aac|\6\2\62;C\\aac|\4"+
		"\2\13\13\"\"\3\2\63;\3\2\62;\5\2\62;CHch\3\2\62\63\4\2--//\4\2CCcc\4\2"+
		"DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4"+
		"\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUu"+
		"u\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u0184\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3\u0085\3\2\2\2\5\u0087\3\2\2\2\7\u0089"+
		"\3\2\2\2\t\u008b\3\2\2\2\13\u008d\3\2\2\2\r\u0090\3\2\2\2\17\u0093\3\2"+
		"\2\2\21\u0098\3\2\2\2\23\u009c\3\2\2\2\25\u00a0\3\2\2\2\27\u00a3\3\2\2"+
		"\2\31\u00a6\3\2\2\2\33\u00aa\3\2\2\2\35\u00ad\3\2\2\2\37\u00b0\3\2\2\2"+
		"!\u00b3\3\2\2\2#\u00b6\3\2\2\2%\u00ba\3\2\2\2\'\u00be\3\2\2\2)\u00c2\3"+
		"\2\2\2+\u00c7\3\2\2\2-\u00cb\3\2\2\2/\u00d2\3\2\2\2\61\u00d7\3\2\2\2\63"+
		"\u00df\3\2\2\2\65\u00e4\3\2\2\2\67\u00ea\3\2\2\29\u00f5\3\2\2\2;\u00fe"+
		"\3\2\2\2=\u0102\3\2\2\2?\u010b\3\2\2\2A\u0114\3\2\2\2C\u011c\3\2\2\2E"+
		"\u012a\3\2\2\2G\u012d\3\2\2\2I\u0132\3\2\2\2K\u0146\3\2\2\2M\u014f\3\2"+
		"\2\2O\u0158\3\2\2\2Q\u015a\3\2\2\2S\u015c\3\2\2\2U\u015e\3\2\2\2W\u0160"+
		"\3\2\2\2Y\u0162\3\2\2\2[\u0164\3\2\2\2]\u0166\3\2\2\2_\u0168\3\2\2\2a"+
		"\u016a\3\2\2\2c\u016c\3\2\2\2e\u016e\3\2\2\2g\u0170\3\2\2\2i\u0172\3\2"+
		"\2\2k\u0174\3\2\2\2m\u0176\3\2\2\2o\u0178\3\2\2\2q\u017a\3\2\2\2s\u017c"+
		"\3\2\2\2u\u017e\3\2\2\2w\u0180\3\2\2\2y\u0182\3\2\2\2{\u0184\3\2\2\2}"+
		"\u0186\3\2\2\2\177\u0188\3\2\2\2\u0081\u018a\3\2\2\2\u0083\u018c\3\2\2"+
		"\2\u0085\u0086\7.\2\2\u0086\4\3\2\2\2\u0087\u0088\7,\2\2\u0088\6\3\2\2"+
		"\2\u0089\u008a\7*\2\2\u008a\b\3\2\2\2\u008b\u008c\7+\2\2\u008c\n\3\2\2"+
		"\2\u008d\u008e\5W,\2\u008e\u008f\5U+\2\u008f\f\3\2\2\2\u0090\u0091\5W"+
		",\2\u0091\u0092\5u;\2\u0092\16\3\2\2\2\u0093\u0094\5Y-\2\u0094\u0095\5"+
		"\177@\2\u0095\u0096\5a\61\2\u0096\u0097\5w<\2\u0097\20\3\2\2\2\u0098\u0099"+
		"\5u;\2\u0099\u009a\5a\61\2\u009a\u009b\5k\66\2\u009b\22\3\2\2\2\u009c"+
		"\u009d\5s:\2\u009d\u009e\5Y-\2\u009e\u009f\5w<\2\u009f\24\3\2\2\2\u00a0"+
		"\u00a1\5g\64\2\u00a1\u00a2\5W,\2\u00a2\26\3\2\2\2\u00a3\u00a4\5u;\2\u00a4"+
		"\u00a5\5w<\2\u00a5\30\3\2\2\2\u00a6\u00a7\5c\62\2\u00a7\u00a8\5i\65\2"+
		"\u00a8\u00a9\5o8\2\u00a9\32\3\2\2\2\u00aa\u00ab\5c\62\2\u00ab\u00ac\5"+
		"]/\2\u00ac\34\3\2\2\2\u00ad\u00ae\5c\62\2\u00ae\u00af\5g\64\2\u00af\36"+
		"\3\2\2\2\u00b0\u00b1\5c\62\2\u00b1\u00b2\5Y-\2\u00b2 \3\2\2\2\u00b3\u00b4"+
		"\5c\62\2\u00b4\u00b5\5\u0083B\2\u00b5\"\3\2\2\2\u00b6\u00b7\5Q)\2\u00b7"+
		"\u00b8\5W,\2\u00b8\u00b9\5W,\2\u00b9$\3\2\2\2\u00ba\u00bb\5u;\2\u00bb"+
		"\u00bc\5y=\2\u00bc\u00bd\5S*\2\u00bd&\3\2\2\2\u00be\u00bf\5i\65\2\u00bf"+
		"\u00c0\5y=\2\u00c0\u00c1\5g\64\2\u00c1(\3\2\2\2\u00c2\u00c3\5o8\2\u00c3"+
		"\u00c4\5y=\2\u00c4\u00c5\5u;\2\u00c5\u00c6\5_\60\2\u00c6*\3\2\2\2\u00c7"+
		"\u00c8\5m\67\2\u00c8\u00c9\5y=\2\u00c9\u00ca\5w<\2\u00ca,\3\2\2\2\u00cb"+
		"\u00cc\5u;\2\u00cc\u00cd\5w<\2\u00cd\u00ce\5s:\2\u00ce\u00cf\5a\61\2\u00cf"+
		"\u00d0\5k\66\2\u00d0\u00d1\5]/\2\u00d1.\3\2\2\2\u00d2\u00d3\5S*\2\u00d3"+
		"\u00d4\5\u0081A\2\u00d4\u00d5\5w<\2\u00d5\u00d6\5Y-\2\u00d6\60\3\2\2\2"+
		"\u00d7\u00d8\5a\61\2\u00d8\u00d9\5k\66\2\u00d9\u00da\5w<\2\u00da\u00db"+
		"\5Y-\2\u00db\u00dc\5]/\2\u00dc\u00dd\5Y-\2\u00dd\u00de\5s:\2\u00de\62"+
		"\3\2\2\2\u00df\u00e0\5U+\2\u00e0\u00e1\5_\60\2\u00e1\u00e2\5Q)\2\u00e2"+
		"\u00e3\5s:\2\u00e3\64\3\2\2\2\u00e4\u00e5\5[.\2\u00e5\u00e6\5g\64\2\u00e6"+
		"\u00e7\5m\67\2\u00e7\u00e8\5Q)\2\u00e8\u00e9\5w<\2\u00e9\66\3\2\2\2\u00ea"+
		"\u00f0\7$\2\2\u00eb\u00ec\7^\2\2\u00ec\u00ef\13\2\2\2\u00ed\u00ef\n\2"+
		"\2\2\u00ee\u00eb\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f3\u00f4\7$\2\2\u00f48\3\2\2\2\u00f5\u00f9\7)\2\2\u00f6\u00f7"+
		"\7^\2\2\u00f7\u00fa\13\2\2\2\u00f8\u00fa\n\3\2\2\u00f9\u00f6\3\2\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\7)\2\2\u00fc:\3\2\2\2\u00fd"+
		"\u00ff\t\4\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101<\3\2\2\2\u0102\u0106\7=\2\2\u0103\u0105"+
		"\n\4\2\2\u0104\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010a\b\37"+
		"\2\2\u010a>\3\2\2\2\u010b\u010f\t\5\2\2\u010c\u010e\t\6\2\2\u010d\u010c"+
		"\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7<\2\2\u0113@\3\2\2\2\u0114"+
		"\u0118\t\5\2\2\u0115\u0117\t\6\2\2\u0116\u0115\3\2\2\2\u0117\u011a\3\2"+
		"\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119B\3\2\2\2\u011a\u0118"+
		"\3\2\2\2\u011b\u011d\t\7\2\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\b\""+
		"\2\2\u0121D\3\2\2\2\u0122\u0126\t\b\2\2\u0123\u0125\t\t\2\2\u0124\u0123"+
		"\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127"+
		"\u012b\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012b\7\62\2\2\u012a\u0122\3"+
		"\2\2\2\u012a\u0129\3\2\2\2\u012bF\3\2\2\2\u012c\u012e\5O(\2\u012d\u012c"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\5E#\2\u0130"+
		"H\3\2\2\2\u0131\u0133\5O(\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0144\3\2\2\2\u0134\u013c\5E#\2\u0135\u0139\7\60\2\2\u0136\u0138\t\t"+
		"\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u0135\3\2"+
		"\2\2\u013c\u013d\3\2\2\2\u013d\u0145\3\2\2\2\u013e\u0140\7\60\2\2\u013f"+
		"\u0141\t\t\2\2\u0140\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2"+
		"\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0134\3\2\2\2\u0144"+
		"\u013e\3\2\2\2\u0145J\3\2\2\2\u0146\u0147\7\62\2\2\u0147\u0148\7z\2\2"+
		"\u0148\u014c\3\2\2\2\u0149\u014b\t\n\2\2\u014a\u0149\3\2\2\2\u014b\u014e"+
		"\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014dL\3\2\2\2\u014e"+
		"\u014c\3\2\2\2\u014f\u0150\7\62\2\2\u0150\u0151\7d\2\2\u0151\u0155\3\2"+
		"\2\2\u0152\u0154\t\13\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156N\3\2\2\2\u0157\u0155\3\2\2\2"+
		"\u0158\u0159\t\f\2\2\u0159P\3\2\2\2\u015a\u015b\t\r\2\2\u015bR\3\2\2\2"+
		"\u015c\u015d\t\16\2\2\u015dT\3\2\2\2\u015e\u015f\t\17\2\2\u015fV\3\2\2"+
		"\2\u0160\u0161\t\20\2\2\u0161X\3\2\2\2\u0162\u0163\t\21\2\2\u0163Z\3\2"+
		"\2\2\u0164\u0165\t\22\2\2\u0165\\\3\2\2\2\u0166\u0167\t\23\2\2\u0167^"+
		"\3\2\2\2\u0168\u0169\t\24\2\2\u0169`\3\2\2\2\u016a\u016b\t\25\2\2\u016b"+
		"b\3\2\2\2\u016c\u016d\t\26\2\2\u016dd\3\2\2\2\u016e\u016f\t\27\2\2\u016f"+
		"f\3\2\2\2\u0170\u0171\t\30\2\2\u0171h\3\2\2\2\u0172\u0173\t\31\2\2\u0173"+
		"j\3\2\2\2\u0174\u0175\t\32\2\2\u0175l\3\2\2\2\u0176\u0177\t\33\2\2\u0177"+
		"n\3\2\2\2\u0178\u0179\t\34\2\2\u0179p\3\2\2\2\u017a\u017b\t\35\2\2\u017b"+
		"r\3\2\2\2\u017c\u017d\t\36\2\2\u017dt\3\2\2\2\u017e\u017f\t\37\2\2\u017f"+
		"v\3\2\2\2\u0180\u0181\t \2\2\u0181x\3\2\2\2\u0182\u0183\t!\2\2\u0183z"+
		"\3\2\2\2\u0184\u0185\t\"\2\2\u0185|\3\2\2\2\u0186\u0187\t#\2\2\u0187~"+
		"\3\2\2\2\u0188\u0189\t$\2\2\u0189\u0080\3\2\2\2\u018a\u018b\t%\2\2\u018b"+
		"\u0082\3\2\2\2\u018c\u018d\t&\2\2\u018d\u0084\3\2\2\2\25\2\u00ee\u00f0"+
		"\u00f9\u0100\u0106\u010f\u0118\u011e\u0126\u012a\u012d\u0132\u0139\u013c"+
		"\u0142\u0144\u014c\u0155\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}