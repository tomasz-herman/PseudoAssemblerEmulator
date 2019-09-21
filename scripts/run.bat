@echo off
@echo Specify input file:
set /p InputPath=""
@echo on
java -jar PseudoAssemblerEmulator.jar -i %InputPath%
@echo off
pause