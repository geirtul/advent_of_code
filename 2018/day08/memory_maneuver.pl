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


# Solve the puzzle
## Part 1:
print "\nPart 1:\n";
my %tree;
build_node(0);
print Dumper(\%tree);
my $sum_metadata = sum_metadata(\%tree);
print "Sum of metadata = $sum_metadata\n";
## Part 2:
print "\nPart 2:\n";

# Subroutine declarations
sub sum_metadata {
  # Sum all the metadata in the tree.
  my $hashref = shift;
  my $metasum = 0;
  foreach my $k (keys %$hashref) {
    my @arr = @{$hashref->{$k}};
    my $tmp_sum = 0;
    for (my $i=2; $i < scalar(@arr); $i++) {
      $tmp_sum += $arr[$i];
    }
    $metasum += $tmp_sum;
  }
  return $metasum;
}

sub build_node {
  my $index = shift;
  my $n_children = $license_file[$index];
  my $n_metadata = $license_file[$index+1];
  my $iter = 0;
  if ($n_children == 0) {
    my @data = splice(@license_file, $index, $n_metadata+2);
    my $name = scalar(keys(%tree));
    $tree{$name} = \@data; #Set ref to current metadata.
    return;
  }
  for (my $i=0; $i < $n_children; $i++) {
    build_node($index + 2);
  }
  my @data = splice(@license_file, $index, $n_metadata+2);
  if ($index == 0) {
    $name = 'root';
  } else {
    my $name = scalar(keys(%tree));
  }
  $tree{$name} = \@data; #Set ref to current metadata.
  return;
}
