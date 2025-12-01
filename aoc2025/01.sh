#!/bin/bash

filename="$1"
mapfile -t lines < "$filename"

curr=50
regex="([[:alpha:]])([[:digit:]]+)"

num_zeros=0
for move in "${lines[@]}"; do
    if [[ "$move" =~ $regex ]]; then
        dir="${BASH_REMATCH[1]}"
        num="${BASH_REMATCH[2]}"
        prev=$curr
        full_rotations=$(( num / 100 ))
        (( num % 100 ))

        case $dir in
            L) (( curr -= num )) ;;
            R) (( curr += num )) ;;
        esac

        if (( prev != 0 )); then
            (( num_zeros += curr < 0 || curr > 100 ? 1 : 0))
        fi

        (( curr = (curr + 100) % 100 ))
        (( num_zeros += curr == 0 ? 1 : 0 ))

        # echo "$move: $num_zeros, full: $full_rotations, prev: $prev, curr: $curr"
    fi

done
echo "The code is: $num_zeros"
