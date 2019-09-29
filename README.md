# Pseudo-Assembler Emulator
This is an emulation environment for Pseudo-Assembler programs. 
It allows to debug or run programs, run them at certain frequency or assemble them into binary form.
Main goal is to provide environment to learn basic programming/assembly languages.
It uses unsafe java class for high speed ram emulation underneath, and its generally high performance.
Whenever it doesn't hurt clarity high performance is priority.
# What is Pseudo-Assembler
Pseudo-Assembler is language taught at my faculty, but without any environment provided to run programs.
This version of Pseudo-Assembler is much more complex than the version my faculty uses, but it's mostly compatible.
I extended functionality of the language to provide asm x86 like experience, but made it more user friendly.
Original Pseudo-Assembler contains twenty-something instructions, while this version has closer to one hundred instructions.
String support, physical stack, byte manipulation, logical instructions, and many utility instructions was added.
Also many functions was simplified for easier use, got more obvious names and instructions with register/memory version was merged into one instruction.
# How to use?
Since program was designed for beginner programmers helper scripts was provided to make easier access to it's functionality.
Java 12+ is required to run emulator.
Since version 3.1.0 graphical interface is provided.
On Windows double-click run.bat to run a program or debug.bat to debug or gui.bat to run emulator in graphical mode.
On Linux/MacOS/Windows(with bash installed) run run.sh/debug.sh/gui.sh in terminal to get started(remember to make it executable).