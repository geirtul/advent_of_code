#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any none);
use List::UtilsBy qw(max_by);
use List::MoreUtils qw(first_index);

# File import
my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
# Remove unnecessary stuff from the lines, leaving csv
# players, value of last marble, high score
foreach my $line (@lines) {
  $line =~ s/[a-z\s]//g;
  $line =~ s/[;:]/,/g;
}


# Solve the puzzle
## Part 1:
print "\nPart 1:\n";


## Part 2: #112 too low.
#print "\nPart 2:\n";


# Subroutine declarations
