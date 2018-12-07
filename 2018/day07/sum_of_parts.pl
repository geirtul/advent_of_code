#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any);
use List::UtilsBy qw(max_by);

# File import
my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
my @alphabet = ("A".."Z");
my %sequences;
foreach $_ (@lines) {
  my @order = order_from_string($_);
  if (exists($sequences{$order[0]})) {
    push(@{$sequences{$order[0]}}, $order[1])
  } else {
    my @arr = ($order[1]);
    $sequences{$order[0]} = \@arr;
  }
}
print Dumper(\%sequences);

# Solve the puzzle
## Part 1:


## Part 2:

# Subroutine declarations

sub order_from_string {
  my $in = shift;
  my @events = $in =~ /Step ([A-Z]) must be finished before step ([A-Z])/g;
  return @events;
}
