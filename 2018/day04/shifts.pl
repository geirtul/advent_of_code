#!/usr/bin/perl
use strict;
use warnings;

use DateTime;
use Data::Dumper;
use List::Util ("none", "any");

my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";


# =========== Solve the puzzle ============
# Part 1:
@lines = sort(@lines);
calc_sleeptime(@lines);
# Part 2:


# =========== Subroutine declarations ============

sub calc_sleeptime {
  my @in = @_;
  # Match datetime, what happens, and guard ID.
  my $regex = qr/\[(.+)\]\s(Guard|falls|wakes)\s(#\d+|asleep|up)/;
  foreach $_ (@in) {
    my @match = $_ =~ $regex;
    print Dumper(\@match);
  }

}
