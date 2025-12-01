#!/bin/bash

filename="$1"
mapfile -t lines < "$filename"

curr=50
regex="([[:alpha:]])([[:digit:]]+)"
rotate() {
    if [[ "$1" =~ $regex ]]; then
        dir="${BASH_REMATCH[1]}"
        num=$(( BASH_REMATCH[2] % 100 ))

        case $dir in
            L) (( curr -= num )) ;;
            R) (( curr += num )) ;;
        esac

        (( curr = (curr + 100) % 100 ))
    fi
}

num_zeros=0
for move in "${lines[@]}"; do
    rotate "$move"
    (( num_zeros += curr == 0 ? 1 : 0))
done
echo "The code is: $num_zeros"
