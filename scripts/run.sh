#!/bin/bash
echo Specify input file:
read -r InputPath
java -jar PseudoAssemblerEmulator.jar -i "$InputPath"