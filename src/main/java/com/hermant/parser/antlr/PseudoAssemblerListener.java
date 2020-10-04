// Generated from PseudoAssembler.g4 by ANTLR 4.8

package com.hermant.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PseudoAssemblerParser}.
 */
public interface PseudoAssemblerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(PseudoAssemblerParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(PseudoAssemblerParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(PseudoAssemblerParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(PseudoAssemblerParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(PseudoAssemblerParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(PseudoAssemblerParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(PseudoAssemblerParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(PseudoAssemblerParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#no_params_instruction}.
	 * @param ctx the parse tree
	 */
	void enterNo_params_instruction(PseudoAssemblerParser.No_params_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#no_params_instruction}.
	 * @param ctx the parse tree
	 */
	void exitNo_params_instruction(PseudoAssemblerParser.No_params_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_mem_or_reg_reg_instruction}.
	 * @param ctx the parse tree
	 */
	void enterReg_mem_or_reg_reg_instruction(PseudoAssemblerParser.Reg_mem_or_reg_reg_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_mem_or_reg_reg_instruction}.
	 * @param ctx the parse tree
	 */
	void exitReg_mem_or_reg_reg_instruction(PseudoAssemblerParser.Reg_mem_or_reg_reg_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_mem_instruction}.
	 * @param ctx the parse tree
	 */
	void enterReg_mem_instruction(PseudoAssemblerParser.Reg_mem_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_mem_instruction}.
	 * @param ctx the parse tree
	 */
	void exitReg_mem_instruction(PseudoAssemblerParser.Reg_mem_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_or_mem_instruction}.
	 * @param ctx the parse tree
	 */
	void enterReg_or_mem_instruction(PseudoAssemblerParser.Reg_or_mem_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_or_mem_instruction}.
	 * @param ctx the parse tree
	 */
	void exitReg_or_mem_instruction(PseudoAssemblerParser.Reg_or_mem_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_instruction}.
	 * @param ctx the parse tree
	 */
	void enterReg_instruction(PseudoAssemblerParser.Reg_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_instruction}.
	 * @param ctx the parse tree
	 */
	void exitReg_instruction(PseudoAssemblerParser.Reg_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#mem_instruction}.
	 * @param ctx the parse tree
	 */
	void enterMem_instruction(PseudoAssemblerParser.Mem_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#mem_instruction}.
	 * @param ctx the parse tree
	 */
	void exitMem_instruction(PseudoAssemblerParser.Mem_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(PseudoAssemblerParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(PseudoAssemblerParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#const_declaration}.
	 * @param ctx the parse tree
	 */
	void enterConst_declaration(PseudoAssemblerParser.Const_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#const_declaration}.
	 * @param ctx the parse tree
	 */
	void exitConst_declaration(PseudoAssemblerParser.Const_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#space_declaration}.
	 * @param ctx the parse tree
	 */
	void enterSpace_declaration(PseudoAssemblerParser.Space_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#space_declaration}.
	 * @param ctx the parse tree
	 */
	void exitSpace_declaration(PseudoAssemblerParser.Space_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#string_declaration}.
	 * @param ctx the parse tree
	 */
	void enterString_declaration(PseudoAssemblerParser.String_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#string_declaration}.
	 * @param ctx the parse tree
	 */
	void exitString_declaration(PseudoAssemblerParser.String_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#byte_val}.
	 * @param ctx the parse tree
	 */
	void enterByte_val(PseudoAssemblerParser.Byte_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#byte_val}.
	 * @param ctx the parse tree
	 */
	void exitByte_val(PseudoAssemblerParser.Byte_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#int_val}.
	 * @param ctx the parse tree
	 */
	void enterInt_val(PseudoAssemblerParser.Int_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#int_val}.
	 * @param ctx the parse tree
	 */
	void exitInt_val(PseudoAssemblerParser.Int_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#float_val}.
	 * @param ctx the parse tree
	 */
	void enterFloat_val(PseudoAssemblerParser.Float_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#float_val}.
	 * @param ctx the parse tree
	 */
	void exitFloat_val(PseudoAssemblerParser.Float_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg}.
	 * @param ctx the parse tree
	 */
	void enterReg(PseudoAssemblerParser.RegContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg}.
	 * @param ctx the parse tree
	 */
	void exitReg(PseudoAssemblerParser.RegContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#mem}.
	 * @param ctx the parse tree
	 */
	void enterMem(PseudoAssemblerParser.MemContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#mem}.
	 * @param ctx the parse tree
	 */
	void exitMem(PseudoAssemblerParser.MemContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#no_params_op_code}.
	 * @param ctx the parse tree
	 */
	void enterNo_params_op_code(PseudoAssemblerParser.No_params_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#no_params_op_code}.
	 * @param ctx the parse tree
	 */
	void exitNo_params_op_code(PseudoAssemblerParser.No_params_op_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_op_code}.
	 * @param ctx the parse tree
	 */
	void enterReg_op_code(PseudoAssemblerParser.Reg_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_op_code}.
	 * @param ctx the parse tree
	 */
	void exitReg_op_code(PseudoAssemblerParser.Reg_op_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#mem_op_code}.
	 * @param ctx the parse tree
	 */
	void enterMem_op_code(PseudoAssemblerParser.Mem_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#mem_op_code}.
	 * @param ctx the parse tree
	 */
	void exitMem_op_code(PseudoAssemblerParser.Mem_op_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_mem_or_reg_reg_op_code}.
	 * @param ctx the parse tree
	 */
	void enterReg_mem_or_reg_reg_op_code(PseudoAssemblerParser.Reg_mem_or_reg_reg_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_mem_or_reg_reg_op_code}.
	 * @param ctx the parse tree
	 */
	void exitReg_mem_or_reg_reg_op_code(PseudoAssemblerParser.Reg_mem_or_reg_reg_op_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_or_mem_op_code}.
	 * @param ctx the parse tree
	 */
	void enterReg_or_mem_op_code(PseudoAssemblerParser.Reg_or_mem_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_or_mem_op_code}.
	 * @param ctx the parse tree
	 */
	void exitReg_or_mem_op_code(PseudoAssemblerParser.Reg_or_mem_op_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PseudoAssemblerParser#reg_mem_op_code}.
	 * @param ctx the parse tree
	 */
	void enterReg_mem_op_code(PseudoAssemblerParser.Reg_mem_op_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PseudoAssemblerParser#reg_mem_op_code}.
	 * @param ctx the parse tree
	 */
	void exitReg_mem_op_code(PseudoAssemblerParser.Reg_mem_op_codeContext ctx);
}