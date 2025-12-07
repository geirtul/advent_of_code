#!/bin/bash

filename="$1"
mapfile -t lines < "$filename"

# Count top row
# Count bottom row
# Count first column
# Count last column

for (( row=0; row < ${#lines}; row++ )); do
    for (( col=0; col < ${#lines[0]}; col++ )); do
	if (( row == 0 )); then
	    echo "Row is 0"
	elif (( row == ${#lines} - 1 )); then
	    echo "Row is $(( ${#lines} - 1 ))"
	elif (( col == 0 )); then
	    echo "Col is 0"
	elif (( col == ${#lines[0]} - 1 )); then
	    echo "Col is $(( ${#lines[0]} - 1 ))"
	else
	    echo "Regular"
	fi
    done
done



for line in "${lines[@]}"; do
    #echo "${line:0:1}"
done


echo "Part 1: "

echo "Part 2: "
