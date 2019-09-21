@echo off
@echo Specify input file:
set /p InputPath=""
@echo on
java -jar PseudoAssemblerEmulator.jar -di %InputPath%
@echo off
pause