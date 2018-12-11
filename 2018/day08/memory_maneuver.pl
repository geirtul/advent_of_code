#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any none);
use List::UtilsBy qw(max_by);
use List::MoreUtils qw(first_index);

# File import
my $file = "input.txt";
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
my $num_nodes = scalar(keys %tree);
print "Built tree with $num_nodes nodes.\n";
my $sum_metadata = sum_metadata($tree{"Groot"}, 0);
print "Sum of metadata in tree = $sum_metadata\n";
## Part 2: #112 too low.
print "\nPart 2:\n";
my $root_value = sum_metadata($tree{"Groot"}, 1);
print "Value of root node = $root_value\n";

# Subroutine declarations
sub sum_metadata {
  # Sum all the metadata in the tree.
  my $current_node = shift;
  my $part2 = shift; # if 1, use part 2 restrictions.
  # Dereference arrays
  my @current_children = @{@$current_node[0]};
  my @current_data = @{@$current_node[1]};

  my $metasum = 0;
  # This part splits into calculating value of root note for part 2
  # and calculating the metasum of the whole tree.
  if ($part2) {
    # For each metadata entry, if the node doesn't have children, add
    # the entry to metadata sum.
    for (my $i=2; $i < scalar(@current_data); $i++) {
      if (not scalar(@current_children)) {
        $metasum += $current_data[$i];
      } else {
        # If the node does have children, check if it's a valid reference
        # and add the calculated value of the child node to metadata sum.
        if ($current_data[$i] <= scalar(@current_children)) {
          my $index = $current_data[$i]; # Child referred to by metadata
          $metasum += sum_metadata($current_children[$index-1], $part2);
        }
      }
    }
  } else { # Part 1
    # If the node has children, call sum_metadata on them.
    if (scalar(@current_children)){
      foreach my $child (@current_children) {
        $metasum += sum_metadata($child, $part2);
      }
    }
    # Sum the metadata of the node.
    for (my $i=2; $i < scalar(@current_data); $i++) {
        $metasum += $current_data[$i];
    }
  }
  return $metasum;
}

sub build_node {
  my $index = shift;
  my $n_children = $license_file[$index];
  my $n_metadata = $license_file[$index+1];

  # children array stores references to the nodes spawned
  # by this build call, i.e the directly subsequent calls.
  my @children;
  my @data;
  my @node = (\@children, \@data);

  # If the node is supposed to have children, make them.
  for (my $i=0; $i < $n_children; $i++) {
    push(@children, build_node($index + 2));
  }

  @data = splice(@license_file, $index, $n_metadata+2);
  my $name;
  if ($index == 0) {
    $name = 'Groot';
  } else {
    $name = scalar(keys(%tree));
  }
  $tree{$name} = \@node; #Store the node.
  return \@node;
}
