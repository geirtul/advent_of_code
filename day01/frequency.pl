#!/usr/bin/perl
use strict;
use warnings;


my $file = "input.txt";
# Open file and read contents into array
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

my $sum = 0;
foreach my $line (@lines){
    $sum += $line;
}
print $sum."\n";
