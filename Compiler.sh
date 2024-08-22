#!/bin/bash
files=("Keyboard" "Mouse" "Player" "Screenshot" "Target" "aiming");
cd "C:\Users\carlk\Documents\GitHub\diep.io-AI\java-diep.io-AI\src";
for el in "${files[@]}";
do
    var="$el.class";
    rm $var;
    var="$el.java";
    javac $var;
done