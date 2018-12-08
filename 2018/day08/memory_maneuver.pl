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

## Part 2:
print "\nPart 2:\n";

# Subroutine declarations
sub build_node {
  # Assumes node can't have no metadata
  my $index = shift;
  my $n_children = $licence_file[$index];
  my $n_metadata = $licence_file[$index+1];
  if ($n_children == 0) {
    my @metadata;
    my $meta_index = $index + 2;
    # Bytt ut while loop med splice
    while ($meta_index < $index + 1 + $n_metadata) {
      push(@metadata, $licence_file[$meta_index]);
      $meta_index++;
    }
    my $name = scalar(keys(%tree))+1;
    $tree{$name} = \@metadata; #Set ref to current metadata.
    return;
  }
  # If the number of children is larger than one though, we have work to do.



}
