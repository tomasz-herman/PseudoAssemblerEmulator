// Generated from PseudoAssembler.g4 by ANTLR 4.8

package com.hermant.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PseudoAssemblerParser extends Parser {
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
	public static final int
		RULE_prog = 0, RULE_line = 1, RULE_label = 2, RULE_instruction = 3, RULE_no_params_instruction = 4, 
		RULE_reg_mem_or_reg_reg_instruction = 5, RULE_reg_mem_instruction = 6, 
		RULE_reg_or_mem_instruction = 7, RULE_reg_instruction = 8, RULE_mem_instruction = 9, 
		RULE_declaration = 10, RULE_const_declaration = 11, RULE_space_declaration = 12, 
		RULE_string_declaration = 13, RULE_byte_val = 14, RULE_int_val = 15, RULE_float_val = 16, 
		RULE_reg = 17, RULE_mem = 18, RULE_no_params_op_code = 19, RULE_reg_op_code = 20, 
		RULE_mem_op_code = 21, RULE_reg_mem_or_reg_reg_op_code = 22, RULE_reg_or_mem_op_code = 23, 
		RULE_reg_mem_op_code = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "line", "label", "instruction", "no_params_instruction", "reg_mem_or_reg_reg_instruction", 
			"reg_mem_instruction", "reg_or_mem_instruction", "reg_instruction", "mem_instruction", 
			"declaration", "const_declaration", "space_declaration", "string_declaration", 
			"byte_val", "int_val", "float_val", "reg", "mem", "no_params_op_code", 
			"reg_op_code", "mem_op_code", "reg_mem_or_reg_reg_op_code", "reg_or_mem_op_code", 
			"reg_mem_op_code"
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

	@Override
	public String getGrammarFileName() { return "PseudoAssembler.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PseudoAssemblerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PseudoAssemblerParser.EOF, 0); }
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(PseudoAssemblerParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(PseudoAssemblerParser.EOL, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DC) | (1L << DS) | (1L << EXIT) | (1L << SIN) | (1L << RET) | (1L << LOAD) | (1L << ST) | (1L << JUMP) | (1L << JG) | (1L << JL) | (1L << JE) | (1L << JZ) | (1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << PUSH) | (1L << OUT) | (1L << LABEL))) != 0)) {
				{
				setState(50);
				line();
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(53);
				match(EOL);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DC) | (1L << DS) | (1L << EXIT) | (1L << SIN) | (1L << RET) | (1L << LOAD) | (1L << ST) | (1L << JUMP) | (1L << JG) | (1L << JL) | (1L << JE) | (1L << JZ) | (1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << PUSH) | (1L << OUT) | (1L << LABEL))) != 0)) {
					{
					setState(54);
					line();
					}
				}

				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LABEL) {
					{
					{
					setState(64);
					label();
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(70);
				instruction();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LABEL) {
					{
					{
					setState(71);
					label();
					}
					}
					setState(76);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(77);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(78);
					label();
					}
					}
					setState(81); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LABEL );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(PseudoAssemblerParser.LABEL, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(LABEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public No_params_instructionContext no_params_instruction() {
			return getRuleContext(No_params_instructionContext.class,0);
		}
		public Reg_instructionContext reg_instruction() {
			return getRuleContext(Reg_instructionContext.class,0);
		}
		public Mem_instructionContext mem_instruction() {
			return getRuleContext(Mem_instructionContext.class,0);
		}
		public Reg_or_mem_instructionContext reg_or_mem_instruction() {
			return getRuleContext(Reg_or_mem_instructionContext.class,0);
		}
		public Reg_mem_instructionContext reg_mem_instruction() {
			return getRuleContext(Reg_mem_instructionContext.class,0);
		}
		public Reg_mem_or_reg_reg_instructionContext reg_mem_or_reg_reg_instruction() {
			return getRuleContext(Reg_mem_or_reg_reg_instructionContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instruction);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXIT:
			case RET:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				no_params_instruction();
				}
				break;
			case SIN:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				reg_instruction();
				}
				break;
			case JUMP:
			case JG:
			case JL:
			case JE:
			case JZ:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				mem_instruction();
				}
				break;
			case PUSH:
			case OUT:
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				reg_or_mem_instruction();
				}
				break;
			case LOAD:
			case ST:
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				reg_mem_instruction();
				}
				break;
			case ADD:
			case SUB:
			case MUL:
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				reg_mem_or_reg_reg_instruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class No_params_instructionContext extends ParserRuleContext {
		public No_params_op_codeContext no_params_op_code() {
			return getRuleContext(No_params_op_codeContext.class,0);
		}
		public No_params_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_params_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterNo_params_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitNo_params_instruction(this);
		}
	}

	public final No_params_instructionContext no_params_instruction() throws RecognitionException {
		No_params_instructionContext _localctx = new No_params_instructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_no_params_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			no_params_op_code();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_mem_or_reg_reg_instructionContext extends ParserRuleContext {
		public Reg_mem_or_reg_reg_op_codeContext reg_mem_or_reg_reg_op_code() {
			return getRuleContext(Reg_mem_or_reg_reg_op_codeContext.class,0);
		}
		public List<RegContext> reg() {
			return getRuleContexts(RegContext.class);
		}
		public RegContext reg(int i) {
			return getRuleContext(RegContext.class,i);
		}
		public MemContext mem() {
			return getRuleContext(MemContext.class,0);
		}
		public Reg_mem_or_reg_reg_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_mem_or_reg_reg_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_mem_or_reg_reg_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_mem_or_reg_reg_instruction(this);
		}
	}

	public final Reg_mem_or_reg_reg_instructionContext reg_mem_or_reg_reg_instruction() throws RecognitionException {
		Reg_mem_or_reg_reg_instructionContext _localctx = new Reg_mem_or_reg_reg_instructionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reg_mem_or_reg_reg_instruction);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				reg_mem_or_reg_reg_op_code();
				setState(98);
				reg();
				setState(99);
				match(T__0);
				setState(100);
				mem();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				reg_mem_or_reg_reg_op_code();
				setState(103);
				reg();
				setState(104);
				match(T__0);
				setState(105);
				reg();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_mem_instructionContext extends ParserRuleContext {
		public Reg_mem_op_codeContext reg_mem_op_code() {
			return getRuleContext(Reg_mem_op_codeContext.class,0);
		}
		public RegContext reg() {
			return getRuleContext(RegContext.class,0);
		}
		public MemContext mem() {
			return getRuleContext(MemContext.class,0);
		}
		public Reg_mem_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_mem_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_mem_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_mem_instruction(this);
		}
	}

	public final Reg_mem_instructionContext reg_mem_instruction() throws RecognitionException {
		Reg_mem_instructionContext _localctx = new Reg_mem_instructionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reg_mem_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			reg_mem_op_code();
			setState(110);
			reg();
			setState(111);
			match(T__0);
			setState(112);
			mem();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_or_mem_instructionContext extends ParserRuleContext {
		public Reg_or_mem_op_codeContext reg_or_mem_op_code() {
			return getRuleContext(Reg_or_mem_op_codeContext.class,0);
		}
		public MemContext mem() {
			return getRuleContext(MemContext.class,0);
		}
		public RegContext reg() {
			return getRuleContext(RegContext.class,0);
		}
		public Reg_or_mem_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_or_mem_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_or_mem_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_or_mem_instruction(this);
		}
	}

	public final Reg_or_mem_instructionContext reg_or_mem_instruction() throws RecognitionException {
		Reg_or_mem_instructionContext _localctx = new Reg_or_mem_instructionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_reg_or_mem_instruction);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				reg_or_mem_op_code();
				setState(115);
				mem();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				reg_or_mem_op_code();
				setState(118);
				reg();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_instructionContext extends ParserRuleContext {
		public Reg_op_codeContext reg_op_code() {
			return getRuleContext(Reg_op_codeContext.class,0);
		}
		public RegContext reg() {
			return getRuleContext(RegContext.class,0);
		}
		public Reg_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_instruction(this);
		}
	}

	public final Reg_instructionContext reg_instruction() throws RecognitionException {
		Reg_instructionContext _localctx = new Reg_instructionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reg_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			reg_op_code();
			setState(123);
			reg();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mem_instructionContext extends ParserRuleContext {
		public Mem_op_codeContext mem_op_code() {
			return getRuleContext(Mem_op_codeContext.class,0);
		}
		public MemContext mem() {
			return getRuleContext(MemContext.class,0);
		}
		public Mem_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mem_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterMem_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitMem_instruction(this);
		}
	}

	public final Mem_instructionContext mem_instruction() throws RecognitionException {
		Mem_instructionContext _localctx = new Mem_instructionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_mem_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			mem_op_code();
			setState(126);
			mem();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Const_declarationContext const_declaration() {
			return getRuleContext(Const_declarationContext.class,0);
		}
		public Space_declarationContext space_declaration() {
			return getRuleContext(Space_declarationContext.class,0);
		}
		public String_declarationContext string_declaration() {
			return getRuleContext(String_declarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaration);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				const_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				space_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				string_declaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Const_declarationContext extends ParserRuleContext {
		public TerminalNode DC() { return getToken(PseudoAssemblerParser.DC, 0); }
		public TerminalNode BYTE_TYPE() { return getToken(PseudoAssemblerParser.BYTE_TYPE, 0); }
		public Byte_valContext byte_val() {
			return getRuleContext(Byte_valContext.class,0);
		}
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode INT_TYPE() { return getToken(PseudoAssemblerParser.INT_TYPE, 0); }
		public Int_valContext int_val() {
			return getRuleContext(Int_valContext.class,0);
		}
		public TerminalNode FLOAT_TYPE() { return getToken(PseudoAssemblerParser.FLOAT_TYPE, 0); }
		public Float_valContext float_val() {
			return getRuleContext(Float_valContext.class,0);
		}
		public TerminalNode CHAR_TYPE() { return getToken(PseudoAssemblerParser.CHAR_TYPE, 0); }
		public TerminalNode CHAR() { return getToken(PseudoAssemblerParser.CHAR, 0); }
		public Const_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterConst_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitConst_declaration(this);
		}
	}

	public final Const_declarationContext const_declaration() throws RecognitionException {
		Const_declarationContext _localctx = new Const_declarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_const_declaration);
		int _la;
		try {
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(DC);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(134);
					match(INT);
					setState(135);
					match(T__1);
					}
				}

				setState(138);
				match(BYTE_TYPE);
				setState(139);
				match(T__2);
				setState(140);
				byte_val();
				setState(141);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(DC);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(144);
					match(INT);
					setState(145);
					match(T__1);
					}
				}

				setState(148);
				match(INT_TYPE);
				setState(149);
				match(T__2);
				setState(150);
				int_val();
				setState(151);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(DC);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(154);
					match(INT);
					setState(155);
					match(T__1);
					}
				}

				setState(158);
				match(FLOAT_TYPE);
				setState(159);
				match(T__2);
				setState(160);
				float_val();
				setState(161);
				match(T__3);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(DC);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(164);
					match(INT);
					setState(165);
					match(T__1);
					}
				}

				setState(168);
				match(CHAR_TYPE);
				setState(169);
				match(T__2);
				setState(170);
				match(CHAR);
				setState(171);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Space_declarationContext extends ParserRuleContext {
		public TerminalNode DS() { return getToken(PseudoAssemblerParser.DS, 0); }
		public TerminalNode BYTE_TYPE() { return getToken(PseudoAssemblerParser.BYTE_TYPE, 0); }
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode INT_TYPE() { return getToken(PseudoAssemblerParser.INT_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(PseudoAssemblerParser.FLOAT_TYPE, 0); }
		public TerminalNode CHAR_TYPE() { return getToken(PseudoAssemblerParser.CHAR_TYPE, 0); }
		public Space_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterSpace_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitSpace_declaration(this);
		}
	}

	public final Space_declarationContext space_declaration() throws RecognitionException {
		Space_declarationContext _localctx = new Space_declarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_space_declaration);
		int _la;
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(DS);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(175);
					match(INT);
					setState(176);
					match(T__1);
					}
				}

				setState(179);
				match(BYTE_TYPE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(DS);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(181);
					match(INT);
					setState(182);
					match(T__1);
					}
				}

				setState(185);
				match(INT_TYPE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				match(DS);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(187);
					match(INT);
					setState(188);
					match(T__1);
					}
				}

				setState(191);
				match(FLOAT_TYPE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(192);
				match(DS);
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(193);
					match(INT);
					setState(194);
					match(T__1);
					}
				}

				setState(197);
				match(CHAR_TYPE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_declarationContext extends ParserRuleContext {
		public TerminalNode DC() { return getToken(PseudoAssemblerParser.DC, 0); }
		public TerminalNode STRING_TYPE() { return getToken(PseudoAssemblerParser.STRING_TYPE, 0); }
		public TerminalNode STRING() { return getToken(PseudoAssemblerParser.STRING, 0); }
		public String_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterString_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitString_declaration(this);
		}
	}

	public final String_declarationContext string_declaration() throws RecognitionException {
		String_declarationContext _localctx = new String_declarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_string_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(DC);
			setState(201);
			match(STRING_TYPE);
			setState(202);
			match(T__2);
			setState(203);
			match(STRING);
			setState(204);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Byte_valContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode BIN() { return getToken(PseudoAssemblerParser.BIN, 0); }
		public TerminalNode HEX() { return getToken(PseudoAssemblerParser.HEX, 0); }
		public Byte_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterByte_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitByte_val(this);
		}
	}

	public final Byte_valContext byte_val() throws RecognitionException {
		Byte_valContext _localctx = new Byte_valContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_byte_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_valContext extends ParserRuleContext {
		public TerminalNode SIGNED_INT() { return getToken(PseudoAssemblerParser.SIGNED_INT, 0); }
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode BIN() { return getToken(PseudoAssemblerParser.BIN, 0); }
		public TerminalNode HEX() { return getToken(PseudoAssemblerParser.HEX, 0); }
		public Int_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterInt_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitInt_val(this);
		}
	}

	public final Int_valContext int_val() throws RecognitionException {
		Int_valContext _localctx = new Int_valContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_int_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << SIGNED_INT) | (1L << HEX) | (1L << BIN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Float_valContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(PseudoAssemblerParser.FLOAT, 0); }
		public TerminalNode SIGNED_INT() { return getToken(PseudoAssemblerParser.SIGNED_INT, 0); }
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode BIN() { return getToken(PseudoAssemblerParser.BIN, 0); }
		public TerminalNode HEX() { return getToken(PseudoAssemblerParser.HEX, 0); }
		public Float_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterFloat_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitFloat_val(this);
		}
	}

	public final Float_valContext float_val() throws RecognitionException {
		Float_valContext _localctx = new Float_valContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_float_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << SIGNED_INT) | (1L << FLOAT) | (1L << HEX) | (1L << BIN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public RegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg(this);
		}
	}

	public final RegContext reg() throws RecognitionException {
		RegContext _localctx = new RegContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_reg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(PseudoAssemblerParser.INT, 0); }
		public TerminalNode SIGNED_INT() { return getToken(PseudoAssemblerParser.SIGNED_INT, 0); }
		public TerminalNode ID() { return getToken(PseudoAssemblerParser.ID, 0); }
		public MemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterMem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitMem(this);
		}
	}

	public final MemContext mem() throws RecognitionException {
		MemContext _localctx = new MemContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_mem);
		try {
			setState(219);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(INT);
				setState(215);
				match(T__2);
				setState(216);
				match(SIGNED_INT);
				setState(217);
				match(T__3);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class No_params_op_codeContext extends ParserRuleContext {
		public TerminalNode EXIT() { return getToken(PseudoAssemblerParser.EXIT, 0); }
		public TerminalNode RET() { return getToken(PseudoAssemblerParser.RET, 0); }
		public No_params_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_params_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterNo_params_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitNo_params_op_code(this);
		}
	}

	public final No_params_op_codeContext no_params_op_code() throws RecognitionException {
		No_params_op_codeContext _localctx = new No_params_op_codeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_no_params_op_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_la = _input.LA(1);
			if ( !(_la==EXIT || _la==RET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_op_codeContext extends ParserRuleContext {
		public TerminalNode SIN() { return getToken(PseudoAssemblerParser.SIN, 0); }
		public Reg_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_op_code(this);
		}
	}

	public final Reg_op_codeContext reg_op_code() throws RecognitionException {
		Reg_op_codeContext _localctx = new Reg_op_codeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_reg_op_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(SIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mem_op_codeContext extends ParserRuleContext {
		public TerminalNode JUMP() { return getToken(PseudoAssemblerParser.JUMP, 0); }
		public TerminalNode JG() { return getToken(PseudoAssemblerParser.JG, 0); }
		public TerminalNode JE() { return getToken(PseudoAssemblerParser.JE, 0); }
		public TerminalNode JL() { return getToken(PseudoAssemblerParser.JL, 0); }
		public TerminalNode JZ() { return getToken(PseudoAssemblerParser.JZ, 0); }
		public Mem_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mem_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterMem_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitMem_op_code(this);
		}
	}

	public final Mem_op_codeContext mem_op_code() throws RecognitionException {
		Mem_op_codeContext _localctx = new Mem_op_codeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_mem_op_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << JUMP) | (1L << JG) | (1L << JL) | (1L << JE) | (1L << JZ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_mem_or_reg_reg_op_codeContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(PseudoAssemblerParser.ADD, 0); }
		public TerminalNode MUL() { return getToken(PseudoAssemblerParser.MUL, 0); }
		public TerminalNode SUB() { return getToken(PseudoAssemblerParser.SUB, 0); }
		public Reg_mem_or_reg_reg_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_mem_or_reg_reg_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_mem_or_reg_reg_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_mem_or_reg_reg_op_code(this);
		}
	}

	public final Reg_mem_or_reg_reg_op_codeContext reg_mem_or_reg_reg_op_code() throws RecognitionException {
		Reg_mem_or_reg_reg_op_codeContext _localctx = new Reg_mem_or_reg_reg_op_codeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_reg_mem_or_reg_reg_op_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MUL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_or_mem_op_codeContext extends ParserRuleContext {
		public TerminalNode PUSH() { return getToken(PseudoAssemblerParser.PUSH, 0); }
		public TerminalNode OUT() { return getToken(PseudoAssemblerParser.OUT, 0); }
		public Reg_or_mem_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_or_mem_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_or_mem_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_or_mem_op_code(this);
		}
	}

	public final Reg_or_mem_op_codeContext reg_or_mem_op_code() throws RecognitionException {
		Reg_or_mem_op_codeContext _localctx = new Reg_or_mem_op_codeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_reg_or_mem_op_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			_la = _input.LA(1);
			if ( !(_la==PUSH || _la==OUT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_mem_op_codeContext extends ParserRuleContext {
		public TerminalNode LOAD() { return getToken(PseudoAssemblerParser.LOAD, 0); }
		public TerminalNode ST() { return getToken(PseudoAssemblerParser.ST, 0); }
		public Reg_mem_op_codeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_mem_op_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).enterReg_mem_op_code(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PseudoAssemblerListener ) ((PseudoAssemblerListener)listener).exitReg_mem_op_code(this);
		}
	}

	public final Reg_mem_op_codeContext reg_mem_op_code() throws RecognitionException {
		Reg_mem_op_codeContext _localctx = new Reg_mem_op_codeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_reg_mem_op_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_la = _input.LA(1);
			if ( !(_la==LOAD || _la==ST) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00ec\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\5\2\66\n\2\3\2\3\2\5\2:\n\2\7\2<\n\2\f\2\16\2?\13\2\3\2"+
		"\3\2\3\3\7\3D\n\3\f\3\16\3G\13\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3\3"+
		"\3\6\3R\n\3\r\3\16\3S\5\3V\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5`\n"+
		"\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7n\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t{\n\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\5\f\u0086\n\f\3\r\3\r\3\r\5\r\u008b\n\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\5\r\u0095\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009f"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a9\n\r\3\r\3\r\3\r\3\r\5\r"+
		"\u00af\n\r\3\16\3\16\3\16\5\16\u00b4\n\16\3\16\3\16\3\16\3\16\5\16\u00ba"+
		"\n\16\3\16\3\16\3\16\3\16\5\16\u00c0\n\16\3\16\3\16\3\16\3\16\5\16\u00c6"+
		"\n\16\3\16\5\16\u00c9\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u00de\n\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\2\2\33\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\n\4\2$$\'(\4\2"+
		"$%\'(\3\2$(\4\2\t\t\13\13\3\2\16\22\3\2\23\25\3\2\26\27\3\2\f\r\2\u00f2"+
		"\2\65\3\2\2\2\4U\3\2\2\2\6W\3\2\2\2\b_\3\2\2\2\na\3\2\2\2\fm\3\2\2\2\16"+
		"o\3\2\2\2\20z\3\2\2\2\22|\3\2\2\2\24\177\3\2\2\2\26\u0085\3\2\2\2\30\u00ae"+
		"\3\2\2\2\32\u00c8\3\2\2\2\34\u00ca\3\2\2\2\36\u00d0\3\2\2\2 \u00d2\3\2"+
		"\2\2\"\u00d4\3\2\2\2$\u00d6\3\2\2\2&\u00dd\3\2\2\2(\u00df\3\2\2\2*\u00e1"+
		"\3\2\2\2,\u00e3\3\2\2\2.\u00e5\3\2\2\2\60\u00e7\3\2\2\2\62\u00e9\3\2\2"+
		"\2\64\66\5\4\3\2\65\64\3\2\2\2\65\66\3\2\2\2\66=\3\2\2\2\679\7\37\2\2"+
		"8:\5\4\3\298\3\2\2\29:\3\2\2\2:<\3\2\2\2;\67\3\2\2\2<?\3\2\2\2=;\3\2\2"+
		"\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\2\2\3A\3\3\2\2\2BD\5\6\4\2CB\3\2"+
		"\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2HV\5\b\5\2IK\5\6"+
		"\4\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OV\5\26"+
		"\f\2PR\5\6\4\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2UE\3\2"+
		"\2\2UL\3\2\2\2UQ\3\2\2\2V\5\3\2\2\2WX\7!\2\2X\7\3\2\2\2Y`\5\n\6\2Z`\5"+
		"\22\n\2[`\5\24\13\2\\`\5\20\t\2]`\5\16\b\2^`\5\f\7\2_Y\3\2\2\2_Z\3\2\2"+
		"\2_[\3\2\2\2_\\\3\2\2\2_]\3\2\2\2_^\3\2\2\2`\t\3\2\2\2ab\5(\25\2b\13\3"+
		"\2\2\2cd\5.\30\2de\5$\23\2ef\7\3\2\2fg\5&\24\2gn\3\2\2\2hi\5.\30\2ij\5"+
		"$\23\2jk\7\3\2\2kl\5$\23\2ln\3\2\2\2mc\3\2\2\2mh\3\2\2\2n\r\3\2\2\2op"+
		"\5\62\32\2pq\5$\23\2qr\7\3\2\2rs\5&\24\2s\17\3\2\2\2tu\5\60\31\2uv\5&"+
		"\24\2v{\3\2\2\2wx\5\60\31\2xy\5$\23\2y{\3\2\2\2zt\3\2\2\2zw\3\2\2\2{\21"+
		"\3\2\2\2|}\5*\26\2}~\5$\23\2~\23\3\2\2\2\177\u0080\5,\27\2\u0080\u0081"+
		"\5&\24\2\u0081\25\3\2\2\2\u0082\u0086\5\30\r\2\u0083\u0086\5\32\16\2\u0084"+
		"\u0086\5\34\17\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0084\3"+
		"\2\2\2\u0086\27\3\2\2\2\u0087\u008a\7\7\2\2\u0088\u0089\7$\2\2\u0089\u008b"+
		"\7\4\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008d\7\31\2\2\u008d\u008e\7\5\2\2\u008e\u008f\5\36\20\2\u008f\u0090"+
		"\7\6\2\2\u0090\u00af\3\2\2\2\u0091\u0094\7\7\2\2\u0092\u0093\7$\2\2\u0093"+
		"\u0095\7\4\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0097\7\32\2\2\u0097\u0098\7\5\2\2\u0098\u0099\5 \21\2\u0099"+
		"\u009a\7\6\2\2\u009a\u00af\3\2\2\2\u009b\u009e\7\7\2\2\u009c\u009d\7$"+
		"\2\2\u009d\u009f\7\4\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\7\34\2\2\u00a1\u00a2\7\5\2\2\u00a2\u00a3\5"+
		"\"\22\2\u00a3\u00a4\7\6\2\2\u00a4\u00af\3\2\2\2\u00a5\u00a8\7\7\2\2\u00a6"+
		"\u00a7\7$\2\2\u00a7\u00a9\7\4\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\33\2\2\u00ab\u00ac\7\5\2\2\u00ac"+
		"\u00ad\7\36\2\2\u00ad\u00af\7\6\2\2\u00ae\u0087\3\2\2\2\u00ae\u0091\3"+
		"\2\2\2\u00ae\u009b\3\2\2\2\u00ae\u00a5\3\2\2\2\u00af\31\3\2\2\2\u00b0"+
		"\u00b3\7\b\2\2\u00b1\u00b2\7$\2\2\u00b2\u00b4\7\4\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00c9\7\31\2\2\u00b6"+
		"\u00b9\7\b\2\2\u00b7\u00b8\7$\2\2\u00b8\u00ba\7\4\2\2\u00b9\u00b7\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00c9\7\32\2\2\u00bc"+
		"\u00bf\7\b\2\2\u00bd\u00be\7$\2\2\u00be\u00c0\7\4\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c9\7\34\2\2\u00c2"+
		"\u00c5\7\b\2\2\u00c3\u00c4\7$\2\2\u00c4\u00c6\7\4\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\7\33\2\2\u00c8"+
		"\u00b0\3\2\2\2\u00c8\u00b6\3\2\2\2\u00c8\u00bc\3\2\2\2\u00c8\u00c2\3\2"+
		"\2\2\u00c9\33\3\2\2\2\u00ca\u00cb\7\7\2\2\u00cb\u00cc\7\30\2\2\u00cc\u00cd"+
		"\7\5\2\2\u00cd\u00ce\7\35\2\2\u00ce\u00cf\7\6\2\2\u00cf\35\3\2\2\2\u00d0"+
		"\u00d1\t\2\2\2\u00d1\37\3\2\2\2\u00d2\u00d3\t\3\2\2\u00d3!\3\2\2\2\u00d4"+
		"\u00d5\t\4\2\2\u00d5#\3\2\2\2\u00d6\u00d7\7$\2\2\u00d7%\3\2\2\2\u00d8"+
		"\u00d9\7$\2\2\u00d9\u00da\7\5\2\2\u00da\u00db\7%\2\2\u00db\u00de\7\6\2"+
		"\2\u00dc\u00de\7\"\2\2\u00dd\u00d8\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de\'"+
		"\3\2\2\2\u00df\u00e0\t\5\2\2\u00e0)\3\2\2\2\u00e1\u00e2\7\n\2\2\u00e2"+
		"+\3\2\2\2\u00e3\u00e4\t\6\2\2\u00e4-\3\2\2\2\u00e5\u00e6\t\7\2\2\u00e6"+
		"/\3\2\2\2\u00e7\u00e8\t\b\2\2\u00e8\61\3\2\2\2\u00e9\u00ea\t\t\2\2\u00ea"+
		"\63\3\2\2\2\30\659=ELSU_mz\u0085\u008a\u0094\u009e\u00a8\u00ae\u00b3\u00b9"+
		"\u00bf\u00c5\u00c8\u00dd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}