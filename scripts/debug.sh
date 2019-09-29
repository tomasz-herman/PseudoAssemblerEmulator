#!/bin/bash
echo Specify input file:
read -r InputPath
java -jar PseudoAssemblerEmulator.jar -di "$InputPath"
read -n 1 -s -r -p "Press any key to continue..."
echo