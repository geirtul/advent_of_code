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
        (( num % 100 ))

        case $dir in
            L) (( curr -= num )) ;;
            R) (( curr += num )) ;;
        esac

        (( curr = (curr + 100) % 100 ))
        (( num_zeros += curr == 0 ? 1 : 0 ))

    fi
done


echo "Part 1: $num_zeros"

num_zeros=0
curr=50
for move in "${lines[@]}"; do
    if [[ "$move" =~ $regex ]]; then
        dir="${BASH_REMATCH[1]}"
        num="${BASH_REMATCH[2]}"
        prev=$curr

        full_rotations=$(( num / 100 ))
        
        moves=$(( num % 100 ))

        case $dir in
            L) (( curr -= moves )) ;;
            R) (( curr += moves )) ;;
        esac

        # Add number of full rotations, if any
        if (( full_rotations > 0 )); then
            (( num_zeros += prev != 0 ? full_rotations : full_rotations - 1 ))
        fi

        # We've crossed zero in either direction and we didn't start on 0
        if (( prev != 0 )); then
            (( num_zeros += curr > 100 || curr < 0 ))
        fi

        (( curr = (curr + 100) % 100 ))

        # Was on zero, made full rotations, no longer on zero = +1
        (( num_zeros += prev == 0 && curr != 0 && full_rotations > 0 ))

        # Landed on 0 after rotation
        (( num_zeros += curr == 0 ))
    fi

done
echo "Part 2: $num_zeros"
