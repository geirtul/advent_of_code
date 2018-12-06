#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any);
use List::UtilsBy qw(max_by);

# File import
my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
## Extract coordinates from input
my @x;
my @y;
foreach $_ (@lines) {
  my @match = $_ =~ /(\d+), (\d+)/g;
  push(@x, $match[0]);
  push(@y, $match[1]);
}
## Determine maximum size of grid using the extreme points.
## If a point's x or y coordinate is equal to the highest or lowest
## it has an infinite area
my $x_max = max(@x);
my $y_max = max(@y);
my $x_min = min(@x);
my $y_min = min(@y);

## Store points as hash, and edgepoints
my %points;
my %edges;
for (my $i=0; $i < scalar(@x); $i++) {
  if ((($x[$i] == $x_max) or ($y[$i] == $y_max)) or (($x[$i] == $x_min) or ($y[$i] == $y_min))){
    $points{"$x[$i],$y[$i]"} = "edge";
    $edges{"$x[$i],$y[$i]"} = 1;
  } else {
  $points{"$x[$i],$y[$i]"} = 1;
  }
}


# Solve the puzzle
## Part 1: 7556, 6390 too high

my %closest_points;
for (my $i=0; $i <= $x_max; $i++) {
  J: for (my $j=0; $j <= $y_max; $j++) {
    if (exists($points{"$i,$j"})) {
      $closest_points{"$i,$j"} = "$i,$j";
      next J;
    }
    my %distances;
    for (my $k=0; $k < scalar(@lines); $k++) {
      my $dist = manhattan_distance($i, $j, $x[$k], $y[$k]);
      #print "Distance $i,$j to $x[$k],$y[$k] = $dist\n";
      #print "^ ZERO!\n" if (($i == $x[$k]) && ($j == $y[$k]));
      if (exists($distances{$dist})) { # set duplicate distance to x and break
        $distances{$dist} = "X";
      } else {
        $distances{$dist} = "$x[$k],$y[$k]";
      }
    }
    #if ((($i != $x_max) && ($j != $y_max)) && (($i != $x_min) && ($j != $y_min))) {
    $closest_points{"$i,$j"} = $distances{min(keys(%distances))};
    #}
  } # End j loop
} # End i loop

## Make hash of points that can't have infinite area around them
my %valid_points;
for (my $i=0; $i < scalar(@x); $i++) {
  if ($points{"$x[$i],$y[$i]"} eq 1) {
    # Find closest edgepoints
    my $dist = 1e9;
    my $closest;
    foreach my $edge (keys(%edges)) {
      my @match = $edge =~ /(\d+),(\d+)/g;
      my $ex = $match[0];
      my $ey = $match[1];
      my $tmp_dist = manhattan_distance($x[$i], $y[$i], $ex, $ey);
      if ($tmp_dist < $dist) {
        $dist = $tmp_dist;
        $closest = $edge;
      }
    }

    # Check if edgepoint belongs to this point.
    if (
      (($closest_points{"$x_max,$y[$i]"} eq "$x[$i],$y[$i]") or
      ($closest_points{"$x_min,$y[$i]"} eq "$x[$i],$y[$i]")) or
      (($closest_points{"$y_max,$x[$i]"} eq "$x[$i],$y[$i]") or
      ($closest_points{"$y_min,$x[$i]"} eq "$x[$i],$y[$i]"))
      ) {
      next;
    } else {
      $valid_points{"$x[$i],$y[$i]"} = 1;
    }
  } #End if
} #End for

my %sizes;
foreach my $val (values(%closest_points)) {
  if (exists($valid_points{$val})) {
    $sizes{$val}++;
  }
}
my $highest = max_by {$sizes{$_}} keys(%sizes);
print "Largest area: ".$sizes{$highest}."\n";
print Dumper(sort(values(%sizes)));

## Part 2:

# Subroutine declarations

sub manhattan_distance {
  # Calculate the manhattan disance between two points.
  my ($x1, $y1, $x2, $y2) = @_;
  my $mdist = abs($x2 - $x1) + abs($y2 - $y1);
  return $mdist;
}
