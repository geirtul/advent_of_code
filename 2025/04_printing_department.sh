#!/bin/bash

filename="$1"
mapfile -t lines < "$filename"

accessible=0

num_rows=${#lines}
num_cols=${#lines[0]}

for (( row=0; row < num_rows; row++ )); do
    for (( col=0; col < num_cols; col++ )); do

	if [[ "${lines[row]:col:1}" != "@" ]]; then
	    continue
	fi

	# Corners
	if (( (row == 0 || row == num_rows - 1) &&
		(col == 0 || col == num_cols - 1) )); then
	    (( accessible++ ))
	    continue
	fi
	
	count=0
	if (( row == 0 )); then
	    [[ "${lines[row]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}" == "@" ]] && (( count++ ))
	elif (( row == num_rows - 1 )); then
	    [[ "${lines[row]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}" == "@" ]] && (( count++ ))
	elif (( col == 0 )); then
	    [[ "${lines[row-1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}" == "@" ]] && (( count++ ))
	elif (( col == num_cols - 1 )); then
	    [[ "${lines[row-1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}" == "@" ]] && (( count++ ))
	else
	    [[ "${lines[row-1]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}" == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}" == "@" ]] && (( count++ ))
	fi
	
	(( count < 4 )) && (( accessible++ ))
    done
done


echo "Part 1: $accessible"

echo "Part 2: "
