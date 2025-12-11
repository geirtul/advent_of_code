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
	    [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
	elif (( row == num_rows - 1 )); then
	    [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
	elif (( col == 0 )); then
	    [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
	elif (( col == num_cols - 1 )); then
	    [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
	else
	    [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
	    [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
	    [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
	fi
	
	(( count < 4 )) && (( accessible++ ))
    done
done


echo "Part 1: $accessible"

accessible=0
roll_indices=()

while :; do
    for (( row=0; row < num_rows; row++ )); do
        for (( col=0; col < num_cols; col++ )); do

        if [[ "${lines[row]:col:1}" != "@" ]]; then
            continue
        fi

        # Corners
        if (( (row == 0 || row == num_rows - 1) &&
            (col == 0 || col == num_cols - 1) )); then
            (( accessible++ ))
            roll_indices+=("$row $col")
            continue
        fi
        
        count=0
        if (( row == 0 )); then
            [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
        elif (( row == num_rows - 1 )); then
            [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
        elif (( col == 0 )); then
            [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
        elif (( col == num_cols - 1 )); then
            [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
        else
            [[ "${lines[row-1]:col-1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row]:col-1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col-1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row-1]:col+1:1}"    == "@" ]] && (( count++ ))
            [[ "${lines[row]:col+1:1}"      == "@" ]] && (( count++ ))
            [[ "${lines[row+1]:col+1:1}"    == "@" ]] && (( count++ ))
        fi
        
        if (( count < 4 )); then
            (( accessible++ ))
            roll_indices+=("$row $col")
        fi

        done
    done

    (( ${#roll_indices} > 0 )) || break

    for indices in "${roll_indices[@]}"; do
        read -r row col <<< "$indices"
        line="${lines[row]}"
        lines[row]="${line:0:col}x${line:col+1}"
    done

    roll_indices=()
done

echo "Part 2: $accessible"
