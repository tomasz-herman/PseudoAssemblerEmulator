#!/bin/bash
echo Specify input file:
read -r InputPath
java -jar PseudoAssemblerEmulator.jar -i "$InputPath"
read -n 1 -s -r -p "Press any key to continue..."
echo