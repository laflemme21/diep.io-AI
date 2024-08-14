#!/bin/bash
files=("Keyboard" "Mouse" "Player" "Screenshot" "Target");
for el in "${files[@]}";
do
    cd "C:\Users\carlk\Documents\GitHub\diep.io-AI\java";
    cd $el;
    var="$el.class";
    rm $var;
    var="$el.java";
    javac $var;
done
others=("aiming");
cd "C:\Users\carlk\Documents\GitHub\diep.io-AI\java";
for el in "${others[@]}";
do
    var="$el.class";
    rm $var;
    var="$el.java";
    javac $var;
done