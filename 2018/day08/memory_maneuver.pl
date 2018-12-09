#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any none);
use List::UtilsBy qw(max_by);
use List::MoreUtils qw(first_index);
use Algorithm::Dependency::Ordered;

# File import
my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
my @license_file = split(/\s/, $lines[0]);
print @license_file;


# Solve the puzzle
## Part 1: 2 3 [0 3 10 11 12] 1 1 [0 1 99] 2 1 1 2
print "\nPart 1:\n";
my %tree;
build_node(0);
## Part 2:
print "\nPart 2:\n";

# Subroutine declarations
sub build_node {
  # Assumes node can't have no metadata
  my $index = shift;
  my $n_children = $license_file[$index];
  my $n_metadata = $license_file[$index+1];
  my $iter = 0;
  my $meta_index = $index + 2;
  if ($n_children == 0) {
    my @metadata = splice(@license_file, $index, $n_metadata+2);
    print "@metadata";
    print "\n";
    my $name = scalar(keys(%tree))+1;
    $tree{$name} = \@metadata; #Set ref to current metadata.
    return;
  }
  for (my $i=0; $i < $n_children; $i++) {
    build_node($index + 2);
  }
  my @metadata = splice(@license_file, $index, $n_metadata+2);
  print "@metadata";
  print "\n";
  my $name = scalar(keys(%tree)) + 1;
  $tree{$name} = \@metadata; #Set ref to current metadata.
  return;
}
