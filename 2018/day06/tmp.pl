#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min);
use List::UtilsBy qw(max_by);

# File import
my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation


# Solve the puzzle
## Part 1:


## Part 2:

# Subroutine declarations
